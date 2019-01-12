package com.corey.vidlib.rest;

import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.entity.CommentID;
import com.corey.vidlib.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // Get all comments - GET /api/comments

    @GetMapping("/comments")
    public List<Comment> findAll(){
        return commentService.findAll();
    }

    // Get all comments that contain the given entityId - GET /api/{entityId}/comments

    @GetMapping("/{entityId}/comments")
    public List<Comment> findCommentsByEntityId(@PathVariable int entityId){
        return commentService.findAll()
                                .stream()
                                .filter(p ->  p.getEntityID() == entityId)
                                .collect(Collectors.toList());
    }

    // Get all comments that contain the given userId - GET /api/user/{userId}/comments

    @GetMapping("/user/{userId}/comments")
    public List<Comment> findCommentsByUserId(@PathVariable int userId){
        return commentService.findAll()
                .stream()
                .filter(p ->  p.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    // Get comment by comment number - GET /api/comments/{commentNumber}

    @GetMapping("/comments/{commentNumber}")
    public Comment findCommentByCommentNumber (@PathVariable("commentNumber") Integer commentId){
        return commentService.findByCommentNumber(commentId);
    }

    // Add comment - POST /api/comments

    @PostMapping("/comments")
    public Comment addComments(@RequestBody Comment theComment) {

        // Just in case they pass an id in JSON set id to 0
        // To force a save of new item instead of update

        theComment.setCommentNumber(0);
        System.out.println("In post: " + theComment);
        commentService.save(theComment);

        return theComment;

    }

    // Edit comment by comment number - PUT /api/comments/{commentNumber}

    @PutMapping("/comments/{commentNumber}")
    public Comment updateVideo(@PathVariable("commentNumber") Integer commentId, @RequestBody Comment commentBody) {


        Comment theComment = commentService.findByCommentNumber(commentId);

        theComment.setComment(commentBody.getComment());

        commentService.save(theComment);

        return theComment;

    }

    // Delete comment by comment number - DELETE /api/comments/{commentNumber}

    @DeleteMapping("/comments/{commentNumber}")
    public String deleteComment(@PathVariable int commentNumber) {

        Comment tempComment = commentService.findByCommentNumber(commentNumber);

        // Throw exception if null

        if(tempComment == null) {
            throw new RuntimeException("Comment number is not found - " + commentNumber);
        }

        commentService.deleteByCommentNumber(commentNumber);

        return "Deleted comment number - " + commentNumber;

    }

}
