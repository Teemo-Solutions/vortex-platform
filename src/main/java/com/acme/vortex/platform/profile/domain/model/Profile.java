package com.acme.vortex.platform.profile.domain.model;

public class Profile {
    private Long id;
    private String username;
    private String email;
    private String biography;
    private String avatarUrl;

    // Constructor
    public Profile(String username, String email, String biography, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.biography = biography;
        this.avatarUrl = avatarUrl;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}