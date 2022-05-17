import React, {useEffect, useState} from 'react';
import axios from "../../api/axiosAccessToken";

const BoostStudent = () => {
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        axios
            .get('/Promotor/MySubjects')
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }, []);

    return(
        <>

        </>
    )

};

export default BoostStudent;