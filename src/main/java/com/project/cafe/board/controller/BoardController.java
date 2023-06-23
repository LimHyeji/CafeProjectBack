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
    public ResponseEntity<String> createBoard(@RequestHeader("Authorization") String authorization,
                                                  @RequestBody BoardCreateRequestDto boardCreateRequestDto ) {
        String memberToken = authorization.split(" ")[1];
        boardService.createBoard(memberToken,boardCreateRequestDto);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestHeader("Authorization") String authorization,
                                              @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        String memberToken = authorization.split(" ")[1];
        boardService.updateBoard(memberToken,boardUpdateRequestDto);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("delete/{articleNo}")
    public ResponseEntity<String> deleteBoard(@RequestHeader("Authorization") String authorization,
                                              @PathVariable int articleNo) {
        String memberToken = authorization.split(" ")[1];
        boardService.deleteBoard(memberToken,articleNo);

        return ResponseEntity.ok().body("ok");

    }





}
