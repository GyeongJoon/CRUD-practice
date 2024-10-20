package com.example.crud.prac2.Controller;

import com.example.crud.prac2.domain.dto.CommentRequestDto;
import com.example.crud.prac2.domain.dto.CommentResponseDto;
import com.example.crud.prac2.domain.enums.Department;
import com.example.crud.prac2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/{boardId}/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회 (전체)
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentAll(@PathVariable Long boardId) {
        List<CommentResponseDto> commentAll = commentService.getCommentAll(boardId);
        return ResponseEntity.ok(commentAll);
    }

    // 댓글 등록
    @PostMapping
    public ResponseEntity<String> createComment(@RequestParam Long memberId,
                                                @PathVariable Long boardId,
                                                @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(memberId, boardId, commentRequestDto);
        return ResponseEntity.ok("댓글이 등록되었습니다.");
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@RequestParam Long memberId,
                                                @PathVariable Long boardId,
                                                @PathVariable Long commentId,
                                                @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(memberId, boardId, commentId, commentRequestDto);
        return ResponseEntity.ok("해당 댓글이 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@RequestParam Long memberId,
                                                @PathVariable Long boardId,
                                                @PathVariable Long commentId){
        commentService.deleteComment(memberId, boardId, commentId);
        return ResponseEntity.ok("해당 댓글이 삭제되었습니다.");
    }

    // 댓글 조회
    @GetMapping("/asdf")
    public ResponseEntity<Page<CommentResponseDto>> getCommentsCreateAts(@PathVariable Long boardId,
                                                                        @RequestParam int page,
                                                                        @RequestParam int size) {
        Page<CommentResponseDto> commentsCreateAt = commentService.getComments(boardId, page, size);
        return ResponseEntity.ok(commentsCreateAt);
    }

    // 댓글 조회 (최신순)
    @GetMapping("/createdAt") // ("/api/board/{boardId}/comment")
    public ResponseEntity<Page<CommentResponseDto>> getCommentsCreateAt(@PathVariable Long boardId,
                                                                        @RequestParam int page,
                                                                        @RequestParam int size) {
        Page<CommentResponseDto> commentsCreateAt = commentService.getCommentsCreateAt(boardId, page, size);
        return ResponseEntity.ok(commentsCreateAt);
    }

    // 댓글 조회 (부서별 + 최신순)
    @GetMapping("/department")
    public ResponseEntity<Page<CommentResponseDto>> getCommentsByDepartmentName(@PathVariable Long boardId,
                                                                                @RequestParam Department department,
                                                                                @RequestParam int page,
                                                                                @RequestParam int size) {
        Page<CommentResponseDto> commentsByDepartmentName = commentService.getCommentsByDepartmentName(boardId, department, page, size);
        return ResponseEntity.ok(commentsByDepartmentName);
    }
}
