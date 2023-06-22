package com.project.cafe.member.model.dto.request;

import com.project.cafe.exception.list.NoLoginMemberException;

public class MemberLoginRequestDto {

    private String memberId;
    private String memberPwd;

    public MemberLoginRequestDto() {  }

    public MemberLoginRequestDto(String memberId, String memberPwd) throws NoLoginMemberException {
        setMemberId(memberId);
        setMemberPwd(memberPwd);
    }

    /*
    태그 필터링 필요!
    inputString.replace( /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, "");
    sanitizedString = sanitizedString.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    */

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws NoLoginMemberException {
        if(!(memberId==null||memberId.trim().equals(""))){
            this.memberId=memberId;
        }
        else{
            throw new NoLoginMemberException("아이디가 공백이어서는 안됩니다.");
        }
    }

    public String getMemberPwd() throws NoLoginMemberException{
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        if(!(memberPwd==null||memberPwd.trim().equals(""))){
            this.memberPwd=memberPwd;
        }
        else{
            throw new NoLoginMemberException("비밀번호가 공백이어서는 안됩니다.");
        }
    }
}
