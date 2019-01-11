package com.corey.vidlib.dao;

import com.corey.vidlib.entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoDAOImpl implements VideoDAO{

    // Define field for entitymanager

    private EntityManager entityManager;

    // Set up constructor injection

    @Autowired
    public VideoDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Video> findAll() {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Create a query

        Query<Video> theQuery = currentSession.createQuery("from Video", Video.class);

        // Execute query and get result list

        List<Video> video = theQuery.getResultList();

        // return the results

        return video;
    }

    @Override
    public Video findById(int theId) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Get the video

        Video theVideo = currentSession.get(Video.class, theId);

        // Return the video

        return theVideo;
    }

    @Override
    public void save(Video theVideo) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Save video

        currentSession.saveOrUpdate(theVideo);

    }

    @Override
    public void deleteById(int theId) {

        // Get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // Delete object with primary key

        Query theQuery = currentSession.createQuery("delete from Video where id=:videoId");
        theQuery.setParameter("videoId", theId);

        theQuery.executeUpdate();
    }
}
