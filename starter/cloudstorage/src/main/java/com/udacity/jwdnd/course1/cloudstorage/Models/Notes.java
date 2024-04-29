package com.udacity.jwdnd.course1.cloudstorage.Models;

import javax.persistence.*;

@Entity
@Table(name = "NOTES")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteid")
    private Long noteId;

    @Column(name = "notetitle")
    private String noteTitle;

    @Column(name = "notedescription", length = 1000)
    private String noteDescription;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Notes(String noteTitle, String noteDescription, User user){
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.user = user;
    }

    public Notes(){}
        // Getters and setters
        public Long getNoteId() {
            return noteId;
        }

        public void setNoteId(Long noteId) {
            this.noteId = noteId;
        }

        public String getNoteTitle() {
            return noteTitle;
        }

        public void setNoteTitle(String noteTitle) {
            this.noteTitle = noteTitle;
        }

        public String getNoteDescription() {
            return noteDescription;
        }

        public void setNoteDescription(String noteDescription) {
            this.noteDescription = noteDescription;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }



}
