import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import UserList from './users/UserList';
import UserAdd from './users/UserAdd'
import AuthorList from './authors/AuthorList'
import BookList from './books/BookList';
import BookAdd from './books/BookAdd'
import AuthorAdd from './authors/AuthorAdd';
import UserEditCity from './users/UserEditCity'
import AuthorEdit from './authors/AuthorEdit'
import BookEdit from './books/BookEdit'
import BookSearch from './books/BookSearch';
import UserSearch from './users/UserSearch';
import Login from './Login/Login';
import BookListForUser from './books/BookListForUser'
import FavoriteAndReadLists from './books/FavoriteAndReadLists'

class App extends Component {
  render() {
    return (
      <Router>
        <Routes>
          <Route path='/' exact={true} element={<Login />} />
          <Route path='/home' exact={true} element={<Home />} />
          <Route path='/users' exact={true} element={<UserList />} />
          <Route path='/users/add' element={<UserAdd />} />
          <Route path='/authors' exact={true} element={<AuthorList />} />
          <Route path='/books' exact={true} element={<BookList />} />
          <Route path='/books/add' element={<BookAdd />} />
          <Route path='/authors/add' element={<AuthorAdd />} />
          <Route path='/users/read_list/:username' element={<BookList />} />
          <Route path='/users/favorite_list/:username' element={<BookList />} />
          <Route path='/users/update/:username' element={<UserEditCity />} />
          <Route path='/authors/update/:username' element={<AuthorEdit />} />
          <Route path='/authors/books/:username' element={<BookList />} />
          <Route path='/books/update/:title' element={<BookEdit />} />
          <Route path='/search/books' element={<BookSearch />} />
          <Route path='/user/search/books/:title' element={<BookListForUser />} />
          <Route path='/search/books/:title' element={<BookList />} />
          <Route path='/search/users' element={<UserSearch />} />
          <Route path='/search/users/:word' element={<UserList />} />
          <Route path='/login' element={<Login />} />
          <Route path='/user/books' element={<BookListForUser />} />
          <Route path='/user/read_list' element={<FavoriteAndReadLists />} />
          <Route path='/user/favorite_list' element={<FavoriteAndReadLists />} />
        </Routes>
      </Router>
    );
  }
}

export default App;
