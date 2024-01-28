import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "../Navbar";
import 'bootstrap/dist/css/bootstrap.css';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledForm, StyledButtonGroupForm,CenteredHeading} from '../StyledComponents';

const token = LocalStorageUtil.getToken();

const BookAdd = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ title: '', genre: '', authorName: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = 'http://localhost:8090/api/admin/books/add';
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Basic ${token}`, // Replace with your actual credentials
        },
        body: JSON.stringify(item),
      });
  
      if (response.ok) {
        const responseData = await response.json();
        console.log(responseData);
        navigate('/books');
      } else {
        console.log('Something happened wrong');
      }
    } catch (error) {
      console.error('Error adding book:', error);
      // Handle the error as needed
    }
  };
  

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Add Book</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="title" id="title"  placeholder="Title" value={item.title || ''} onChange={handleChange}></Input>
          </FormGroup>
          <FormGroup>
            <Input type="text" name="authorName" id="author" placeholder="Author" value={item.authorName} onChange={handleChange} />
          </FormGroup>
          <FormGroup>
            <Input type="text" name="genre" id="genre"  placeholder="Genre" value={item.genre || ''} onChange={handleChange} />
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">Save</Button>
            <Link to="/books">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default BookAdd;
