import React, {Component} from "react";
import {Navbar} from "react-bootstrap";
import {BrowserRouter as Router, Route} from "react-router-dom"

import MainComponent from './MainComponent'
import DashBoardComponent from './DashBoard'

class TopMenuComponent extends Component{
    render(){
        return(
        <Router>
            <Navbar>
                <Navbar.Brand href="/main">Home </Navbar.Brand><Navbar.Brand href="/dashboard">Main</Navbar.Brand>
            </Navbar>

            <Route path="/main" component={MainComponent}/>
            <Route path="/dashboard" component={DashBoardComponent}/>
        </Router>
        )
    }
}

export default TopMenuComponent;