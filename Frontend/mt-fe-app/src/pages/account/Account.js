import React from 'react';
import {Button, Container, Row} from "react-bootstrap";
import axios from "../../api/axiosAccessToken";
import {useNavigate} from "react-router-dom";

export default function Account() {
    const navigate=useNavigate()

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

    const handleDelete = () => {
        axios
            .delete(`/User/delete/${sessionStorage.getItem('userID')}`)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            });

        handleLogout()
        navigate('/Login')
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
                    <th>User id</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>{sessionStorage.getItem('firstName')}</td>
                    <td>{sessionStorage.getItem('surname')}</td>
                    <td>{sessionStorage.getItem('phone')}</td>
                    <td>{sessionStorage.getItem('email')}</td>
                    <td>{sessionStorage.getItem('userID')}</td>
                </tr>
                </tfoot>
            </table>
            <Row className={'d-flex justify-content-center'}>
                <Button className="btn btn-block btn-primary mr-3" onClick={handleLogout} href={"/Login"}>Log out</Button>
                <Button className="btn btn-block btn-primary mr-3" onClick={handleDelete}>Delete account</Button>
            </Row>
        </Container>

    );
}