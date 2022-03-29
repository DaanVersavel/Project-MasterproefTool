import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Button} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

const FormSList = () => {
    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get('http://localhost:8080/Subjects');
                setSubjects(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }
        fetchSubjects()
    }, []);

    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/Subjects/Post");
    }

    return (
        <div>
            <Button onClick={handleClick}>
                Add subject
            </Button>

            {loading && <div>Loading</div>}
            {!loading && (
                <div>
                    {
                        subjects.length ?
                            subjects.map(item => (<div>{item.title}</div>)) :
                            <div>No subjects available</div>
                    }
                </div>
            )}
        </div>
    );
}

export default FormSList;