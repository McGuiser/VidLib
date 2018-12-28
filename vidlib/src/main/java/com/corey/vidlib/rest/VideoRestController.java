package com.corey.vidlib.rest;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.Region;
import com.corey.vidlib.config.AWSKeys;
import com.corey.vidlib.entity.Video;
import com.corey.vidlib.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/secure")
    public List<String> generateSignedURL(@RequestParam("fileExtension") String fileExtension){
        String bucketName = "vidlib.io";

        Random random = new Random(System.nanoTime());
        String objectKey = String.format("%09d", random.nextInt(1000000000)) + fileExtension;



        try {
            BasicAWSCredentials credits = new BasicAWSCredentials(

                    // Access key            Secret Access Key
                    AWSKeys.ACCESS_KEY, AWSKeys.SECRET_KEY

            );

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.US_WEST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(credits))
                    .build();

            // Set the pre-signed URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            // Generate the pre-signed URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);

            generatePresignedUrlRequest.addRequestParameter(
                    Headers.S3_CANNED_ACL,
                    CannedAccessControlList.PublicRead.toString()
            );

            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            List<String> secureList = new ArrayList<>();
            secureList.add(url.toString());
            secureList.add(objectKey);
            return secureList;
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }

        return null;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/videos")
    public Video addVideo(@RequestBody Video theVideo) {

        // Just in case they pass an id in JSON set id to 0
        // To force a save of new item instead of update

        theVideo.setId(0);
        System.out.println("In post: " + theVideo);
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
