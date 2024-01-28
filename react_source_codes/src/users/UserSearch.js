import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, FormGroup, Input} from "reactstrap";
import AppNavbar from "../Navbar";
import { StyledContainer, StyledForm, StyledButtonGroupForm, CenteredHeading} from '../StyledComponents';

const UserSearch = () => {
  const navigate = useNavigate();
  const [item, setItem] = useState({ word: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem({ ...item, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate(`/search/users/${item.word}`);
  };

  return (
    <div>
      <AppNavbar/>
      <StyledContainer>
        <CenteredHeading>Search for a user:</CenteredHeading>
        <StyledForm onSubmit={handleSubmit}>
          <FormGroup>
            <Input
              type="text"
              name="word"
              id="word"
              placeholder="Search for a user..."
              value={item.word || ''}
              onChange={handleChange}
            />
          </FormGroup>
          <StyledButtonGroupForm>
            <Button color="info" type="submit">Search</Button>
            <Link to="/users">
              <Button color="secondary">Cancel</Button>
            </Link>
          </StyledButtonGroupForm>
        </StyledForm>
      </StyledContainer>
    </div>
  );
}

export default UserSearch;
