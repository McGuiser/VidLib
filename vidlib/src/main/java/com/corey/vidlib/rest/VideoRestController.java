package com.corey.vidlib.rest;

import com.corey.vidlib.entity.Video;
import com.corey.vidlib.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoRestController {

    private VideoService videoService;

    @Autowired
    public VideoRestController(VideoService theVideoService) {
        videoService = theVideoService;
    }

    // Expose "/videos" and return list of videos

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/videos")
    public List<Video> findAll(){
        return videoService.findAll();
    }

    // Add mapping for GET /videos/{videoId}

    @GetMapping("/videos/{videoId}")
    public Video getVideo(@PathVariable int videoId) {

        Video theVideo = videoService.findById(videoId);

        if (theVideo == null) {
            throw new RuntimeException("Video id is not found - " + videoId);
        }

        return theVideo;

    }

    // Add mapping for POST /videos - add new video

    @PostMapping("/videos")
    public Video addVideo(@RequestBody Video theVideo) {

        // Just in case they pass an id in JSON set id to 0
        // To force a save of new item instead of update

        theVideo.setId(0);

        videoService.save(theVideo);

        return theVideo;

    }

    // Add mapping for PUT /videos - update existing video

    @PutMapping("/videos")
    public Video updateVideo(@RequestBody Video theVideo) {

        videoService.save(theVideo);

        return theVideo;

    }

    // Add mapping for DELETE /videos/{videoId} - delete video

    @DeleteMapping("/videos/{videoId}")
    public String deleteVideo(@PathVariable int videoId) {

        Video tempVideo = videoService.findById(videoId);

        // Throw exception if null

        if(tempVideo == null) {
            throw new RuntimeException("Video id is not found - " + videoId);
        }

        videoService.deleteById(videoId);

        return "Deleted video id - " + videoId;

    }

}
