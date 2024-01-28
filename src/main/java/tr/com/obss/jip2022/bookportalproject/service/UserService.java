package tr.com.obss.jip2022.bookportalproject.service;

import tr.com.obss.jip2022.bookportalproject.dto.BookDto;
import tr.com.obss.jip2022.bookportalproject.dto.CreateNewUserRequest;
import tr.com.obss.jip2022.bookportalproject.dto.UserDto;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto findByName(String name);

    User findByUsername(String username);

    void createNewUser(CreateNewUserRequest createNewUserRequest);

    void createNewUser(User user); //add user

    void deleteUser(String username);

    void updateUserCity(String username, String city);

    List<UserDto> searchUsers(String searchedWord);

    void addBookToReadList(String username, String title);

    void removeBookFromReadList(String username, String title); //title aynı olma ihtimalini düşünmel miyiz?

    void addBookToFavoriteList(String username, String title);

    void removeBookFromFavoriteList(String username, String title);

    List<BookDto> getUserReadList(String username);

    List<BookDto> getUserFavoriteList(String username);
}
