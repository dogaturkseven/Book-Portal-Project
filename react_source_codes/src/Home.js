import React, { Component } from 'react';
import './App.css';
import AppNavbar from './Navbar';
import LocalStorageUtil from "./Login/util/LocalStorageUtil";
import './Home.css'
import library from './images/library.jpg'

class Home extends Component {

    constructor(props) {
        super(props);
        const token = LocalStorageUtil.getToken();
        const decoded = atob(token)
        const username = decoded.split(':')[0]
        this.state = {
            username: username
        };
    }

    render() {
        const {username} = this.state
        return (
            <div>
                <AppNavbar />
                <div  className="centered-text">

                        Welcome back, {username}!
                </div>
                <div style={{ marginTop: '0px', textAlign: 'center bottom', marginRight: '30px'}}>
                    <img
                        src ={library}
                        alt  = ""
                        style={{ maxWidth: '100%', height: 'auto' }}
                    />
                </div>
        </div>
        
        );
    }
}
export default Home;