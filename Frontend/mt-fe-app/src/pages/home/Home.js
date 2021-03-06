import React from "react";
import {Button, Container} from "react-bootstrap";
import '../../pages/home/Home.css'

export default function Home() {

    return (
        <Container>
            <div className={'home-block'}>
                <h1>Welcome</h1>
                <h2>What would you like to do?</h2>
                <div className=" text-center mt-4">
                    <Button className="btn mr-3 btn-info" href={'/Subjects'} >View all subjects</Button>
                    <Button className="btn btn-info" href={'/Student/Starred'}>View your favorite subjects</Button>
                    <Button className="btn ml-3 btn-mb3 btn-info" href={'/Subjects/Post'}>Submit a subject</Button>
                </div>

            </div>
        </Container>
    );
}