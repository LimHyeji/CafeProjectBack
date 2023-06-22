package com.project.cafe.board.model.service;

import com.project.cafe.board.model.dto.request.BoardCreateRequestDto;
import com.project.cafe.board.model.dto.request.BoardUpdateRequestDto;
import com.project.cafe.board.model.dto.response.BoardDetailInfoResponseDto;
import com.project.cafe.board.model.dto.response.BoardSimpleInfoResponseDto;
import com.project.cafe.board.model.repository.BoardRepository;
import com.project.cafe.board.model.vo.BoardVO;
import com.project.cafe.exception.list.NoDetailBoardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardSimpleInfoResponseDto> getSimpleInfoBoardList() {
        List<BoardVO> all = boardRepository.findAll();

        BoardSimpleInfoResponseDto dto = new BoardSimpleInfoResponseDto();
        int listSize = all.size();
        List<BoardSimpleInfoResponseDto> boardList = new ArrayList<>();
        for (int i=0; i<listSize; i++) {
            dto.convertToDto(all.get(i));
            boardList.add(dto);
        }
        return boardList;
    }

    public BoardDetailInfoResponseDto getBoardDetailInfo(int articleNo) throws NoDetailBoardException{
        BoardVO board = boardRepository.findById(articleNo).orElseThrow(()->new NoDetailBoardException());
        BoardDetailInfoResponseDto boardDetailOfArticleNo = new BoardDetailInfoResponseDto();
        boardDetailOfArticleNo.convertToDto(board);

        return boardDetailOfArticleNo;
    }

    public void createBoard(BoardCreateRequestDto boardCreateRequestDto) {
        BoardVO board = new BoardVO();
        board.setContent(boardCreateRequestDto.getContent());
        board.setWriter(boardCreateRequestDto.getWriter());
        board.setTitle(boardCreateRequestDto.getTitle());

        boardRepository.save(board);
    }

    public void updateBoard(BoardUpdateRequestDto boardUpdateRequestDto) throws NoDetailBoardException{
        BoardVO board = boardRepository.findById(boardUpdateRequestDto.getArticleNo()).orElseThrow(() -> new NoDetailBoardException());

        board.setTitle(boardUpdateRequestDto.getTitle());
        board.setContent(boardUpdateRequestDto.getContent());

        boardRepository.save(board);
    }
}
