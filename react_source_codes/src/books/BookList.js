import React from "react";
import "antd/dist/reset.css";
import axios from "axios";
import { fetchBooks } from "./BookService";
import { Button, ButtonGroup, Container, Table} from 'reactstrap';
import styled from 'styled-components';
import AppNavbar from '../Navbar';
import { Link } from 'react-router-dom';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import {StyledTable, StyledButtonGroup, CenteredHeading} from '../StyledComponents';

const StyledButton = styled(Button)`
  margin-right: 5px;
`;

const token = LocalStorageUtil.getToken();

class BookList extends React.Component {

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
        this.remove = this.remove.bind(this);
    }

    async remove(title) {
      try {
        const url = `http://localhost:8090/api/admin/books/delete/${title}`;
        await fetch(url, {
          method: 'POST',
          headers: {
            'Authorization':  `Basic ${token}`, // Replace with your actual credentials
          },
        });
    
        let updatedBooks= [...this.state.books].filter(books => books.title !== title);
        this.setState({ books: updatedBooks });
      } catch (error) {
        console.error('Error removing author:', error);
        // Handle the error as needed
      }
    }
     
  
    fetch = async (params = {}) => {
      this.setState();
    
      const data = await fetchBooks(params);
  
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
                <StyledButtonGroup>
                    <StyledButton size="sm" margin-right=" 5px" color="primary" tag={Link} to={"/books/update/" + book.title}>Edit</StyledButton>
                    <StyledButton size="sm" color="danger" onClick={() => this.remove(book.title)}>Delete</StyledButton>
                </StyledButtonGroup>
            </td>
        </tr>
      });
  
      return (
        <div>
            <AppNavbar/>
            <br/>
            <Container fluid>
                <div className="float-right">
                    <StyledButton color="success" tag={Link} to="/books/add">Add Book</StyledButton>
                </div>
                <br/>
                <CenteredHeading>Books</CenteredHeading>
                <StyledTable className="mt-4">
                    <thead>
                    <tr>
                        <th width="15%">Title</th>
                        <th width="15%">Author</th>
                        <th width="15%">Genre</th>
                        <th width="15%">Edit Delete</th>
  
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
  export default BookList;