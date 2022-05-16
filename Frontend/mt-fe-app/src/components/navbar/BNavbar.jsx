import React, {useState} from 'react';
import {Container, Nav, Navbar, NavDropdown} from 'react-bootstrap'
import KULogo from '../../KU_Leuven_logo.svg'
import axios from '../../api/axiosAccessToken'

const BNavbar = () => {
    let buttons
    const [role, setRole] = useState(null);

    if(sessionStorage.getItem("access_token")){
        axios
            .get('/User/whoami')
            .then((response) => {
                console.log(response);
                setRole(response.data['role'].roleName)
                sessionStorage.setItem('name', response.data['firstName'].concat(' ', response.data['surname']))
                sessionStorage.setItem('firstName', response.data['firstName'])
                sessionStorage.setItem('surname', response.data['surname'])
                sessionStorage.setItem('phone', response.data['gsm'])
                sessionStorage.setItem('email', response.data['email'])
                sessionStorage.setItem('userID', response.data['keyId'])
            })
            .catch(error => {
                console.log(error)
            })
        if(role==="ROLE_STUDENT"){
            buttons = (
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav>
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Student/Starred">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/Account">Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            )
        }
        if(role==="ROLE_COÖRDINATOR"){
            buttons = (
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav>
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Student/Starred">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/Account">Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            )
        }

        if(role==="ROLE_PROMOTOR"){
            buttons = (
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav>
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Student/Starred">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/Account">Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            )
        }

        if(role==="ROLE_COMPANY"){
            buttons = (
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav>
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Student/Starred">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/Account">Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            )
        }

        if(role==="ROLE_ADMIN"){
            buttons = (
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav>
                        <NavDropdown title="Subjects" id="collasible-nav-dropdown">
                            <NavDropdown.Item href="/Subjects">Subject List</NavDropdown.Item>
                            <NavDropdown.Item href="/Subjects/Post">Add Subject</NavDropdown.Item>
                            <NavDropdown.Item href="/Student/Starred">Enlist for Subject</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/Subjects/Review">Approve Subject</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="/Account">Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            )
        }
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
