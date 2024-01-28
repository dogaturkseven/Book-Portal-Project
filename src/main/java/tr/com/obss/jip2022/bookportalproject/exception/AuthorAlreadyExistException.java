package tr.com.obss.jip2022.bookportalproject.exception;

public class AuthorAlreadyExistException extends BaseException{

    public AuthorAlreadyExistException(){}

    public AuthorAlreadyExistException(String name)
    {
        super(String.format("%s username already exists", name));
    }
}
