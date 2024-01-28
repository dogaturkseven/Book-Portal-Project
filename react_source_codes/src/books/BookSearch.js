import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "../Navbar";
import 'bootstrap/dist/css/bootstrap.css';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading} from '../StyledComponents';

const BookSearch = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ title: '' });
  const token = LocalStorageUtil.getToken();
  const decoded = atob(token);
  const username = decoded.split(':')[0];

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (username === "sys.admin") {
      navigate(`/search/books/${item.title}`);
    } else {
      navigate(`/user/search/books/${item.title}`);
    }
  };

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Search for a book:</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="title" id="title" placeholder="Search for a title..." value={item.title || ''} onChange={handleChange}></Input>
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">
              {username === "sys.admin" ? "Search" : "Book Search For User"}
            </Button>
            <Link to="/books">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default BookSearch;
