package com.corey.vidlib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="video")
public class Video {

    // Define fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="creator")
    private String creator;

    @Column(name="date")
    private String date;

    @Column(name="video_source")
    private String videoSource;

    @Column(name="thumbnail_source")
    private String thumbnailSource;

    // Define constructors

    public Video() {
    }

    public Video(String name, String creator, String date, String videoSource, String thumbnailSource) {
        this.name = name;
        this.creator = creator;
        this.date = date;
        this.videoSource = videoSource;
        this.thumbnailSource = thumbnailSource;
    }

    // Define getters/setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getThumbnailSource() {
        return thumbnailSource;
    }

    public void setThumbnailSource(String thumbnailSource) {
        this.thumbnailSource = thumbnailSource;
    }

    // Define toString

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", date=" + date +
                ", videoSource='" + videoSource + '\'' +
                ", thumbnailSource=" + thumbnailSource +
                '}';
    }
}

