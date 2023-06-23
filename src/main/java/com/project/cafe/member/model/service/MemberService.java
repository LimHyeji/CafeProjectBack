package com.project.cafe.member.model.service;

import com.project.cafe.exception.list.*;
import com.project.cafe.member.model.dto.request.MemberLoginRequestDto;
import com.project.cafe.member.model.dto.request.MemberModifyRequestDto;
import com.project.cafe.member.model.dto.request.MemberRegistRequestDto;
import com.project.cafe.member.model.dto.response.MemberInfoResponseDto;
import com.project.cafe.member.model.repository.MemberRepository;
import com.project.cafe.member.model.repository.MemberSecRepository;
import com.project.cafe.member.model.vo.MemberSecVO;
import com.project.cafe.member.model.vo.MemberVO;
import com.project.cafe.util.encrypt.OpenCrypt;
import com.project.cafe.util.jwt.JWTException;
import com.project.cafe.util.jwt.JwtProvider;
import com.project.cafe.util.jwt.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberSecRepository memberSecRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberSecRepository memberSecRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.memberSecRepository = memberSecRepository;
        this.jwtProvider = jwtProvider;
    }

    /*
    트랜잭션 만족하지 않을 때의 예외 처리?
    member테이블과 memberSec테이블이 원자성을 만족해야 함 ((예) 외래키, ... )
     */

    //아이디 중복 상황을 고려해 serializable로 트랜잭션 처리
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registMember(MemberRegistRequestDto dto) throws NoMemberRegistException {
        Optional<MemberVO> existMember=memberRepository.findById(dto.getMemberId());//단순히 회원이 존재하는 조회하기 위함이므로 Optional 사용
        if(existMember.isPresent()){
            throw new NoMemberRegistException("이미 존재하는 회원입니다.");
        }

        try {
            String salt = UUID.randomUUID().toString();
            String hashPwd = OpenCrypt.getSHA256(dto.getMemberPwd(), salt);

            MemberVO memberVO=new MemberVO(dto.getMemberId(),hashPwd,dto.getMemberName(),dto.getMemberEmail());
            MemberSecVO secVO=new MemberSecVO(dto.getMemberId(),salt);

            memberRepository.save(memberVO);
            memberSecRepository.save(secVO);
        }
        catch(Exception e){
            throw new NoMemberRegistException("회원가입에 실패했습니다.");
        }
    }

    public TokenDto loginMember(MemberLoginRequestDto dto) throws NoMemberLoginException {
        MemberVO member=memberRepository.findById(dto.getMemberId()).orElseThrow(()->new NoMemberLoginException("아이디를 확인하세요."));
        MemberSecVO memberSec=memberSecRepository.findById(dto.getMemberId()).orElseThrow(()->new NoMemberLoginException("관리자에게 문의하세요."));//member테이블에 존재하지만 memberSec테이블에서는 부득이하게 삭제된 경우 로그인이 되지 않는 상황 발생

        try{
            String salt = memberSec.getSalt();
            String hashPwd = OpenCrypt.getSHA256(dto.getMemberPwd(), salt);

            if(member.getMemberPwd().equals(hashPwd)){
                String accessToken=jwtProvider.createToken(member);
                return new TokenDto(accessToken);
            }
            else{
                throw new NoMemberLoginException("비밀번호를 확인하세요.");
            }
        }
        catch(Exception e){
            throw new NoMemberLoginException("로그인에 실패했습니다.");
        }

    }

    public MemberInfoResponseDto getMemberInfo(String token) throws NoMemberInfoException, MaliciousAccessExcption {
        try{
            MemberVO parsedMember=jwtProvider.parseInfo(token);
            MemberVO memberVO=memberRepository.findById(parsedMember.getMemberId()).orElseThrow(()->new MaliciousAccessExcption());//토큰 변조가 발생한 상황으로 유추

            return new MemberInfoResponseDto(memberVO.getMemberId(), memberVO.getMemberName(), memberVO.getMemberEmail());
        }catch(JWTException e){
            throw new NoMemberInfoException("회원정보조회에 실패했습니다.");
        }
    }

    //동시에 수정되는 상황을 고려한 트랜잭션 처리
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public MemberInfoResponseDto modifyMemberInfo(String token, MemberModifyRequestDto dto) throws NoMemberModifyException, MaliciousAccessExcption {
        try{
            //토큰에 저장된 memberId 확인
            String memberId=jwtProvider.parseInfo(token).getMemberId();
            MemberVO member=memberRepository.findById(memberId).orElseThrow(()->new MaliciousAccessExcption());
            MemberSecVO memberSec=memberSecRepository.findById(memberId).orElseThrow(()->new NoMemberModifyException("관리자에게 문의하세요."));

            //현재 비밀번호 확인
            String salt = memberSec.getSalt();
            String hashPwd = OpenCrypt.getSHA256(dto.getCurrentPwd(), salt);

            //현재 비밀번호가 올바르게 입력된 경우
            if(hashPwd.equals(member.getMemberPwd())){
                //새로운 데이터 저장
                String newHashPwd= OpenCrypt.getSHA256(dto.getNewPwd(), salt);

                member.setMemberPwd(newHashPwd);
                member.setMemberName(dto.getMemberName());
                member.setMemberEmail(dto.getMemberEmail());

                memberRepository.save(member);

                return new MemberInfoResponseDto(member.getMemberId(),member.getMemberName(),member.getMemberEmail());
            }
            else{
                throw new NoMemberModifyException("비밀번호를 확인하세요.");
            }
        }catch(Exception e){//JWTException, NoSuchAlgorithmException
            throw new NoMemberModifyException("회원정보수정에 실패했습니다.");
        }
    }
}
