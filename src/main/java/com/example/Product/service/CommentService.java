package com.example.Product.service;

import com.example.Product.dto.CommentSaveRequest;
import com.example.Product.dto.CommentUpdateRequest;
import com.example.Product.dto.ProductSaveRequest;
import com.example.Product.entity.Comment;
import com.example.Product.entity.Product;
import com.example.Product.repository.CommentRepository;
import com.example.Product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    private final ProductRepository productRepository;

    private final GetEmailService getEmailService;

    //서비스단 메소드명 설정 필요

    public ResponseEntity addComment(CommentSaveRequest commentSaveRequest) {
        try {
            String writeuseremail = getEmailService.getEmail(commentSaveRequest.getJwt());
            Product commentsproduct = productRepository.findByProductId(commentSaveRequest.getProduct_id());
            Comment savecomment = new Comment(writeuseremail, commentSaveRequest.getComment_detail(),commentsproduct);
            commentRepository.save(savecomment);
            return ResponseEntity.ok().build();
        } catch(NullPointerException e) {
            log.info("null 반환");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity deleteComment(Long commentid)
    {
        try{
            Comment deletecomment = commentRepository.findByCommentId(commentid);
            commentRepository.delete(deletecomment);
            return ResponseEntity.ok().build() ;
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Comment>> findCommentsByProductid (Long productid)
    {
        return ResponseEntity.ok(commentRepository.findByProduct_ProductId(productid));
    }

    public ResponseEntity<Comment> findComment(Long commentid)
    {
        return ResponseEntity.ok(commentRepository.findByCommentId(commentid)) ;
    }

    public ResponseEntity updateComment(Long commentid, CommentUpdateRequest commentUpdateRequest)
    {
        String changecommentdetail = commentUpdateRequest.getComment_detail();

        try {
            commentRepository.updateComment(commentid,changecommentdetail);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e ){
            log.info("ohmyexception") ;
            log.info(e.getMessage()) ;
            return ResponseEntity.badRequest().build() ;

        }
    }
}
