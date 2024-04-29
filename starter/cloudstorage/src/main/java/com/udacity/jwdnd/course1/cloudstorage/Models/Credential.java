package com.udacity.jwdnd.course1.cloudstorage.Models;

import javax.persistence.*;

@Entity
@Table( name = "CREDENTIALS")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credentialId")
    private Long credentialId;

    @Column(name = "url")
    private String url;

    @Column(name = "username")
    private String username;

    @Column(name = "key")
    private String key;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    public Credential(String url, String username, String key, String password,
                      User user){
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.user = user;

    }
    public Credential(){}
        // Getters and setters
        public Long getCredentialId() {
            return credentialId;
        }

        public void setCredentialId(Long credentialId) {
            this.credentialId = credentialId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }


}
