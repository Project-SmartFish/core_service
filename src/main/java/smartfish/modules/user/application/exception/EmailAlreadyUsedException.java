package smartfish.modules.user.application.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("O email já foi utilizado");
    }
}
