package com.project.cafe.member.model.dto.response;

import com.project.cafe.exception.list.NoInfoMemberException;

public class MemberInfoResponseDto {
    private String memberId;
    private String memberName;
    private String memberEmail;

    public MemberInfoResponseDto() {
    }

    public MemberInfoResponseDto(String memberId, String memberName, String memberEmail) throws NoInfoMemberException {
        setMemberId(memberId);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws NoInfoMemberException{
        if(!(memberId==null||memberId.trim().equals(""))){
            this.memberId=memberId;
        }
        else{
            throw new NoInfoMemberException("아이디를 받아오지 못했습니다.");
        }
    }

    public String getMemberName(){
        return memberName;
    }

    public void setMemberName(String memberName) throws NoInfoMemberException{
        if(!(memberName==null||memberName.trim().equals(""))){
            this.memberName=memberName;
        }
        else{
            throw new NoInfoMemberException("이름을 받아오지 못했습니다.");
        }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) throws NoInfoMemberException{
        if(!(memberEmail==null||memberEmail.trim().equals(""))){
            this.memberEmail=memberEmail;
        }
        else{
            throw new NoInfoMemberException("이메일을 받아오지 못했습니다.");
        }
    }
}
