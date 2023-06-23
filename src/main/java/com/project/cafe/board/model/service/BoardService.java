package com.project.cafe.board.model.service;

import com.project.cafe.board.model.dto.request.BoardCreateRequestDto;
import com.project.cafe.board.model.dto.request.BoardUpdateRequestDto;
import com.project.cafe.board.model.dto.response.BoardDetailInfoResponseDto;
import com.project.cafe.board.model.dto.response.BoardSimpleInfoResponseDto;
import com.project.cafe.board.model.repository.BoardRepository;
import com.project.cafe.board.model.vo.BoardVO;
import com.project.cafe.exception.list.AuthorizationException;
import com.project.cafe.exception.list.InvalidBoardException;
import com.project.cafe.exception.list.MaliciousAccessExcption;
import com.project.cafe.exception.list.NoDetailBoardException;
import com.project.cafe.member.model.repository.MemberRepository;
import com.project.cafe.member.model.vo.MemberVO;
import com.project.cafe.util.jwt.JWTException;
import com.project.cafe.util.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public BoardService(MemberRepository memberRepository, BoardRepository boardRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.jwtProvider = jwtProvider;
    }

    public List<BoardSimpleInfoResponseDto> getSimpleInfoBoardList() {
        List<BoardVO> all = boardRepository.findAll();

        BoardSimpleInfoResponseDto dto = new BoardSimpleInfoResponseDto();
        List<BoardSimpleInfoResponseDto> boardList = new ArrayList<>();
        int listSize = all.size();
        for (int i=0; i<listSize; i++) {
            boardList.add(dto.convertToDto(all.get(i)));
        }

        return boardList;
    }

    public BoardDetailInfoResponseDto getBoardDetailInfo(int articleNo) throws NoDetailBoardException{
        BoardVO board = boardRepository.findById(articleNo).orElseThrow(()->new NoDetailBoardException());
        BoardDetailInfoResponseDto boardDetailOfArticleNo = new BoardDetailInfoResponseDto();
        boardDetailOfArticleNo.convertToDto(board);

        return boardDetailOfArticleNo;
    }

    @Transactional
    public void createBoard(String memberToken, BoardCreateRequestDto boardCreateRequestDto) {

        try {
            MemberVO tokenInfo = jwtProvider.parseInfo(memberToken);
            MemberVO member = memberRepository.findById(tokenInfo.getMemberId()).orElseThrow(() -> new MaliciousAccessExcption());

            BoardVO board = new BoardVO();
            board.setContent(boardCreateRequestDto.getContent());
            board.setWriter(boardCreateRequestDto.getWriter());
            board.setTitle(boardCreateRequestDto.getTitle());

            boardRepository.save(board);
        } catch (JWTException e){
            throw new InvalidBoardException("다시 로그인해주세요.");
        }
    }

    public void updateBoard(String memberToken, BoardUpdateRequestDto boardUpdateRequestDto) {
        try {
            MemberVO tokenInfo = jwtProvider.parseInfo(memberToken);
            MemberVO member = memberRepository.findById(tokenInfo.getMemberId()).orElseThrow(() -> new MaliciousAccessExcption());

            BoardVO board = boardRepository.findById(boardUpdateRequestDto.getArticleNo()).orElseThrow(() -> new NoDetailBoardException());

            if (!board.getWriter().equals(member.getMemberId())) throw new AuthorizationException();

            board.setTitle(boardUpdateRequestDto.getTitle());
            board.setContent(boardUpdateRequestDto.getContent());

            boardRepository.save(board);
        } catch (JWTException e){
            throw new InvalidBoardException("다시 로그인해주세요.");
        }
    }

    public void deleteBoard(String memberToken, int articleNo) {
        try {
            MemberVO tokenInfo = jwtProvider.parseInfo(memberToken);
            MemberVO member = memberRepository.findById(tokenInfo.getMemberId()).orElseThrow(() -> new MaliciousAccessExcption());

            BoardVO board = boardRepository.findById(articleNo).orElseThrow(() -> new NoDetailBoardException());

            if (!board.getWriter().equals(member.getMemberId())) throw new AuthorizationException();

            boardRepository.deleteById(articleNo);
        } catch(JWTException e) {
            throw new InvalidBoardException("다시 로그인해주세요.");
        }
    }
}
