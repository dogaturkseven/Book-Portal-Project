package tr.com.obss.jip2022.bookportalproject.exception;

public class BaseException extends RuntimeException{
    public BaseException(){}

    public BaseException(String message)
    {
        super(message);
    }

    public BaseException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
