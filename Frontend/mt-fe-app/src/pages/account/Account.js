import React from 'react';
import {Button, Container, Row} from "react-bootstrap";
import axios from "../../api/axiosAccessToken";

export default function Account() {

    const handleLogout = () => {
        sessionStorage.clear()
        /*axios
            .get('/logout')
            .then((response) => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })*/
    }

    let userId = sessionStorage.getItem('userId')
    const handleDelete = () => {
        axios
            .delete(`/User/delete/${userId}`)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            });

        handleLogout()
    }

    return (
        <Container>
            <h1>This is the account of {sessionStorage.getItem('name')}</h1>
            <table className="table">
                <thead>
                <tr>
                    <th>Firstname</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>Email adress</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>{sessionStorage.getItem('firstName')}</td>
                    <td>{sessionStorage.getItem('surname')}</td>
                    <td>{sessionStorage.getItem('phone')}</td>
                    <td>{sessionStorage.getItem('email')}</td>
                </tr>
                </tfoot>
            </table>
            <Row className={'d-flex justify-content-center'}>
                <Button className="btn btn-primary mr-3" href={"/Edit"}>Edit user info</Button>
                <Button className="btn btn-primary mr-3" onClick={handleLogout} href={"/Login"}>Log out</Button>
                <Button className="btn btn-primary mr-3" onClick={handleDelete} href={"/Login"}>Delete account</Button>
            </Row>
        </Container>

    );
}