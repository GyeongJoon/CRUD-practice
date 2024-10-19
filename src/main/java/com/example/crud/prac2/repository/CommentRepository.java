package com.example.crud.prac2.repository;

import com.example.crud.prac2.domain.entity.Comment;
import com.example.crud.prac2.domain.enums.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 최신순(작성시간으로 내림차순)
    @Query("SELECT c FROM Comment c WHERE c.board.id = :boardId ORDER BY c.createdAt DESC")
    Page<Comment> findAllByOrderByCreatedAtDesc(@Param("boardId") Long boardId, Pageable pageable);

    // 부서별 최신순 조회
    @Query("SELECT c FROM Comment c WHERE c.board.id = :boardId AND c.member.department = :department ORDER BY c.createdAt DESC")
    Page<Comment> findAllByMember_DepartmentOrderByCreatedAtDesc(@Param("boardId") Long boardId, @Param("department") Department department, Pageable pageable);
}

