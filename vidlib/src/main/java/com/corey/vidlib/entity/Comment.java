package com.corey.vidlib.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@IdClass(CommentID.class)
@Table(name="entity_comment")
public class Comment implements Serializable {

    @Id
    @Column(name = "entity_id")
    private int entityID;

    @Id
    @Column(name = "comment_no")
    private int commentNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment")
    private String comment;

    public Comment() {
    }

    public Comment(int entityID, int commentNumber, User user, String comment) {
        this.entityID = entityID;
        this.commentNumber = commentNumber;
        this.user = user;
        this.comment = comment;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
