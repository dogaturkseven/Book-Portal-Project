package tr.com.obss.jip2022.bookportalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.bookportalproject.dto.AuthorDto;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.exception.AuthorAlreadyExistException;
import tr.com.obss.jip2022.bookportalproject.exception.AuthorNotFoundException;
import tr.com.obss.jip2022.bookportalproject.exception.UserNotFoundException;
import tr.com.obss.jip2022.bookportalproject.mapper.AuthorMapper;
import tr.com.obss.jip2022.bookportalproject.model.Author;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.repository.AuthorRepository;
import tr.com.obss.jip2022.bookportalproject.repository.BookRepository;
import tr.com.obss.jip2022.bookportalproject.repository.UserRepository;
import tr.com.obss.jip2022.bookportalproject.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;


    @Override
    public List<AuthorDto> getAllAuthors() {
        final Iterable<Author> authors = authorRepository.findAll();
        List<AuthorDto> allAuthors = new ArrayList<>();
        authors.forEach(author -> allAuthors.add(authorMapper.mapTo(author)));
        return allAuthors;
    }

    @Override
    public AuthorDto findByName(String name) {
        final Author author = authorRepository.findAuthorByName(name).orElseThrow(AuthorNotFoundException::new);
        return authorMapper.mapTo(author);
    }

    @Override
    public void addNewAuthor(AuthorDto authorDto) {
        final Optional<User> existAuthor = userRepository.findUserByUsername(authorDto.getName());
        if(existAuthor.isPresent()){
            throw new AuthorAlreadyExistException(authorDto.getName());
        }
        Objects.requireNonNull(authorDto.getName(),"author name cannot be blank");
        final Author author = new Author();
        author.setName(authorDto.getName());
        List<Book> writtenBooks = authorDto.getWrittenBooks();
        if(writtenBooks != null) {
            writtenBooks.forEach(book -> {
                book.setAuthor(author);
                book.setAuthorName(author.getName());
            });
        }
        author.setWrittenBooks(writtenBooks);

        authorRepository.save(author);
    }

    public void addWrittenBook(String authorName, Book book)
    {
        Author author = authorRepository.findAuthorByName(authorName).orElseThrow(AuthorNotFoundException::new);
        List<Book> books = author.getWrittenBooks();
        if(books != null)
        {
            books.add(book);
        }
        else
        {
            books = new ArrayList<>();
            books.add(book);

        }
        author.setWrittenBooks(books);
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String name) {
        Author author = authorRepository.findAuthorByName(name).orElseThrow(AuthorNotFoundException::new);
        deleteUserWithBookFromAuthor(name);
        authorRepository.delete(author);
    }

    @Override
    public void updateAuthor(String authorName, String updatedName) {
        bookRepository.updateBookAuthor(authorName, updatedName);
        authorRepository.updateAuthorName(authorName, updatedName);
    }

    public void deleteUserWithBookFromAuthor(String authorName) {
        final Iterable<User> users = userRepository.findAll();
        users.forEach(user -> {
            List<Book> booksList = user.getReadList();
            List<Book> favList = user.getFavoriteList();
            booksList.removeIf(book -> book.getAuthorName().equals(authorName));
            favList.removeIf(book -> book.getAuthorName().equals(authorName));
        });
    }

    @Override
    public List<BookDto> getBooksOfAuthor(String authorName) {
        Author author = authorRepository.findAuthorByName(authorName).orElseThrow(AuthorNotFoundException::new);
        List<BookDto> writtenBooks = new ArrayList<>();
        List<Book> booklist = author.getWrittenBooks();
        booklist.forEach(book -> writtenBooks.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return writtenBooks;
    }
}
