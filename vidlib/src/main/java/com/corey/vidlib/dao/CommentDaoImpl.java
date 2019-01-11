package com.corey.vidlib.dao;

import com.corey.vidlib.entity.Comment;
import com.corey.vidlib.entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{

    // Define field for entitymanager

    private EntityManager entityManager;

    // Set up constructor injection

    @Autowired
    public CommentDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Comment> findAll() {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Create a query

        Query<Comment> theQuery = currentSession.createQuery("from Comment", Comment.class);

        // Execute query and get result list

        List<Comment> comments = theQuery.getResultList();

        // return the results

        return comments;
    }

    @Override
    public Comment findByCommentNumber(int commentNumber) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Get the video

        Comment theComment = currentSession.get(Comment.class, commentNumber);

        // Return the video

        return theComment;
    }

    @Override
    public Comment findByEntityId(int theId) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Get the video

        Comment theComment = currentSession.get(Comment.class, theId);

        // Return the video

        return theComment;
    }

    @Override
    public void save(Comment theComment) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Save video

        currentSession.saveOrUpdate(theComment);

    }

    @Override
    public void deleteByCommentNumber(int commentNumber) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Delete object with primary key

        Query theQuery = currentSession.createQuery("delete from Comment where commentNumber=:commentNumber");
        theQuery.setParameter("commentNumber", commentNumber);

        theQuery.executeUpdate();
    }
}
