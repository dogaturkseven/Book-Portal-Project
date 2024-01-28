package tr.com.obss.jip2022.bookportalproject.exception;

import javax.validation.constraints.NotNull;

public class UserNameAlreadyExistException extends BaseException {
    public UserNameAlreadyExistException(){}

    public UserNameAlreadyExistException(String username)
    {
        super(String.format("%s username already exists",username));
    }
}
