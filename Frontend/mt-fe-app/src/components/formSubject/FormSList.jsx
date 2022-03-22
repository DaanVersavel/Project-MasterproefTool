import React, {useEffect, useState} from 'react';
import axios from 'axios';

const FormSList = () => {
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        return () => {
            axios
                .get('https://localhost:8080/Subject/')
                .then(response => {
                    console.log(response)
                    setSubjects({subjects: response.data})
                })
                .catch(error => {
                    console.log(error)
                })
        };
    }, []);


    return (
        <div>
            {
                subjects.length ?
                    subjects.map(subjects => <div key={subjects.id}>{subjects.title}</div>) :
                    <div>No subjects available</div>
            }
        </div>
    );
}

export default FormSList;