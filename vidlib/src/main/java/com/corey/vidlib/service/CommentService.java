package com.corey.vidlib.service;

import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.entity.Video;

import java.util.List;

public interface CommentService {

    public List<Comment> findAll();

    public Comment findByCommentNumber(int commentNumber);

    public Comment findByEntityId(int entityId);

    public Comment findByUserId(int userId);

    public void save(Comment theComment);

    public void deleteByCommentNumber(int commentNumber);

}