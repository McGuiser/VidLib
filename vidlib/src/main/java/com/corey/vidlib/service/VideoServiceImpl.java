package com.corey.vidlib.service;

import com.corey.vidlib.dao.VideoDAO;
import com.corey.vidlib.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    private VideoDAO videoDAO;

    @Autowired
    public VideoServiceImpl(VideoDAO theVideoDAP) {
        videoDAO = theVideoDAP;
    }


    @Override
    @Transactional
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    @Transactional
    public Video findById(int theId) {
        return videoDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Video theVideo) {
        videoDAO.save(theVideo);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        videoDAO.deleteById(theId);
    }

}
