package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Models.File;
import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private HomeService homeService;

    // Endpoints for files
    @GetMapping("/files")
    public List<File> getAllFiles() {
        return homeService.getAllFiles();
    }

    @PostMapping("/files/upload")
    public File uploadFile(@RequestBody File file) {
        return homeService.uploadFile(file);
    }

    @DeleteMapping("/files/{fileId}")
    public String deleteFile(@PathVariable Long fileId) {
        return homeService.deleteFile(fileId);
    }

    // Endpoints for credentials
    @GetMapping("/credentials")
    public List<Credential> getAllCredentials() {
        return homeService.getAllCredentials();
    }

    @PostMapping("/credentials")
    public Credential addCredential(@RequestBody Credential credential) {
        return homeService.addCredential(credential);
    }

    @DeleteMapping("/credentials/{credentialId}")
    public String deleteCredential(@PathVariable Long credentialId) {
        return homeService.deleteCredential(credentialId);
    }

    // Endpoints for notes
    @GetMapping("/notes")
    public List<Notes> getAllNotes() {
        return homeService.getAllNotes();
    }

    @PostMapping("/notes")
    public Notes addNote(@RequestBody Notes note) {
        return homeService.addNote(note);
    }

    @DeleteMapping("/notes/{noteId}")
    public String deleteNote(@PathVariable Long noteId) {
        return homeService.deleteNote(noteId);
    }
}
