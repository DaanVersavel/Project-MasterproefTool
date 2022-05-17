import React, {useState} from 'react';
import {Button, Container} from "react-bootstrap";
import axios from "../../api/axiosAccessToken";
import NotAssignedTable from "../../components/Assigned/NotAssignedTable";

export default function AssignSubjects() {
    const [assSubjects, setAssSubjects] = useState([]);
    const [notAssSubjects, setNotAssSubjects] = useState([]);

    const showAssigned = () => {
        axios
            .get("/Coordinator/AssignedSubjects")
            .then((response) => {
                console.log(response)
                setAssSubjects(response.data.map((subject) => ({id: subject.id, label: subject.title})))
            })
            .catch((error) => {
                console.log(error)
            })
    }

    let notAssinged;
    const showNotAssigned = () => {
        axios
            .get("/Coordinator/NotAssignedSubjects")
            .then((response) => {
                console.log(response)
                // setNotAssSubjects(response.data.map((subject) => ({id: subject.id, label: subject.title})))
                setNotAssSubjects(response.data)
            })
            .catch((error) => {
                console.log(error)
            })
        notAssinged = (
            <NotAssignedTable notAssSubjects={notAssSubjects}/>
        )
    }

    const handleBoosted = () => {
        axios
            .get("/Coordinator/AutoAssignBoosted")
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const handleFirstChoice = () => {
        axios
            .get("/Coordinator/AutoAssignFirstChoise")
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }


    return (
        <Container>
            <div>
                <Button className="btn btn-primary btn-lg btn-block mr-3" onClick={showAssigned}>Show assigned subjects</Button>
                <Button className="btn btn-primary btn-lg btn-block mr-3" onClick={showNotAssigned}>Show unassigned subjects</Button>
                {notAssinged}
                <Button className="btn btn-primary btn-lg btn-block mr-3" onClick={handleBoosted}>Assign boosted students automatically</Button>
                <Button className="btn btn-primary btn-lg btn-block mr-3" onClick={handleFirstChoice}>Assign students with first choice automatically</Button>
            </div>
        </Container>

    );
}