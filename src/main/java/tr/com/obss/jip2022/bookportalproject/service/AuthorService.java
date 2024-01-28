package tr.com.obss.jip2022.bookportalproject.service;

import tr.com.obss.jip2022.bookportalproject.dto.AuthorDto;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.model.Book;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    AuthorDto findByName(String name);

    void addNewAuthor(AuthorDto authorDto);

    void addWrittenBook(String authorName, Book book);

    void deleteAuthor(String name);

    void updateAuthor(String authorName, String updatedName);

    void deleteUserWithBookFromAuthor(String authorName);

    List<BookDto> getBooksOfAuthor(String authorName);
    //update author yaz

}
