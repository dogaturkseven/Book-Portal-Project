import React from "react";
//import {Table} from "antd"
import "antd/dist/reset.css";
import { fetchBooksForUser } from "../users/UserBookService";
import { Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../Navbar';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledTable, CenteredHeading} from '../StyledComponents';

const StyledButton = styled(Button)`
  margin-right: 5px;
`;

const token = LocalStorageUtil.getToken();

class BookListForUser extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: [],
          pagination: {
            current: 1, 
            pageSize: 10
        },};
    }
  
    componentDidMount() {
        const {pagination} = this.state;
        this.fetch({pagination});
    }

    async addToReadList(title) {
        try {
          const url = `http://localhost:8090/api/user/read_list/add/${title}`;
          await fetch(url, {
            method: 'POST',
            headers: {
              'Authorization': `Basic ${token}`, 
            },
          });
        } catch (error) {
          console.error('Error adding to read list:', error);
          // Handle the error as needed
        }
      }
      
      async addToFavoriteList(title) {
        try {
          const url = `http://localhost:8090/api/user/favorite_list/add/${title}`;
          await fetch(url, {
            method: 'POST',
            headers: {
              'Authorization': `Basic ${token}`, // Replace with your actual credentials
            },
          });
        } catch (error) {
          console.error('Error adding to favorite list:', error);
          // Handle the error as needed
        }
      }
      
  
    fetch = async (params = {}) => {
      this.setState();
      const data = await fetchBooksForUser(params);
  
      this.setState({
          books: data 
      });
  };
  
    render () {
      const {books, isLoading} = this.state;
  
      if (isLoading) {
          return <p>Loading...</p>;
      }
  
      const BookList = books.map(book => {
        return <tr key={book.id}>
            <td style={{whiteSpace: 'nowrap'}}>{book.title}</td>
            <td style={{whiteSpace: 'nowrap'}}>{book.authorName}</td>
            <td style={{whiteSpace: 'nowrap'}}>{book.genre}</td>
            <td>
                <ButtonGroup>
                    <StyledButton size="sm" color="primary" onClick={() => this.addToReadList(book.title)}tag={Link}>Add To Read List</StyledButton>
                    <StyledButton size="sm" color="success" onClick={() => this.addToFavoriteList(book.title)}tag={Link}>Add To Favorite List</StyledButton>
                </ButtonGroup>
            </td>
        </tr>
      });
  
      return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-right">
                    <br/>
                    <StyledButton color="success" tag={Link} to="/user/read_list">Go to Read List</StyledButton>
                    <StyledButton color="warning" tag={Link} to="/user/favorite_list">Go to Favorite List</StyledButton>
                    <br/>
                </div>
                <br/>
                <CenteredHeading>Books</CenteredHeading>
                <StyledTable className="mt-4">
                    <thead>
                    <tr>
                        <th width="15%">Title</th>
                        <th width="15%">Author</th>
                        <th width="15%">Genre</th>
                        <th width="25%">Add To List</th>
  
                    </tr>
                    </thead>
                    <tbody>
                    {BookList}
                    </tbody>
                </StyledTable>
            </Container>
        </div>
      );
    }
  }
  export default BookListForUser;