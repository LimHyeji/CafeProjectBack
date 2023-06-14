package com.project.cafe.board.repository;

import com.project.cafe.board.vo.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardVO,String> {

}
