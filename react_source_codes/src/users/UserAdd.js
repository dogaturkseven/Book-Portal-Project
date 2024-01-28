import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, FormGroup, Input } from "reactstrap";
import AppNavbar from "../Navbar";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading } from '../StyledComponents';
import 'bootstrap/dist/css/bootstrap.css';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";

const token = LocalStorageUtil.getToken();

const UserAdd = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({
    name: '',
    surname: '',
    username: '',
    password: '',
    age: '',
    city: ''
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = 'http://localhost:8090/api/admin/users/add';
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
        navigate('/users');
      } else {
        console.log('Something happened wrong');
      }
    } catch (error) {
      console.error('Error adding user:', error);
      // Handle the error as needed
    }
  };
  

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Add User</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="name" id="name" placeholder="Name" value={item.name || ''} onChange={handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Input type="text" name="surname" id="surname" placeholder="Surname" value={item.surname || ''} onChange={handleChange} />
          </FormGroup>
          <FormGroup>
            <Input type="text" name="username" id="username" placeholder="Username" value={item.username || ''} onChange={handleChange} />
          </FormGroup>
          <FormGroup>
            <Input type="password" name="password" id="password" placeholder="Password" value={item.password || ''} onChange={handleChange}/>
          </FormGroup>
          <FormGroup>
            <Input type="text" name="age" id="age" value={item.age || ''} placeholder="Age" onChange={handleChange} />
          </FormGroup>
          <FormGroup>
            <Input type="text" name="city" id="city" value={item.city || ''} placeholder="City" onChange={handleChange} />
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">Save</Button>{' '}
            <Link to="/users">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default UserAdd;