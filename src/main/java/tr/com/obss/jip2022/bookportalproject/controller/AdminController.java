package tr.com.obss.jip2022.bookportalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.bookportalproject.dto.AuthorDto;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.dto.CreateNewUserRequest;
import tr.com.obss.jip2022.bookportalproject.dto.UserDto;
import tr.com.obss.jip2022.bookportalproject.model.Author;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.service.AuthorService;
import tr.com.obss.jip2022.bookportalproject.service.BookService;
import tr.com.obss.jip2022.bookportalproject.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    //list all users
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users/add")
    public Boolean createNewUser(@RequestBody @Valid CreateNewUserRequest createNewUserRequest)
    {
        userService.createNewUser(createNewUserRequest);
        return Boolean.TRUE;
    }

    @GetMapping("/users/read_list/{username}")
    public List<BookDto> getUserReadList(@PathVariable("username") String username)
    {
        return userService.getUserReadList(username);
    }
    @GetMapping("/users/favorite_list/{username}")
    public List<BookDto> getUserFavoriteList(@PathVariable("username") String username)
    {
        return userService.getUserFavoriteList(username);
    }

    @PostMapping("/users/delete/{username}")
    public Boolean deleteUser(@PathVariable("username") String username)
    {
        userService.deleteUser(username);
        return Boolean.TRUE;
    }

    @PostMapping  ("/users/update/{username}")
    public Boolean updateUser(@PathVariable("username") String username, @RequestBody @Valid String city)
    {
        userService.updateUserCity(username, city.replaceAll("^\"|\"$", ""));
        return Boolean.TRUE;
    }

    @GetMapping("/search/users/{word}")
    public List<UserDto> searchUser(@PathVariable("word") @Valid String searchedWord)
    {
        return userService.searchUsers(searchedWord);
    }

    @PostMapping("/books/add")
    public Boolean addNewBook(@RequestBody @Valid BookDto bookDto)
    {
        bookService.addNewBook(bookDto);
        return Boolean.TRUE;
    }

    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/books/delete/{title}")
    public Boolean deleteBook(@PathVariable("title") String bookTitle)
    {
        bookService.deleteBook(bookTitle);
        return Boolean.TRUE;
    }

    @PostMapping("/books/update/{title}")
    public Boolean updateBook(@PathVariable("title") String title, @RequestBody  @Valid String updatedTitle)
    {
        bookService.updateBookTitle(title, updatedTitle.replaceAll("^\"|\"$", ""));
        return Boolean.TRUE;
    }

    @PostMapping("/search_genre")
    public List<BookDto> searchBookByGenre(@RequestBody @Valid String genre)
    {
        return bookService.searchBookByGenre(genre);
    }


    @PostMapping("/authors/add")
    public Boolean addNewAuthor(@RequestBody @Valid AuthorDto authorDto)
    {
        authorService.addNewAuthor(authorDto);
        return Boolean.TRUE;
    }

    @GetMapping("/authors/books/{authorname}")
    public List<BookDto> getBooksOfAuthor(@PathVariable("authorname") String authorName)
    {
        return authorService.getBooksOfAuthor(authorName);
    }

    @GetMapping("/all_authors")
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping("/authors/delete/{name}")
    public Boolean deleteAuthor(@PathVariable("name") String name)
    {
        authorService.deleteAuthor(name);
        return Boolean.TRUE;
    }

    @CrossOrigin
    @PostMapping("/authors/update/{authorname}")
    public Boolean updateAuthor(@PathVariable("authorname") String name, @RequestBody  @Valid String updatedName)
    {
        authorService.updateAuthor(name, updatedName.replaceAll("^\"|\"$", ""));
        return Boolean.TRUE;
    }

    @GetMapping("/search/books/{searchedWord}")
    public List<BookDto> searchBook(@PathVariable("searchedWord")  @Valid String searchedWord)
    {
        return bookService.searchBooks(searchedWord);
    }

}

