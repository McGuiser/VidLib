package com.corey.vidlib.entity;

import java.io.Serializable;

public class CommentID implements Serializable{

    private int entityID;
    private int commentNumber;

    public CommentID() {
    }

    public CommentID(int entityID, int commentNumber) {
        this.entityID = entityID;
        this.commentNumber = commentNumber;
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
}
