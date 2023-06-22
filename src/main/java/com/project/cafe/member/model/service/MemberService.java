package com.project.cafe.member.model.service;

import com.project.cafe.exception.list.NoLoginMemberException;
import com.project.cafe.exception.list.NoRegistMemberException;
import com.project.cafe.member.model.dto.request.MemberLoginRequestDto;
import com.project.cafe.member.model.dto.request.MemberRegistRequestDto;
import com.project.cafe.member.model.repository.MemberRepository;
import com.project.cafe.member.model.repository.MemberSecRepository;
import com.project.cafe.member.model.vo.MemberSecVO;
import com.project.cafe.member.model.vo.MemberVO;
import com.project.cafe.util.encrypt.OpenCrypt;
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
    public void registMember(MemberRegistRequestDto dto) throws NoRegistMemberException{

        Optional<MemberVO> existMember=memberRepository.findById(dto.getMemberId());
        if(existMember.isPresent()){
            throw new NoRegistMemberException("이미 존재하는 회원입니다.");
        }

        try {
            String salt = UUID.randomUUID().toString();
            String hashPwd = OpenCrypt.getSHA256(dto.getMemberPwd(), salt);
            //System.out.println(salt+" "+hashPwd);

            MemberVO memberVO=new MemberVO(dto.getMemberId(),hashPwd,dto.getMemberName(),dto.getMemberEmail());
            MemberSecVO secVO=new MemberSecVO(dto.getMemberId(),salt);
            //System.out.println(memberVO.toString());
            //System.out.println(secVO.toString());

            memberRepository.save(memberVO);
            //System.out.println(memberVO+"save");
            memberSecRepository.save(secVO);
            //System.out.println(secVO+"save");
        }
        catch(Exception e){
            throw new NoRegistMemberException("회원가입에 실패했습니다.");
        }
    }

    public TokenDto loginMember(MemberLoginRequestDto dto) throws NoLoginMemberException{
        Optional<MemberVO> member=memberRepository.findById(dto.getMemberId());
        Optional<MemberSecVO> memberSec=memberSecRepository.findById(dto.getMemberId());
        if(member.isEmpty()){
            throw new NoLoginMemberException("존재하지 않는 회원입니다.");
        }
        if(memberSec.isEmpty()){
            throw new NoLoginMemberException("관리자에게 문의하세요.");
        }

        try{
            String salt = memberSec.get().getSalt();
            //System.out.println("memberSec salt"+salt);
            String hashPwd = OpenCrypt.getSHA256(dto.getMemberPwd(), salt);
            //System.out.println("hashPwd "+hashPwd);

            if(member.get().getMemberPwd().equals(hashPwd)){
                //System.out.println("일치하는 상황");
                //System.out.println(member.get().toString());
                String accessToken=jwtProvider.createToken(member.get());
                //System.out.println("jwt"+accessToken);
                return new TokenDto(accessToken);
            }
            else{
                throw new NoLoginMemberException("비밀번호가 일치하지 않습니다.");
            }
        }
        catch(Exception e){
            throw new NoLoginMemberException("로그인에 실패했습니다.");
        }

    }
}
