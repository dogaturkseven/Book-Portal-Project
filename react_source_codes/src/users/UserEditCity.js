import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, FormGroup, Input} from "reactstrap";
import AppNavbar from "../Navbar";
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading } from '../StyledComponents';

const token = LocalStorageUtil.getToken();

const UserEditCity = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ city: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = `http://localhost:8090/api/admin${window.location.pathname}`;
      
      const data = {
        city: item.city,
      };

      const headers = {
        'Content-Type': 'application/json',
        Authorization: `Basic ${token}`,
      };
  
      await axios.post(url, data.city, { headers });
  
      navigate('/users');
    } catch (error) {
      console.error('Error updating city:', error);
      console.log('Something happened wrong');
    }
  };

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Edit user:</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="city" id="city" placeholder="Enter the new city for the user" value={item.city || ''} onChange={handleChange} />
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">Save</Button>
            <Link to="/users">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default UserEditCity;
