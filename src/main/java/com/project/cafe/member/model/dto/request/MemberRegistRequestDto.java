package com.project.cafe.member.model.dto.request;

import com.project.cafe.exception.list.NoMemberRegistException;

import java.util.regex.Pattern;

public class MemberRegistRequestDto {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberEmail;

    public MemberRegistRequestDto() { }

    public MemberRegistRequestDto(String memberId, String memberPwd, String memberName, String memberEmail) throws NoMemberRegistException {
        setMemberId(memberId);
        setMemberPwd(memberPwd);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
    }

    /*
    공통되게 공백 허용하지 않음
    */
    /*
    태그 필터링 필요!
        inputString.replace( /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, "");
        sanitizedString = sanitizedString.replace(/</g, "&lt;").replace(/>/g, "&gt;");
     */

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws NoMemberRegistException {
        //5자리 이상인지 확인
        if(!(memberId==null||memberId.trim().equals(""))&&memberId.length()>=5){
            this.memberId=memberId;
        }
        else{
            throw new NoMemberRegistException("아이디 조건에 맞지 않습니다.");
        }
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) throws NoMemberRegistException {
        //아이디와 3자리 이상 중복되는지 확인
        boolean isContain=false;
        for(int len=3;len<this.memberId.length();len++){
            for(int start=0;start<this.memberId.length()-len;start++){
                String subStr=this.memberId.substring(start,start+len);

                if(memberPwd.contains(subStr)){
                   isContain=true;
                   break;
                }
            }
        }

        /*
                1. 대문자/소문자/숫자/기호 각각 최소 1개 이상 포함
                2. 10자 이상의 길이
                3. 연속된 숫자 x
         */
        String regex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])(?!.*(\\d)\\1{3})(?!.*(\\d+)(\\d)\\2{2})[A-Za-z\\d@$!%*?&]{10,}$";

        if(!(memberPwd==null||memberPwd.trim().equals("")||isContain)&& Pattern.matches(regex,memberPwd)){
            this.memberPwd=memberPwd;
        }
        else{
            throw new NoMemberRegistException("비밀번호 조건에 맞지 않습니다.");
        }
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) throws NoMemberRegistException {
       if(!(memberName==null||memberName.trim().equals(""))){
           this.memberName=memberName;
       }
       else{
           throw new NoMemberRegistException("이름 조건에 맞지 않습니다.");
       }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) throws NoMemberRegistException {
        //이메일 형식인지 확인
        String regex="^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        if(!(memberEmail==null||memberEmail.trim().equals(""))&&Pattern.matches(regex,memberEmail)){
            this.memberEmail=memberEmail;
        }
        else{
            throw new NoMemberRegistException("이메일 조건에 맞지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return "MemberRegistDTO{" +
                "memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}