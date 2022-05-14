import React from 'react';
import {Button} from "react-bootstrap";
import axios from "../../api/axiosAccessToken";

export default function Account() {

    const handleLogout = () => {
        /*axios
            .get('/logout')
            .then((response) => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })*/
        sessionStorage.clear()
    }
    return (
        <div>
            <h1>This is the account of blablabla</h1>
            <Button onClick={handleLogout} href={"/Login"}>Log out</Button>
        </div>
    );
}