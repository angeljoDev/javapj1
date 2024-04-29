package com.udacity.jwdnd.course1.cloudstorage.Models;


import javax.persistence.*;

@Entity
@Table(name="files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String filename;
    private String contentType;
    private Long fileSize;
    private byte[] fileData;

    public File(){

    }

    public File(String filename, String contentType, Long fileSize, byte[] fileData){
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public Long getFileId(){return fileId;}

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFilename(){ return filename;}

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType(){return contentType;}

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public Long getFileSize(){
        return  fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
