package tr.com.obss.jip2022.bookportalproject.exception;

public class BookNotFoundException extends BaseException{
    public BookNotFoundException() {
        super("This book does not exist!");
    }
}
