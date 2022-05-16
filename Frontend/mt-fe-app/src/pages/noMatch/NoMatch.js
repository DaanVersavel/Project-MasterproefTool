import React from 'react';
import {Container} from "react-bootstrap";

export default function NoMatch() {
    setTimeout(function(){
        window.location.href = '/Home';
    }, 100);

    return (
        <Container>
            <h2>Redirecting...</h2>
        </Container>

    );
}

