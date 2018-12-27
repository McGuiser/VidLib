package com.corey.vidlib;

import com.corey.vidlib.entity.Video;

import java.util.List;

public class Library {

    public List<Video> videos;

    public Library() {
    }

    public Library(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
