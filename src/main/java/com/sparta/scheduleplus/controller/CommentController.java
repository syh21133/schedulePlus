package com.sparta.scheduleplus.controller;

import com.sparta.scheduleplus.dto.CommentRequestDto;
import com.sparta.scheduleplus.dto.CommentResponseDto;
import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write/{scheduleId}")
    public String createComment(@PathVariable Long scheduleId, @Valid CommentRequestDto dto,
                                @RequestAttribute("userId") Long userId) {

        return commentService.createComment(scheduleId,dto,userId);
    }

    @GetMapping("/list/{scheduleId}")
    public List<CommentResponseDto> listComment(@PathVariable Long scheduleId) {

        return commentService.listComment(scheduleId).stream().map(CommentResponseDto::new).toList();
    }

    @PutMapping("/update/{commentId}")
    public String updateComment(@RequestAttribute("userId") Long userId,@Valid CommentRequestDto dto,
                                 @PathVariable Long commentId) {

        return commentService.updateComment(userId,dto,commentId);
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteSchedule(@RequestAttribute("userId") Long userId,@PathVariable Long commentId) {

        return commentService.deleteComment(userId,commentId);
    }





}
