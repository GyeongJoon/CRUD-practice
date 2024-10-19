package com.example.crud.prac2.service;

import com.example.crud.prac2.domain.dto.CommentRequestDto;
import com.example.crud.prac2.domain.dto.CommentResponseDto;
import com.example.crud.prac2.domain.entity.Board;
import com.example.crud.prac2.domain.entity.Comment;
import com.example.crud.prac2.domain.entity.Member;
import com.example.crud.prac2.domain.enums.Department;
import com.example.crud.prac2.domain.mapper.CommentMapper;
import com.example.crud.prac2.exception.ErrorCode;
import com.example.crud.prac2.exception.appException;
import com.example.crud.prac2.repository.BoardRepository;
import com.example.crud.prac2.repository.CommentRepository;
import com.example.crud.prac2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    // 댓글 조회 API (전체)
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentAll(Long boardId) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        List<Comment> commentList = commentRepository.findAll();
        return CommentMapper.toCommentAllDto(commentList);
    }

    // 댓글 등록
    @Transactional
    public CommentResponseDto createComment(Long memberId, Long boardId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        Comment commentEntity = CommentMapper.toCommentEntity(member, board, commentRequestDto);
        commentRepository.save(commentEntity);
        return CommentMapper.toCommentDto(commentEntity);
    }

    // 댓글 수정
    @Transactional
        public CommentResponseDto updateComment(Long memberId, Long boardId, Long commentId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new appException(ErrorCode.COMMENT_NOT_FOUND));

        if (!member.getId().equals(comment.getMember().getId())){
            throw new appException(ErrorCode.COMMENT_UPDATE_PERMISSION_DENIED);
        }

        comment.updateCommentInfo(commentRequestDto);
        commentRepository.save(comment);
        return CommentMapper.toCommentDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long memberId, Long boardId, Long commentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new appException(ErrorCode.COMMENT_NOT_FOUND));

        if (!member.getId().equals(comment.getMember().getId())){
            throw new appException(ErrorCode.COMMENT_UPDATE_PERMISSION_DENIED);
        }

        commentRepository.delete(comment);
    }

    // 전체 댓글 조회(페이징 이용)
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getComments(Long boardId, int page, int size) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> allComments = commentRepository.findAllById(boardId, pageable);
        return CommentMapper.toCommentDtoPage(allComments);
    }

    // 댓글 조회 (최신순)
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getCommentsCreateAt(Long boardId, int page, int size) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));


        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> allByOrderByCreatedAtDesc = commentRepository.findAllByOrderByCreatedAtDesc(boardId, pageable);
        return CommentMapper.toCommentDtoPage(allByOrderByCreatedAtDesc);
    }

    // 댓글 조회 (부서별 + 최신순)
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getCommentsByDepartmentName(Long boardId, Department department, int page, int size) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        if (department == null) {
            throw new appException(ErrorCode.INVALID_DEPARTMENT_NAME);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> allByDepartmentOrderByCreatedAtDesc = commentRepository.findAllByMember_DepartmentOrderByCreatedAtDesc(boardId, department, pageable);
        return CommentMapper.toCommentDtoPage(allByDepartmentOrderByCreatedAtDesc);
    }

}
