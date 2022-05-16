import React, {useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
import'./SubjectDetails.css'



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

    function checkSubjects() {
        console.log("subjects:",subjects)
        console.log("Subject promotor", subjects.promotor.firstName)
        console.log("last name",subjects.promotor.surname)

    }

    return(
        <>
            <h1 className={"detailheader"}>{subjects.title}</h1>
            <div className={"detailcontent"}>{subjects.description}</div>
            {/*<button onClick={checkSubjects}>Subject Details</button>*/}
        </>
    )

}
export default SubjectDetails;