package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Models.File;
import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private RestTemplate restTemplate;



    public List<File> getAllFiles(){
        return restTemplate.getForObject( "/files", List.class);
    }

    public File uploadFile(File file) {
        return restTemplate.postForObject( "/files/upload", file, File.class);
    }

    public String deleteFile(Long fileId) {
        restTemplate.delete( "/files/{fileId}", fileId);
        return "File deleted successfully";
    }
    public List<Credential> getAllCredentials() {
        return Arrays.asList(restTemplate.getForObject( "/credentials", Credential[].class));
    }

    public Credential addCredential(Credential credential) {
        return restTemplate.postForObject( "/credentials", credential, Credential.class);
    }

    public String deleteCredential(Long credentialId) {
        restTemplate.delete( "/credentials/{credentialId}", credentialId);
        return "Credential deleted successfully";
    }

    public List<Notes> getAllNotes() {
        return Arrays.asList(restTemplate.getForObject("/notes", Notes[].class));
    }

    public Notes addNote(Notes note) {
        return restTemplate.postForObject("/notes", note, Notes.class);
    }

    public String deleteNote(Long noteId) {
        restTemplate.delete( "/notes/{noteId}", noteId);
        return "Note deleted successfully";
    }
}
