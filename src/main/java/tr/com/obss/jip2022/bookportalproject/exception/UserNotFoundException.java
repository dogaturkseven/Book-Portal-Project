package tr.com.obss.jip2022.bookportalproject.exception;


public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User does not exist!");
    }

    public UserNotFoundException(String s)
    {
        super(s);
    }
}
