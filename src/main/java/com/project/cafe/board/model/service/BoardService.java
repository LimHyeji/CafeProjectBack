package com.project.cafe.board.model.service;

import com.project.cafe.board.model.dto.response.BoardDetailInfoResponseDto;
import com.project.cafe.board.model.dto.response.BoardSimpleInfoResponseDto;
import com.project.cafe.board.model.repository.BoardRepository;
import com.project.cafe.board.model.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardSimpleInfoResponseDto> getSimpleInfoBoardList() {
        List<BoardSimpleInfoResponseDto> boardList = boardRepository.getSimpleInfoBoardList();
        return boardList;
    }

    public BoardDetailInfoResponseDto getBoardDetailInfo(int articleNo) {
        BoardDetailInfoResponseDto boardDetailOfArticleNo = boardRepository.getBoardDetailInfo(articleNo);
        return boardDetailOfArticleNo;
    }
}
