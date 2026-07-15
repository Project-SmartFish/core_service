package smartfish.modules.fishing_event.application.exception;

public class FishingEventNotFoundException extends RuntimeException {
    public FishingEventNotFoundException(String message) {
        super(message);
    }

    public FishingEventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
