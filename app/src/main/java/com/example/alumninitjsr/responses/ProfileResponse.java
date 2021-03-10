package com.example.alumninitjsr.responses;

import com.example.alumninitjsr.models.UserProfile;

public class ProfileResponse {
    int status;
    String message;
    UserProfile response;

    public ProfileResponse() {
    }

    public ProfileResponse(int status, String message, UserProfile response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfile getResponse() {
        return response;
    }

    public void setResponse(UserProfile response) {
        this.response = response;
    }
}
