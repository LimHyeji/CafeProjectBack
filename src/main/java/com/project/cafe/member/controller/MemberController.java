package com.project.cafe.member.controller;

import com.project.cafe.exception.list.*;
import com.project.cafe.member.model.dto.request.MemberLoginRequestDto;
import com.project.cafe.member.model.dto.request.MemberModifyRequestDto;
import com.project.cafe.member.model.dto.request.MemberRegistRequestDto;
import com.project.cafe.member.model.dto.response.MemberInfoResponseDto;
import com.project.cafe.member.model.service.MemberService;
import com.project.cafe.util.jwt.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/member")
@CrossOrigin(origins="http://localhost:8080",methods={RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST},allowCredentials = "true",allowedHeaders = "*")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //post, member -> 성공/실패
    @PostMapping(value="/regist")
    public ResponseEntity<String> registMember(@RequestBody MemberRegistRequestDto dto) throws NoMemberRegistException {
        //System.out.println(dto.toString());
        //System.out.println("---service 호출---");
        memberService.registMember(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    //post, member -> 토큰
    @PostMapping(value="/login")
    public ResponseEntity<TokenDto> loginMember(@RequestBody MemberLoginRequestDto dto) throws NoMemberLoginException {
        return ResponseEntity.ok().body(memberService.loginMember(dto));
    }

    //get 토큰 -> memberInfo
    @GetMapping
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(HttpServletRequest request) throws NoMemberInfoException, MaliciousAccessExcption {
        String token=request.getHeader("Authorization").split(" ")[1];//맨 앞에는 "bearer"이 붙으므로 1번 인덱스를 받아옴
        return ResponseEntity.ok().body(memberService.getMemberInfo(token));
    }

    //post, member(+ 현 비번, 새 비번),토큰 -> 성공/실패
    @PostMapping(value="/modify")
    public ResponseEntity<MemberInfoResponseDto> modifyMemberInfo(HttpServletRequest request,
                                                                  @RequestBody MemberModifyRequestDto dto) throws NoMemberModifyException, MaliciousAccessExcption{
        String token=request.getHeader("Authrization").split(" ")[1];
        return ResponseEntity.ok().body(memberService.modifyMemberInfo(token,dto));
    }
}
