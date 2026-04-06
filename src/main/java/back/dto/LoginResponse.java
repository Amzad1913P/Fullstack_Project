package back.dto;

public class LoginResponse {

    private boolean success;
    private String message;
    private Long userId;
    private String name;
    private String role;
    private String token;

    // ======================
    // Constructor: basic failure
    // ======================
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // ======================
    // Constructor: full success
    // ======================
    public LoginResponse(boolean success, String message,
                         Long userId, String name, String role, String token) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.token = token;
    }

    // ======================
    // Getters and Setters
    // ======================

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}