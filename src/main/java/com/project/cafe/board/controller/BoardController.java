package com.project.cafe.board.controller;

import com.project.cafe.board.model.dto.response.BoardDetailInfoResponseDto;
import com.project.cafe.board.model.dto.response.BoardSimpleInfoResponseDto;
import com.project.cafe.board.model.service.BoardService;
import com.project.cafe.board.model.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardService boardService;


    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardSimpleInfoResponseDto>> getBoardSimpleInfoList() {
        return ResponseEntity.ok().body(boardService.getSimpleInfoBoardList());
    }

    //articleNo는 PathVariable
    @GetMapping("{articleNo}")
    public ResponseEntity<BoardDetailInfoResponseDto> getBoardDetailInfo(@PathVariable("articleNo") int articleNo) throws Exception{

        return ResponseEntity.ok().body(boardService.getBoardDetailInfo(articleNo));
    }





}
