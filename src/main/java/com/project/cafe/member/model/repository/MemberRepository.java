package com.project.cafe.member.model.repository;

import com.project.cafe.member.model.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO,String> {
    //이름 규칙을 따라 메소드를 커스텀할 경우 선언
}
