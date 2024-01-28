import React from "react";
//import {Table} from "antd"
import "antd/dist/reset.css";
import axios from "axios";
import { fetchUsers } from "./UserService";
import { Button, ButtonGroup, Container, Table} from 'reactstrap';
import styled from 'styled-components';
import AppNavbar from '../Navbar';
import { Link } from 'react-router-dom';
import LocalStorageUtil from "../Login/util/LocalStorageUtil";
import {StyledTable, StyledButtonGroup, CenteredHeading} from '../StyledComponents';

const StyledButton = styled(Button)`
  margin-right: 5px;
`;

const token = LocalStorageUtil.getToken();


class UserList extends React.Component {

  constructor(props) {
      super(props);
      this.state = {users: [],
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

  async remove(username) {
    try {
      const url = `http://localhost:8090/api/admin/users/delete/${username}`;
      await fetch(url, {
        method: 'POST',
        headers: {
          'Authorization':  `Basic ${token}`, // Replace with your actual credentials
        },
      });
  
      let updatedUsers= [...this.state.users].filter(users => users.username !== username);
      this.setState({ users: updatedUsers });
    } catch (error) {
      console.error('Error removing author:', error);
      // Handle the error as needed
    }
  }


  fetch = async (params = {}) => {
    this.setState();

    const data = await fetchUsers(params);

    this.setState({
        users: data 
    });
};

  render () {
    const {users, isLoading} = this.state;

    if (isLoading) {
        return <p>Loading...</p>;
    }

    const UserList = users.map(user => {
      console.log(user)
      return <tr key={user.username}>
          <td style={{whiteSpace: 'nowrap'}}>{user.username}</td>
          <td>{user.name}</td>
          <td>{user.surname}</td>
          <td>{user.age}</td>
          <td>{user.city}</td>
          <td>
              <StyledButtonGroup>
                  <StyledButton size="sm" color="primary" tag={Link} to={"/users/update/" + user.username}>Edit</StyledButton>
                  {user.username !== 'sys.admin' ? (
                  <StyledButton size="sm" color="danger" onClick={() => this.remove(user.username)}>Delete</StyledButton>) : null}
              </StyledButtonGroup>
          </td>
          <td>
              <ButtonGroup>
                  <StyledButton size="sm" color="success" tag= {Link} to = {"/users/read_list/" + user.username}> Read List </StyledButton>
                  <StyledButton size="sm" color="warning" tag= {Link} to = {"/users/favorite_list/" + user.username}> Favorite List </StyledButton>
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
                  <StyledButton color="success" tag={Link} to="/users/add">Add User</StyledButton>
              </div>
              <br/>
              <CenteredHeading>Users</CenteredHeading>
            
              <StyledTable className="mt-4">
                  <thead>
                  <tr>
                      <th width="10%">Username</th>
                      <th width="10%">Name</th>
                      <th width="10%">Surname</th>
                      <th width="10%">Age</th>
                      <th width="10%">City</th>
                      <th width="20%">Edit Delete</th>
                      <th width="20%">Lists</th>

                  </tr>
                  </thead>
                  <tbody>
                  {UserList}
                  </tbody>
              </StyledTable>
          </Container>
      </div>
    );
  }
}
export default UserList;