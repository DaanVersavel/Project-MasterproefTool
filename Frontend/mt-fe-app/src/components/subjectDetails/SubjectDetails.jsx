import React, {useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
import {Button, Container} from "react-bootstrap";
import'./SubjectDetails.css'
import {useParams} from "react-router-dom";
import {stringify} from "qs";


const SubjectDetails = () => {

    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);

    const query=new URLSearchParams(window.location.search);
    console.log(window.location);
    const id=query.get('id');
    console.log(id);
    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get(`/Subjects/${id}`,{

                });
                setSubjects(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }
        fetchSubjects()
    }, []);

    return(
        <>
            <h1>{subjects.title}</h1>
            <div>{subjects.description}</div>
        </>
    )

}
export default SubjectDetails;