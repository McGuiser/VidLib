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
}
