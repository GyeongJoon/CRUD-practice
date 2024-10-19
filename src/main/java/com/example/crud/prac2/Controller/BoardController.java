package com.example.crud.prac2.Controller;

import com.example.crud.prac2.domain.dto.BoardRequestDto;
import com.example.crud.prac2.domain.dto.BoardResponseDto;
import com.example.crud.prac2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 조회 (전체)
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoardAll() {
        List<BoardResponseDto> boardAll = boardService.getBoardAll();
        return ResponseEntity.ok(boardAll);
    }

    // 게시물 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoardId(@PathVariable Long boardId) {
        BoardResponseDto responseDto = boardService.getBoardId(boardId);
        return ResponseEntity.ok(responseDto);
    }

    // 게시물 등록
    @PostMapping
    public ResponseEntity<String> createBoard(@RequestParam Long memberId,
                                              @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto responseDto = boardService.createBoard(memberId, boardRequestDto);
        return ResponseEntity.ok(responseDto.getTitle() + "게시물이 등록되었습니다.");
    }

    // 게시물 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@RequestParam Long memberId,
                                              @PathVariable Long boardId,
                                              @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto responseDto = boardService.updateBoard(memberId, boardId, boardRequestDto);
        return ResponseEntity.ok(responseDto.getTitle() + "게시물이 수정되었습니다.");
    }

    // 게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@RequestParam Long memberId,
                                              @PathVariable Long boardId) {
        boardService.deleteBoard(memberId, boardId);
        return ResponseEntity.ok("해당 게시물이 삭제되었습니다.");
    }
}
