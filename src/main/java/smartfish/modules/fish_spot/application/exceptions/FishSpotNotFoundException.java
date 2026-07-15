package smartfish.modules.fish_spot.application.exceptions;

public class FishSpotNotFoundException extends RuntimeException {
    public FishSpotNotFoundException(String message) {
        super(message);
    }
}
