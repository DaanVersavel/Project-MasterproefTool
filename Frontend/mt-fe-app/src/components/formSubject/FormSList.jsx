import React, {useEffect, useState} from 'react';
import axios from 'axios';

const FormSList = () => {
    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get('https://localhost:8080/Subjects/');
                setSubjects(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }

        fetchSubjects()
    }, []);

    return (
        <div>
            {loading && <div>Loading</div>}
            {!loading && (
                <div>
                    {
                        subjects.length ?
                            subjects.map(item => (<span>{item.name}</span>)) :
                            <div>No subjects available</div>
                    }
                </div>
            )}
        </div>
    );
}

export default FormSList;