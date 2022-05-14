import React from 'react';
import {Container, Nav, Navbar, NavDropdown} from 'react-bootstrap'
import KULogo from '../../KU_Leuven_logo.svg'
const BNavbar = () => {
    let buttons

    if(sessionStorage.getItem("access_token")){
        buttons = (
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav>
                    <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                        <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                        <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                        <NavDropdown.Item href="/Student/Starred">Starred Subjects</NavDropdown.Item>
                        <NavDropdown.Divider />
                        <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                    </NavDropdown>
                    <Nav.Link href="/Account">Account</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        )
    }
    else {
        buttons = (
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="ml-auto">
                    <Nav.Link href="/Login">Login</Nav.Link>
                    <Nav.Link href="/SignUp">Sign up</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        )
    }

    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" sticky="top">
            <Container>
                <Navbar.Brand href="/Home">
                    <img
                        src={KULogo}
                        width="150"
                        height="50"
                        alt="KU Leuven logo"
                    />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                {buttons}
            </Container>
        </Navbar>
    );
};

export default BNavbar;
