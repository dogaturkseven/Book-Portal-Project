package tr.com.obss.jip2022.bookportalproject.service;

import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto findByTitle(String title);

    int getAuthorsBookCount(String name);

    void addNewBook(BookDto bookDto);

    void deleteBook(String title); //title yerine başka bir şey de olabilir

    void updateBookTitle(String title, String updatedTitle); //bu da değişebilir

    List<BookDto> searchBooks(String searchedWord);

    List<BookDto> searchBookByGenre(String genre);

    void updateBookAuthor(String authorName, String updatedName);

    List<User> findUsersByBookId(long id);

}
