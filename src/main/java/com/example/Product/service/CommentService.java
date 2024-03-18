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

    public ResponseEntity saveComment(CommentSaveRequest commentSaveRequest) {
        try {
            String writeuseremail = getEmailService.getemail(commentSaveRequest.getJwt());
            //이메일부 문제 없음

            log.info("productid = {}",commentSaveRequest.getProductid());
            Product commentsproduct = productRepository.findByProductId(commentSaveRequest.getProductid());
            //여기서 터지는중

            log.info("product={}",commentsproduct.getProductname());

            Comment savecomment = new Comment(writeuseremail, commentSaveRequest.getCommentdetail(),commentsproduct);
            log.info("savecomment ={}",savecomment);
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
        }catch(Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    public List<Comment> getallCommentByProductId (Long productid)
    {
        return commentRepository.findByProduct_ProductId(productid);
    }

    public Comment getCommentByCommentId(Long commentid)
    {
        return commentRepository.findByCommentId(commentid) ;
    }

    public ResponseEntity updateComment(Long commentid, CommentUpdateRequest commentUpdateRequest)
    {
        String changecommentdetail = commentUpdateRequest.getCommentdetail();

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
