package smartfish.modules.user_fishing_event.application.exception;

public class UserNotParticipatingException extends RuntimeException {
    public UserNotParticipatingException(String message) {
        super(message);
    }

    public UserNotParticipatingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
