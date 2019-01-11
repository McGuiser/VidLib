package com.corey.vidlib.rest;

import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private CommentService commentService;

    @Autowired
    public CommentRestController(CommentService theCommentService) {
        commentService = theCommentService;
    }

    @GetMapping("/comments")
    public List<Comment> findAll(){
        return commentService.findAll();
    }

    @GetMapping("/{entityId}/comments")
    public List<Comment> findCommentsByEntityId(@PathVariable int entityId){
        return commentService.findAll()
                                .stream()
                                .filter(p ->  p.getEntityID() == entityId)
                                .collect(Collectors.toList());
    }

}
