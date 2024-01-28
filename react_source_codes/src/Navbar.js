import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';
import LocalStorageUtil from "./Login/util/LocalStorageUtil"



export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        const token = LocalStorageUtil.getToken();
        const decoded = atob(token)
        const username = decoded.split(':')[0]
        this.state = {
            username: username,
            isOpen: false
        };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        const {username} = this.state
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/home">Home</NavbarBrand>
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/users">Users</NavbarBrand>) : " "}
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/authors">Authors</NavbarBrand>) : " "}
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/books">Books</NavbarBrand>) : " "}
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/authors/add">Add Author</NavbarBrand>) : " "}
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/books/add">Add Book</NavbarBrand>) : " "}
            {username == "sys.admin" ? (<NavbarBrand tag={Link} to="/search/users">Search User</NavbarBrand>) : " "}
            <NavbarBrand tag={Link}  to="/search/books">Search Book</NavbarBrand>
            <NavbarBrand tag={Link} to="/user/books">Books For User</NavbarBrand>

        </Navbar>;
    }
}
