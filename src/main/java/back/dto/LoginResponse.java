package back.dto;

public class LoginResponse {

    private boolean success;
    private String message;
    private Long studentId;
    private String name;

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(boolean success, String message,
                         Long studentId, String name) {
        this.success = success;
        this.message = message;
        this.studentId = studentId;
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}