import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, FormGroup, Input } from "reactstrap";
import AppNavbar from "../Navbar";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading} from '../StyledComponents';

import LocalStorageUtil from "../Login/util/LocalStorageUtil";

const token = LocalStorageUtil.getToken();

const AuthorAdd = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ name: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = 'http://localhost:8090/api/admin/authors/add';
      await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Basic ${token}`, 
        },
        body: JSON.stringify(item),
      });
  
      navigate('/authors');
    } catch (error) {
      console.error('Error adding author:', error);
      // Handle the error as needed
    }
  };
  
  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Add Author</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="name" id="name" placeholder="Name" value={item.name || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">Save</Button>
            <Link to="/authors">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default AuthorAdd;
