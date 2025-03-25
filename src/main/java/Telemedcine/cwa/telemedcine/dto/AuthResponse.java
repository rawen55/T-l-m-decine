package Telemedcine.cwa.telemedcine.dto;

public class AuthResponse {
    private String token;

    // Constructor with a String parameter
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter and Setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
