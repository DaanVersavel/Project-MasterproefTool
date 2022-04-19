import React, {useEffect, useState} from 'react';
import axios from '../../api/axios';
import {Button, Container} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import SocialCard from "./SocialCard";
import "./FormSList.css";

const FormSList = () => {
    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);
    const [allSubjects, setAllSubjects] = useState([]);


    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/Subjects/Post");
    }

    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get('/Subjects');
                setSubjects(response);
                setAllSubjects(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }
        fetchSubjects()
    }, []);


    const filterCards = event => {
        const value = event.target.value.toLowerCase();
        const filteredSubjects = allSubjects.filter(subject => (`${subject.title} ${subject.description}`.toLowerCase().includes(value)));
        setSubjects(filteredSubjects);
    };

    return (
         <Container>
             {loading && <div>Loading</div>}
             {!loading && (
                 <div>
                     <h1>List of Approved Subjects</h1>
                     <input className="search-box" placeholder={"Search..."} onInput={filterCards}/>
                     <div className="cards-container">
                         {subjects.map((subject,index)=>(
                             <SocialCard subjectData={subject} key={index}/>
                         ))}
                     </div>
                 </div>
             )}
             <Button onClick={handleClick}>Add subjects</Button>
         </Container>
    )
}

export default FormSList;