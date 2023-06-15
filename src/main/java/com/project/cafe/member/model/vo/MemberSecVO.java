package com.project.cafe.member.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//유효성 검사(null 어노테이션)
@Entity(name="memberSec")
public class MemberSecVO {

    @Id
    @Column(name="memberId", nullable = false, length = 20)
    private String memberId;

    @Column(name="salt", nullable = false, length = 500)
    private String salt;

    public MemberSecVO() {
    }

    public MemberSecVO(String memberId, String salt) {
        setMemberId(memberId);
        setSalt(salt);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
