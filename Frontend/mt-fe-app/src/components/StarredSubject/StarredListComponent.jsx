import React, {useEffect, useState} from 'react';
import SocialCard from "./SocialCardStarred";
import {Button, Container} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import axios from "../../api/axiosAccessToken";

const StarredListComponent = () => {

    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);
    const [allSubjects, setAllSubjects] = useState([]);



    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get('/Student/Starred',{
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

    return(
        <Container>
            {loading && <div>Loading</div>}
            {!loading && (
                <div>
                    <h1>List of Starred Subjects</h1>
                    <div className="cards-container">
                        {subjects.map((subject,index)=>(
                            <SocialCard subjectData={subject} key={index}/>
                        ))}
                    </div>
                </div>
            )}
        </Container>
    )
};
export default StarredListComponent;