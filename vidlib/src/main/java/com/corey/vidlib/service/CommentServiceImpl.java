package com.corey.vidlib.service;

import com.corey.vidlib.dao.CommentDao;
import com.corey.vidlib.dao.VideoDAO;
import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao theCommentDao) {
        commentDao = theCommentDao;
    }


    @Override
    @Transactional
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment findByCommentNumber(int commentNumber) { return commentDao.findByCommentNumber(commentNumber); }

    @Override
    @Transactional
    public Comment findByEntityId(int entityId) {
        return commentDao.findByEntityId(entityId);
    }

    @Override
    @Transactional
    public Comment findByUserId(int userId) {
        return commentDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public void save(Comment theComment) {
        commentDao.save(theComment);
    }

    @Override
    @Transactional
    public void deleteByCommentNumber(int commentNumber) {
        commentDao.deleteByCommentNumber(commentNumber);
    }

}
