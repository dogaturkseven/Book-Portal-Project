import React from "react";
import axios from "axios";
import { fetchBooksForUser } from "../users/UserBookService";
import { Button, ButtonGroup} from 'reactstrap';
import AppNavbar from '../Navbar';
import { Link, useNavigate } from 'react-router-dom';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledTable, CenteredHeading} from '../StyledComponents';

const token = LocalStorageUtil.getToken();

class FavoriteAndReadLists extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      books: [],
      pagination: {
        current: 1,
        pageSize: 10
      },
    };
  }

  componentDidMount() {
    const { pagination } = this.state;
    this.fetch({ pagination });
  }

  async deleteFromList(title) {
    try {
      const url = "http://localhost:8090/api" + window.location.pathname + `/remove/${title}`
      await fetch(url, {
        method: 'POST',
        headers: {
          'Authorization': `Basic ${token}`, // Replace with your actual credentials
        },
      });

      window.location.reload();
  
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

  render() {
    const { books, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const BookList = books.map(book => {
      return <tr key={book.id}>
        <td style={{ whiteSpace: 'nowrap' }}>{book.title}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{book.authorName}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{book.genre}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" onClick={() => this.deleteFromList(book.title)} tag={Link}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar />
        <StyledContainer fluid className="mt-5">
          <CenteredHeading>Books</CenteredHeading>
          <StyledTable className="mt-4">
            <thead>
              <tr>
                <th width="15%">Title</th>
                <th width="15%">Author</th>
                <th width="15%">Genre</th>
                <th width="15%">Delete</th>
              </tr>
            </thead>
            <tbody>
              {BookList}
            </tbody>
          </StyledTable>
        </StyledContainer>
      </div>
    );
  }
}

export default FavoriteAndReadLists;
