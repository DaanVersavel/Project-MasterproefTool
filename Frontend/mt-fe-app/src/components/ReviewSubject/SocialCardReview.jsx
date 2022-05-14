import "./FormSubjectReview"
import "./SocialCardReview.css"
import {motion} from "framer-motion";
import {Button} from "react-bootstrap";
import React from "react";
import axios from "../../api/axiosAccessToken";
import {Link} from "react-router-dom";
import {AiOutlineFileText, AiOutlineTeam, AiTwotoneBank} from "react-icons/ai";


const SocialCardReview=({subjectData})=>{

    const listDisciplines= subjectData.disciplines.map((d)=>
        <div key={d.naam}><AiTwotoneBank className="side-icon"/>{d.naam}</div>
    );

    function handleApprove() {
        let subjectId=subjectData.id;
        axios.put(`/Subjects/Approved/${subjectData.id}`,{subjectId},{

        }).then(response=>{
            console.log(response)
        }).catch(error => {
            console.log(error)
        });
    }

    function handleDeny() {
        axios.put(`/Subjects/Denied/${subjectData.id}`)
    }

    return(
        <div className="card">
            <div className="card__title">{subjectData.title} </div>
            <div className="card-body">
                <div><AiOutlineFileText className={"side-icon"}/>{subjectData.description}</div>
                <div>{listDisciplines}</div>
                <div><AiOutlineTeam className="side-icon"/>Amount of students: {subjectData.aStudents}</div>
            </div>
            <div>
                <Link to={'/Subjects/Details/?id='+subjectData.id} >
                    <motion.button
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.9 }}
                        className="save-button">
                        Details
                    </motion.button>
                </Link>

                <Button onClick={handleApprove}>Approve</Button>
                <Button onClick={handleDeny}>Deny</Button>
            </div>

        </div>

    )
};
export default SocialCardReview;