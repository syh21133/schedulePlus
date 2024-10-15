package com.sparta.scheduleplus.service;

import com.sparta.scheduleplus.dto.CommentRequestDto;
import com.sparta.scheduleplus.dto.CommentResponseDto;
import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.entity.Comment;
import com.sparta.scheduleplus.entity.Schedule;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.repository.CommentRepository;
import com.sparta.scheduleplus.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    public String createComment(Long scheduleId, CommentRequestDto dto, Long userId) {
        User user = userService.findUser(userId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
                new RuntimeException("Schedule not found"));

        Comment comment = new Comment(dto.getText(),schedule,user);
        schedule.getCommentList().add(comment);
        commentRepository.save(comment);
        return "댓글 작성";
    }


    public List<Comment> listComment(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId);

    }

    public String updateComment(Long userId, CommentRequestDto dto, Long id) {
        User user = userService.findUser(userId);
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Comment not found"));

        if(!user.getId().equals(comment.getUser().getId())) return "작성한 사용자가 아닙니다";
        comment.setText(dto.getText());
        commentRepository.save(comment);

        return "댓글 수정";

    }

    public String deleteComment(Long userId, Long commentId) {
        User user = userService.findUser(userId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new RuntimeException("Comment not found"));

        if(!user.getId().equals(comment.getUser().getId())) return "작성한 사용자가 아닙니다";

        commentRepository.delete(comment);

        return "댓글 삭제";
    }
}
