package com.example.crud.prac2.service;

import com.example.crud.prac2.domain.dto.BoardRequestDto;
import com.example.crud.prac2.domain.dto.BoardResponseDto;
import com.example.crud.prac2.domain.entity.Board;
import com.example.crud.prac2.domain.entity.Member;
import com.example.crud.prac2.domain.mapper.BoardMapper;
import com.example.crud.prac2.exception.ErrorCode;
import com.example.crud.prac2.exception.appException;
import com.example.crud.prac2.repository.BoardRepository;
import com.example.crud.prac2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 게시물 조회 API (전체)
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardAll() {
        List<Board> boards = boardRepository.findAll();
        return BoardMapper.toBoardAllDto(boards);

    }

    // 게시물 조회 API
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        return BoardMapper.toBoardDto(board);
    }

    // 게시물 등록 API
    @Transactional
    public BoardResponseDto createBoard(Long memberId, BoardRequestDto boardRequestDto) {
        Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        Board boardEntity = BoardMapper.toBoardEntity(member, boardRequestDto);
        boardRepository.save(boardEntity);
        return BoardMapper.toBoardDto(boardEntity);
    }

    // 게시물 수정 API
    @Transactional
    public BoardResponseDto updateBoard(Long memberId, Long boardId, BoardRequestDto boardRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        if (member.getId() != board.getMember().getId()) {
            throw new appException(ErrorCode.BOARD_UPDATE_PERMISSION_DENIED);
        }

        board.updateBoardInfo(boardRequestDto);
        boardRepository.save(board);
        return BoardMapper.toBoardDto(board);

    }

    // 게시물 삭제 API
    @Transactional
    public void deleteBoard(Long memberId, Long boardId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new appException(ErrorCode.BOARD_NOT_FOUND));

        if (member.getId() != board.getMember().getId()) {
            throw new appException(ErrorCode.BOARD_DELETE_PERMISSION_DENIED);
        }
        boardRepository.delete(board);
    }
}
