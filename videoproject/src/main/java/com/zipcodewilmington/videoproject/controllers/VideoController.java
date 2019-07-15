package com.zipcodewilmington.videoproject.controllers;

import com.zipcodewilmington.videoproject.models.Video;
import com.zipcodewilmington.videoproject.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/videos")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    private VideoService service;

    @Autowired
    public VideoController(VideoService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Video>> index(){
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }


    @PostMapping("uploadFile")
    public Video uploadFile(@RequestParam("file") MultipartFile file) {
        Video video = service.storeVideo(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("videos/")
                .path(String.valueOf(video.getVideoId()))
                .toUriString();
        video.setVideoPath(fileDownloadUri);
        update(video.getVideoId(), video);
        return video;
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Video> create(@RequestBody Video video) {
        return new ResponseEntity<>(service.create(video), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Video> update(@PathVariable Long id, @RequestBody Video video) {
        return new ResponseEntity<>(service.update(id, video), HttpStatus.OK);
    }



    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        Video dbFile = service.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getVideoType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getVideoName() + "\"")
                .body(new ByteArrayResource(dbFile.getVideoBytes()));
    }








}
