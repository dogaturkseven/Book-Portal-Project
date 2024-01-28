package tr.com.obss.jip2022.bookportalproject.service.impl;

import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.exception.AuthorNotFoundException;
import tr.com.obss.jip2022.bookportalproject.exception.BookNotFoundException;
import tr.com.obss.jip2022.bookportalproject.model.Author;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.repository.AuthorRepository;
import tr.com.obss.jip2022.bookportalproject.repository.BookRepository;
import tr.com.obss.jip2022.bookportalproject.repository.UserRepository;
import tr.com.obss.jip2022.bookportalproject.service.AuthorService;
import tr.com.obss.jip2022.bookportalproject.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;

    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, UserRepository userRepository1) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.userRepository = userRepository1;
    }

    @Override
    public List<BookDto> getAllBooks() {
        final Iterable<Book> books = bookRepository.findAll();
        List<BookDto> allBooks = new ArrayList<>();
        books.forEach(book -> allBooks.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return allBooks;
    }

    @Override
    public BookDto findByTitle(String title) {
        final Book book = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        //return bookMapper.mapTo(book);
        return BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build();
    }

    @Override
    public int getAuthorsBookCount(String name) {
        return bookRepository.findBookByAuthor(name).size();
    }

    @Override
    public void addNewBook(BookDto bookDto) {
        Objects.requireNonNull(bookDto,"bookDto cannot be null");
        Objects.requireNonNull(bookDto.getTitle(),"book title cannot be blank");
        Objects.requireNonNull(bookDto.getAuthorName(),"author cannot be blank");

        Author author = new Author();

        //author ekle, sonra kitabı author a ekle
        final Optional<Author> existAuthor = authorRepository.findAuthorByName(bookDto.getAuthorName()); //returns full name of author
        if(existAuthor.isEmpty()){
            author.setName(bookDto.getAuthorName());
            authorRepository.save(author);
        }else{
            author = authorRepository.findAuthorByName(bookDto.getAuthorName()).orElseThrow(AuthorNotFoundException::new);
        }

        final Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setAuthorName(bookDto.getAuthorName());

        book.setAuthor(author);
        authorService.addWrittenBook(bookDto.getAuthorName(),book);


        //bookRepository.save(book);
    }

    @Override
    public void deleteBook(String title) {
        Book book = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        List<User> users = findUsersByBookId(book.getId());
        for(User user: users)
        {
            user.getReadList().remove(book);
            user.getFavoriteList().remove(book);
        }

        int authorBookCount = getAuthorsBookCount(book.getAuthorName());
       /* if(authorBookCount == 1)
        {
            Author author = authorRepository.findAuthorByName(book.getAuthorName()).orElseThrow(AuthorNotFoundException::new);
            authorRepository.delete(author);
        }*/
        bookRepository.delete(book);

    }


    @Override
    public void updateBookTitle(String title, String updatedTitle) {
        bookRepository.updateBookTitle(title, updatedTitle);
    }

    @Override
    public List<BookDto> searchBooks(String searchedWord) {
        final List<Book> books = bookRepository.CustomSearchBooks(searchedWord);
        List<BookDto> searchedBooks = new ArrayList<>();
        books.forEach(book -> searchedBooks.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return searchedBooks;

    }

    @Override
    public List<BookDto> searchBookByGenre(String genre) {
        final List<Book> books = bookRepository.searchBookByGenre(genre);
        List<BookDto> booksWithSameGenre = new ArrayList<>();
        books.forEach(book -> booksWithSameGenre.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return booksWithSameGenre;
    }

    @Override
    public void updateBookAuthor(String authorName, String updatedName) {
        bookRepository.updateBookAuthor(authorName, updatedName);
    }

    public List<User> findUsersByBookId(long id) {
        final Iterable<User> users = userRepository.findAll();
        List<User> ListUsers = new ArrayList<>();
        Book book = bookRepository.findBookById(id).orElseThrow(BookNotFoundException::new);
        users.forEach(user -> {
            if (user.getReadList().contains(book) || user.getFavoriteList().contains(book)) {
                ListUsers.add(user);
            }});

        return ListUsers;

    }

}
