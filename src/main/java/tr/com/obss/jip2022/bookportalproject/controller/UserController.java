package tr.com.obss.jip2022.bookportalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.service.BookService;
import tr.com.obss.jip2022.bookportalproject.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    //see list of books
    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    //search book
    @GetMapping("/search/books/{searchedWord}")
    public List<BookDto> searchBook(@PathVariable("searchedWord")  @Valid String searchedWord)
    {
        return bookService.searchBooks(searchedWord);
    }

    @PostMapping("/search_genre")
    public List<BookDto> searchBookByGenre(@RequestBody @Valid String genre)
    {
        return bookService.searchBookByGenre(genre);
    }

    @PostMapping("/read_list/add/{title}")
    public Boolean addToReadList(@PathVariable("title") String title)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.addBookToReadList(username, title);
        return Boolean.TRUE;
    }

    @GetMapping("/read_list")
    public List<BookDto> getUserReadList()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserReadList(username);
    }
    @GetMapping("/favorite_list")
    public List<BookDto> getUserFavoriteList()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserFavoriteList(username);
    }

    @PostMapping("/read_list/remove/{title}")
    public Boolean removeFromReadList(@PathVariable("title") String title)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.removeBookFromReadList(username, title);
        return Boolean.TRUE;
    }

    @PostMapping("/favorite_list/add/{title}")
    public Boolean addToFavoriteList(@PathVariable("title") String title)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.addBookToFavoriteList(username, title);
        return Boolean.TRUE;
    }

    @PostMapping("/favorite_list/remove/{title}")
    public Boolean removeFromFavoriteList(@PathVariable("title") String title)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.removeBookFromFavoriteList(username, title);
        return Boolean.TRUE;
    }

    @PostMapping("search/books")
    public List<BookDto> searchBookByName(@RequestBody @Valid String searchedWord) {
        return bookService.searchBooks(searchedWord);

    }

}
