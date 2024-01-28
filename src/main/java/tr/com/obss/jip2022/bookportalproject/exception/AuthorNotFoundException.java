package tr.com.obss.jip2022.bookportalproject.exception;

public class AuthorNotFoundException extends BaseException{
    public  AuthorNotFoundException(){

    }
    public AuthorNotFoundException(String s) {
        super(s);
    }
}
