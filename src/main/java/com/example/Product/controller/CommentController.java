package com.example.Product.controller;

import com.example.Product.dto.CommentSaveRequest;
import com.example.Product.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //    // 댓글 작성
    @CrossOrigin
    @PostMapping("/comment/create")
    public ResponseEntity savecomment(@RequestBody CommentSaveRequest commentSaveRequest)
    {
        return commentService.saveComment(commentSaveRequest) ;
    }
//
//    // 댓글 삭제
    @CrossOrigin
    @DeleteMapping("/comment/delete")
    public ResponseEntity deletecomment(@RequestHeader Long commentid)
    {
        return commentService.deleteComment(commentid);
    }
//
//    // 댓글 수정
//    @PutMapping
}
