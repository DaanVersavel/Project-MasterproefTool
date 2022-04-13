import React from 'react';
import {Container, Nav, Navbar, NavDropdown} from 'react-bootstrap'
import KULogo from '../../KU_Leuven_logo.svg'

const BNavbar = () => {
    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" sticky="top">
            <Container>
                <Navbar.Brand href="/">
                    <img
                        src={KULogo}
                        width="150"
                        height="50"
                        className="d-inline-block align-top"
                        alt="KU Leuven logo"
                    />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Preferences">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <Nav>
                        <Nav.Link href="/Login">Login</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link href="/Contact">Contact</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default BNavbar;
