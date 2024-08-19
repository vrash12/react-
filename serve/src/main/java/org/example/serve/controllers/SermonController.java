package org.example.serve.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.example.serve.model.Sermon;
import org.example.serve.service.SermonService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sermons")
public class SermonController {

    @Autowired
    private SermonService sermonService;

    @GetMapping
    public List<Sermon> getAllSermons() {
        return sermonService.getAllSermons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sermon> getSermonById(@PathVariable Long id) {
        Optional<Sermon> sermon = sermonService.getSermonById(id);
        return sermon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Sermon createSermon(@RequestBody Sermon sermon) {
        return sermonService.saveSermon(sermon);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Sermon> updateSermon(@PathVariable Long id, @RequestBody Sermon updatedSermon) {
        Optional<Sermon> sermonOptional = sermonService.getSermonById(id);

        if (sermonOptional.isPresent()) {
            Sermon sermon = sermonOptional.get();
            sermon.setTitle(updatedSermon.getTitle());
            sermon.setDescription(updatedSermon.getDescription());
            sermon.setVideoUrl(updatedSermon.getVideoUrl());
            sermon.setAudioUrl(updatedSermon.getAudioUrl());
            return ResponseEntity.ok(sermonService.saveSermon(sermon));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSermon(@PathVariable Long id) {
        sermonService.deleteSermon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<String> uploadSermon(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("video") MultipartFile video,
            @RequestParam("audio") MultipartFile audio) {

        // Here you would save the files to your storage and save metadata to the database
        // Example:
        String videoUrl = saveFile(video);
        String audioUrl = saveFile(audio);

        // Save the sermon metadata to the database
        Sermon sermon = new Sermon();
        sermon.setTitle(title);
        sermon.setDescription(description);
        sermon.setVideoUrl(videoUrl);
        sermon.setAudioUrl(audioUrl);

        sermonService.saveSermon(sermon);

        return ResponseEntity.ok("Sermon uploaded successfully");
    }

    private String saveFile(MultipartFile file) {
        // Implement your file saving logic here, returning the URL or path to the file
        return "/path/to/" + file.getOriginalFilename();
    }
}
