import React, {useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
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
                const {data: response} = await axios.get('/Subjects',{
                    headers:{
                        'Authorization':'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb3R0ZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFX1NUVURFTlQiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2xvZ2luIiwiZXhwIjoxNjUyMTc3NjgxfQ.3l45RoUlVkr9o6uxKYjRp-7vGMnRUj5XLijVn5HgTws'

                    }
                });
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