import React from "react";
//import {Table} from "antd"
import "antd/dist/reset.css";
import { fetchAuthors } from "./AuthorService";
import { Button, ButtonGroup, Container} from 'reactstrap';
import AppNavbar from '../Navbar';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import {StyledTable, StyledButtonGroup, CenteredHeading} from '../StyledComponents';

const StyledButton = styled(Button)`
  margin-right: 5px;
`;

const token = LocalStorageUtil.getToken();

class AuthorList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {authors: [],
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
  
    async remove(authorname) {
        try {
          const url = `http://localhost:8090/api/admin/authors/delete/${authorname}`;
          await fetch(url, {
            method: 'POST',
            headers: {
              'Authorization':  `Basic ${token}`, // Replace with your actual credentials
            },
          });
      
          let updatedAuthors = [...this.state.authors].filter(author => author.name !== authorname);
          this.setState({ authors: updatedAuthors });
        } catch (error) {
          console.error('Error removing author:', error);
          // Handle the error as needed
        }
      }
      
  
    fetch = async (params = {}) => {
      this.setState();
  
      const data = await fetchAuthors(params);
  
      this.setState({
          authors: data 
      });
  };
  
    render () {
      const {authors, isLoading} = this.state;
  
      if (isLoading) {
          return <p>Loading...</p>;
      }
  
      const AuthorList = authors.map(author => {
        return <tr key={author.id}>
            <td style={{whiteSpace: 'nowrap'}}>{author.name}</td>
            <td>
                <StyledButtonGroup>
                    <StyledButton size="sm" color="primary" tag={Link} to={"/authors/update/" + author.name}>Edit</StyledButton>
                    <StyledButton size="sm" color="danger" onClick={() => this.remove(author.name)}>Delete</StyledButton>
                </StyledButtonGroup>
            </td>
            <td>
                <ButtonGroup>
                    <Button width="150px" color="success" tag= {Link} to = {"/authors/books/" + author.name}> See Books </Button>
                </ButtonGroup>
            </td>
        </tr>
      });
  
      return (
        <div>
            <AppNavbar/>
            <br/>
            <Container fluid>
                <div className="float-right">
                    <StyledButton color="success" tag={Link} to="/authors/add">Add Author</StyledButton>
                </div>
                <br/>
                <CenteredHeading>Authors</CenteredHeading>
                <StyledTable className="mt-4">
                    <thead>
                    <tr>
                        <th width="15%">Name</th>
                        <th width="15%">Edit Delete</th>
                        <th width="15%">Books</th>
  
                    </tr>
                    </thead>
                    <tbody>
                    {AuthorList}
                    </tbody>
                </StyledTable>
            </Container>
        </div>
      );
    }
  }
  export default AuthorList;