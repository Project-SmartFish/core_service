package smartfish.modules.fishing_event.domain.exception;

import smartfish.modules.fishing_event.application.exception.FishingEventNotFoundException;

public class FishingEventNotOpenException extends RuntimeException {
    public FishingEventNotOpenException(String message) {
        super(message);
    }

    public FishingEventNotOpenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
