package com.example.Product.controller;

import com.example.Product.dto.CommentSaveRequest;
import com.example.Product.dto.CommentUpdateRequest;
import com.example.Product.entity.Comment;
import com.example.Product.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //상품에 달린 댓글들가져오기
    @CrossOrigin
    @GetMapping("/comment/getall/{productid}")
    public List<Comment> getallcomment(@PathVariable("productid") Long productid)
    {
        return commentService.getallCommentByProductId(productid) ;
    }

    //댓글 1개 가져오기
    @CrossOrigin
    @GetMapping("/comment/getone/{commentid}")
    public Comment getcomment(@PathVariable("commentid") Long commentid)
    {
        return commentService.getCommentByCommentId(commentid) ;
    }
//
    // 댓글 수정
    @CrossOrigin
    @PutMapping("/comment/update/{commentid}")
    public ResponseEntity updatecomment(@PathVariable("commentid") Long commentid,
                                        @RequestBody CommentUpdateRequest commentUpdateRequest)
    {
        return commentService.updateComment(commentid, commentUpdateRequest) ;
    }
}
