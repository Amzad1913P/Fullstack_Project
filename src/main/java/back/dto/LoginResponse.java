package back.dto;

public class LoginResponse {

    private boolean success;
    private String message;
    private Long userId;
    private String name;
    private String role;

    // ======================
    // Constructor: basic
    // ======================
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // ======================
    // Constructor: full
    // ======================
    public LoginResponse(boolean success, String message,
                         Long userId, String name, String role) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    // ======================
    // Getters
    // ======================

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}