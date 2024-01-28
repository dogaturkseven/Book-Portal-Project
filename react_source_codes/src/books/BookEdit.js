import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input} from "reactstrap";
import AppNavbar from "../Navbar";
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading} from '../StyledComponents';

const token = LocalStorageUtil.getToken();

const BookEdit = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ title: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = `http://localhost:8090/api/admin${window.location.pathname}`;
      
      const data = {
        title: item.title,
      };

      console.log(data.title)
  
      const headers = {
        'Content-Type': 'application/json',
        Authorization: `Basic ${token}`,
      };
  
      await axios.post(url, data.title, { headers });
  
      navigate('/books');
    } catch (error) {
      console.error('Error updating book:', error);
      console.log('Something happened wrong');
    }
  };
  

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Edit book:</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="title" id="title" placeholder="Enter the new title for the book..." value={item.title || ''} onChange={handleChange} />
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

export default BookEdit;
