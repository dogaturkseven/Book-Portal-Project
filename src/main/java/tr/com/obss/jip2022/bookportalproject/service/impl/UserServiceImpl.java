package tr.com.obss.jip2022.bookportalproject.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.dto.CreateNewUserRequest;
import tr.com.obss.jip2022.bookportalproject.dto.UserDto;
import tr.com.obss.jip2022.bookportalproject.exception.BookAlreadyInListException;
import tr.com.obss.jip2022.bookportalproject.exception.BookNotFoundException;
import tr.com.obss.jip2022.bookportalproject.exception.UserNameAlreadyExistException;
import tr.com.obss.jip2022.bookportalproject.exception.UserNotFoundException;
import tr.com.obss.jip2022.bookportalproject.mapper.UserMapper;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.Role;
import tr.com.obss.jip2022.bookportalproject.model.RoleType;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.repository.BookRepository;
import tr.com.obss.jip2022.bookportalproject.repository.UserRepository;
import tr.com.obss.jip2022.bookportalproject.service.RoleService;
import tr.com.obss.jip2022.bookportalproject.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;

    private final RoleService roleService;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, BookRepository bookRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
        this.roleService = roleService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        final Iterable<User> users = userRepository.findAll();
        List<UserDto> allUsers = new ArrayList<>();
        users.forEach(user -> allUsers.add(UserDto.builder().name(user.getName()).surname(user.getSurname())
                .age(user.getAge()).roles(user.getRoles()).readList(user.getReadList())
                .favoriteList(user.getFavoriteList()).username(user.getUsername()).city(user.getCity()).build()));
        return allUsers;
    }

    @Override
    public UserDto findByName(String name) {
        final User user = userRepository.findUserByName(name).orElseThrow(UserNotFoundException::new);
        return userMapper.mapTo(user);
    }

    @Override
    public User findByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(String.format("%s username not found",username)));

    }


    /*public List<User> findUserByBook(Book book) {
        List<User> users = userRepository.findAllByBook(book);
        users.forEach(user -> user.getReadList().remove(book));
    }*/

    @Override
    public void createNewUser(CreateNewUserRequest createNewUserRequest) {
        Objects.requireNonNull(createNewUserRequest,"userDto cannot be null");
        Objects.requireNonNull(createNewUserRequest.getName(),"name cannot be blank");
        Objects.requireNonNull(createNewUserRequest.getSurname(),"surname cannot be blank");

        final Optional<User> existUsername = userRepository.findUserByUsername(createNewUserRequest.getUsername());
        if(existUsername.isPresent()){
            throw new UserNameAlreadyExistException(createNewUserRequest.getUsername());
        }

        final User user = new User();
        user.setPassword(passwordEncoder.encode(createNewUserRequest.getPassword())); //encode ekle
        user.setUsername(createNewUserRequest.getUsername());
        user.setName(createNewUserRequest.getName());
        user.setSurname(createNewUserRequest.getSurname());
        user.setAge(createNewUserRequest.getAge());
        user.setCity(createNewUserRequest.getCity());
        final Role standartRole = roleService.findByName(RoleType.ROLE_USER);
        user.setRoles(List.of(standartRole));

        userRepository.save(user);

    }

    @Override
    public void createNewUser(User user) {
        Objects.requireNonNull(user,"user cannot be null");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException ::new);
        userRepository.delete(user);

    }

    public void updateUserCity(String username, String updatedCity)
    {
        userRepository.updateUser(username, updatedCity);
    }

    @Override
    public List<UserDto> searchUsers(String searchedWord) {
        final List<User> users = userRepository.customSearchUser(searchedWord);
        List<UserDto> searchedUsers = new ArrayList<>();
        users.forEach(user -> searchedUsers.add(userMapper.mapTo(user)));
        return searchedUsers;
    }

    @Override
    public void addBookToReadList(String username, String title) {
        Book addedBook = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Book> readListBooks = user.getReadList();
        if(!readListBooks.contains(addedBook))
        {
            readListBooks.add(addedBook);
        }
        else {
            throw new BookAlreadyInListException(addedBook.getTitle());
        }

        user.setReadList(readListBooks);
        userRepository.save(user);

    }

    @Override
    public void removeBookFromReadList(String username, String title) {
        Book removedBook = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNameAlreadyExistException::new);
        List<Book> readListBooks = user.getReadList();
        readListBooks.remove(removedBook);
        user.setReadList(readListBooks);
        userRepository.save(user);
    }

    @Override
    public void addBookToFavoriteList(String username, String title) {
        Book addedBook = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Book> favoriteListBooks = user.getFavoriteList();
        if(!favoriteListBooks.contains(addedBook))
        {
            favoriteListBooks.add(addedBook);
        }
        else {
            throw new BookAlreadyInListException(addedBook.getTitle());
        }
        
        user.setFavoriteList(favoriteListBooks);
        userRepository.save(user);

    }

    @Override
    public void removeBookFromFavoriteList(String username, String title) {
        Book removedBook = bookRepository.findBookByTitle(title).orElseThrow(BookNotFoundException::new);
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNameAlreadyExistException::new);
        List<Book> favoriteListBooks = user.getFavoriteList();
        favoriteListBooks.remove(removedBook);
        user.setFavoriteList(favoriteListBooks);
        userRepository.save(user);
    }

    @Override
    public List<BookDto> getUserReadList(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        List<BookDto> readBookList = new ArrayList<>();
        List <Book> booklist = user.getReadList();
        booklist.forEach(book -> readBookList.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return readBookList;
    }

    @Override
    public List<BookDto> getUserFavoriteList(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        List<BookDto> favoriteBookList = new ArrayList<>();
        List <Book> booklist = user.getFavoriteList();
        booklist.forEach(book -> favoriteBookList.add(BookDto.builder().title(book.getTitle()).authorName(book.getAuthor().getName()).genre(book.getGenre()).build()));
        return favoriteBookList;
    }


}
