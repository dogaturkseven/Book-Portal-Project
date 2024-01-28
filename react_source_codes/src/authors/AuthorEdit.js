import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, FormGroup, Input} from "reactstrap";
import AppNavbar from "../Navbar";
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading} from '../StyledComponents';

const token = LocalStorageUtil.getToken();

const AuthorEdit = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ name: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    try {
      const url = `http://localhost:8090/api/admin${window.location.pathname}`;
      
      const data = {
        name: item.name,
      };
  
      const headers = {
        'Content-Type': 'application/json',
         Authorization: `Basic ${token}`,
      };
  
      await axios.post(url, data.name, { headers });
  
      navigate('/authors');
    } catch (error) {
      console.error('Error updating author:', error);
      console.log('Something happened wrong');
    }
  };
  

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Edit author:</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input type="text" name="name" id="name" placeholder="Enter the updated name for the author..." value={item.name || ''} onChange={handleChange} />
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="primary" type="submit">Save</Button>
            <Link to="/authors">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default AuthorEdit;
