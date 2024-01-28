package tr.com.obss.jip2022.bookportalproject.exception;

public class BookAlreadyInListException extends BaseException{
    public BookAlreadyInListException(){}

    public BookAlreadyInListException(String bookTitle)
    {
        super(String.format("%s already exists in the list!",bookTitle));
    }
}
