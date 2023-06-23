package com.project.cafe.member.model.dto.response;

import com.project.cafe.exception.list.NoMemberInfoException;

public class MemberInfoResponseDto {
    private String memberId;
    private String memberName;
    private String memberEmail;

    public MemberInfoResponseDto() {
    }

    public MemberInfoResponseDto(String memberId, String memberName, String memberEmail) throws NoMemberInfoException {
        setMemberId(memberId);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws NoMemberInfoException {
        if(!(memberId==null||memberId.trim().equals(""))){
            this.memberId=memberId;
        }
        else{
            throw new NoMemberInfoException("아이디를 받아오지 못했습니다.");
        }
    }

    public String getMemberName(){
        return memberName;
    }

    public void setMemberName(String memberName) throws NoMemberInfoException {
        if(!(memberName==null||memberName.trim().equals(""))){
            this.memberName=memberName;
        }
        else{
            throw new NoMemberInfoException("이름을 받아오지 못했습니다.");
        }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) throws NoMemberInfoException {
        if(!(memberEmail==null||memberEmail.trim().equals(""))){
            this.memberEmail=memberEmail;
        }
        else{
            throw new NoMemberInfoException("이메일을 받아오지 못했습니다.");
        }
    }

    @Override
    public String toString() {
        return "MemberInfoResponseDto{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
