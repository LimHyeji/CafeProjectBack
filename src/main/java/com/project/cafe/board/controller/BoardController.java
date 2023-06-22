package com.project.cafe.board.controller;

import com.project.cafe.board.model.dto.request.BoardCreateRequestDto;
import com.project.cafe.board.model.dto.request.BoardUpdateRequestDto;
import com.project.cafe.board.model.dto.response.BoardDetailInfoResponseDto;
import com.project.cafe.board.model.dto.response.BoardSimpleInfoResponseDto;
import com.project.cafe.board.model.service.BoardService;
import com.project.cafe.board.model.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody BoardCreateRequestDto boardCreateRequestDto ) {

        boardService.createBoard(boardCreateRequestDto);

        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {

        boardService.updateBoard(boardUpdateRequestDto);

        return ResponseEntity.ok().body("ok");
    }





}
