import React from "react";
import {Button, Container} from "react-bootstrap";
import '../../pages/home/Home.css'

export default function Home() {

    return (
        <Container>
            <div className={'home-block'}>
                <h1>Welcome</h1>
                <h2>What would you like to do?</h2>
                <Button className="btn btn-primary btn-lg btn-block mr-3" href={'/Subjects'} >View all subjects</Button>
                <Button className="btn btn-primary btn-lg btn-block mr-3" href={'/Subjects/Post'}>Submit a subject</Button>
                <Button className="btn btn-primary btn-lg btn-block mr-3" href={'/Student/Starred'}>View your favorite subjects</Button>
            </div>
        </Container>
    );
}