package com.corey.vidlib.service;

import com.corey.vidlib.entity.Video;

import java.util.List;

public interface VideoService {

    public List<Video> findAll();

    public Video findById(int theId);

    public void save(Video theVideo);

    public void deleteById(int theId);

}
