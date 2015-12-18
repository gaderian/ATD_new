package modell;

/**
 * Class:       WrongClassTypeException
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        12/4/15
 */
public class WrongClassTypeException extends Throwable {

    /**
     * Constructor setting a message.
     * @param message a message held by the exception.
     */
    public WrongClassTypeException(String message) {
        super(message);
    }
}
