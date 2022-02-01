package project.automate_me.model;

public class SelfReviewResponse {
    private String message;
    public SelfReviewResponse(){

    }

    public SelfReviewResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
