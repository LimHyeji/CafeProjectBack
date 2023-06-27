package com.project.cafe.member.model.dto.request;

import com.project.cafe.exception.list.NoMemberModifyException;

import java.util.regex.Pattern;

public class MemberModifyRequestDto {
    private String currentPwd;
    private String newPwd;
    private String memberName;
    private String memberEmail;

    public MemberModifyRequestDto() {
    }

    public MemberModifyRequestDto(String currentPwd, String newPwd, String memberName, String memberEmail) throws NoMemberModifyException{
        setCurrentPwd(currentPwd);
        setNewPwd(newPwd);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
    }

    /*
    태그 필터링 필요!
    inputString.replace( /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, "");
    sanitizedString = sanitizedString.replace(/</g, "&lt;").replace(/>/g, "&gt;");
     */

    public String getCurrentPwd() {
        return currentPwd;
    }

    public void setCurrentPwd(String memberPwd) throws NoMemberModifyException{
        if(!(memberPwd==null||memberPwd.trim().equals(""))){
            this.currentPwd=memberPwd;
        }
        else{
            throw new NoMemberModifyException("현재 비밀번호가 입력되어야 합니다.");
        }
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String memberPwd) throws NoMemberModifyException{
        //아이디와 3자 이상 같은지 확인하는 유효성 검사는 'memberId를 확인하는 service단'에서 처리

        /*
                1. 대문자/소문자/숫자/기호 각각 최소 1개 이상 포함
                2. 10자 이상의 길이
                3. 연속된 숫자 x
         */
        String regex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])(?!.*(\\d)\\1{3})(?!.*(\\d+)(\\d)\\2{2})[A-Za-z\\d@$!%*?&]{10,}$";

        if(!(memberPwd==null||memberPwd.trim().equals(""))&& Pattern.matches(regex,memberPwd)){
            this.newPwd=memberPwd;
        }
        else{
            throw new NoMemberModifyException("비밀번호 조건에 맞지 않습니다.");
        }
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) throws NoMemberModifyException {
        if(!(memberName==null||memberName.trim().equals(""))){
            this.memberName=memberName;
        }
        else{
            throw new NoMemberModifyException("이름 조건에 맞지 않습니다.");
        }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) throws NoMemberModifyException {
        //이메일 형식인지 확인
        String regex="^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        if(!(memberEmail==null||memberEmail.trim().equals(""))&&Pattern.matches(regex,memberEmail)){
            this.memberEmail=memberEmail;
        }
        else{
            throw new NoMemberModifyException("이메일 조건에 맞지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return "MemberModifyRequestDto{" +
                "currentPwd='" + currentPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
