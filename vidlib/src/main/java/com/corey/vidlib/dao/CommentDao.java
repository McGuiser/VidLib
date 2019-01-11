package com.corey.vidlib.dao;

import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.entity.Video;

import java.util.List;

public interface CommentDao {

    public List<Comment> findAll();

    public Comment findByCommentNumber(int commentNumber);

    public Comment findByEntityId(int entityId);

    public void save(Comment theComment);

    public void deleteByCommentNumber(int commentNumber);

}