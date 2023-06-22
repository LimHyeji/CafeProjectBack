package com.project.cafe.member.controller;

import com.project.cafe.member.model.dto.request.MemberLoginRequestDto;
import com.project.cafe.member.model.dto.request.MemberRegistRequestDto;
import com.project.cafe.member.model.service.MemberService;
import com.project.cafe.util.jwt.JwtProvider;
import com.project.cafe.util.jwt.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/member")
@CrossOrigin(origins="http://localhost:8080",methods={RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST},allowCredentials = "true",allowedHeaders = "*")
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @Autowired
    public MemberController(MemberService memberService, JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    //post, member -> 성공/실패
    @PostMapping(value="/regist")
    public ResponseEntity<String> registMember(@RequestBody MemberRegistRequestDto dto){
        //System.out.println(dto.toString());
        //System.out.println("---service 호출---");
        memberService.registMember(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    //post, member -> 토큰
    @PostMapping(value="/login")
    public ResponseEntity<TokenDto> loginMember(@RequestBody MemberLoginRequestDto dto){
        return ResponseEntity.ok().body(memberService.loginMember(dto));
    }

//    //get ${memberId}, -> memberInfo
//    @GetMapping
//    public void getMemberInfo(){
//
//    }
//
//    //post, member(+ 현 비번, 새 비번) -> 성공/실패
//    @PostMapping(value="/${memberId}/modify")
//    public void modifyMemberInfo(){
//
//    }
}
