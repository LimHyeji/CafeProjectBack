package com.project.cafe.member.model.vo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//MySQL 테이블 삭제!
//유효성 검사(null 어노테이션)
/*
외래키일 경우 @OneToOne, ...
 */
@Entity(name="member")
@DynamicInsert //insert 시에 null이 아닌 값만 적용되므로, null이 들어온 경우 명시된 기본값 적용
//@Table(name="member")
public class MemberVO {

    @Id
    @Column(name="memberId", nullable = false, length = 20)
    private String memberId;

    @Column(name="grade", length = 20)
    @ColumnDefault("'회원'")//null일 경우 '회원'으로 저장하며, '관리자'는 별도로 입력함
    private String grade;

    @Column(name="memberPwd", nullable = false, length = 500)
    private String memberPwd;

    @Column(name="memberName", nullable = false, length = 20)
    private String memberName;

    @Column(name="memberEmail", nullable = false, length = 40)
    private String memberEmail;

    @Column(name="loginCnt")
    @ColumnDefault("0")
    private int loginCnt;

    public MemberVO() {}

    public MemberVO(String memberId, String memberPwd, String memberName, String memberEmail) {
        setMemberId(memberId);
        setMemberPwd(memberPwd);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
    }

    public MemberVO(String memberId, String grade, String memberPwd, String memberName, String memberEmail, int loginCnt) {
        setMemberId(memberId);
        setGrade(grade);
        setMemberPwd(memberPwd);
        setMemberName(memberName);
        setMemberEmail(memberEmail);
        setLoginCnt(loginCnt);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public int getLoginCnt() {
        return loginCnt;
    }

    public void setLoginCnt(int loginCnt) {
        this.loginCnt = loginCnt;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "memberId='" + memberId + '\'' +
                ", grade='" + grade + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", loginCnt=" + loginCnt +
                '}';
    }
}
