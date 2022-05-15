import "./StarredListComponent"
import "./SocialCardStarred.css"
import {motion} from "framer-motion";
import {Button} from "react-bootstrap";
import React from "react";
import {Link} from "react-router-dom";
import {AiOutlineFileText, AiOutlineTeam, AiTwotoneBank} from "react-icons/ai";
import axios from "../../api/axiosAccessToken";



const SocialCard=({subjectData})=>{
    const id=subjectData.id;
    const listDisciplines= subjectData.disciplines.map((d)=>
            <div key={d.naam}><AiTwotoneBank className="side-icon"/>{d.naam}</div>
    );

    const handleUnStar=()=> {
        axios.put(`/Student/StarredRemove/${subjectData.id}`,{
            }).then(response => {
                console.log(response);
        })
    };

    return(
        <div className="card">
            <div className="card__title">{subjectData.title} </div>
            <div className="card-body">
                <div><AiOutlineFileText className={"side-icon"}/>{subjectData.description}</div>
                <div>{listDisciplines}</div>
                <div><AiOutlineTeam className="side-icon"/>Amount of students: {subjectData.aStudents}</div>
            </div>
            <div>
                <Link to={'/Subjects/Details/?id='+id} >
                    <motion.button
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.9 }}
                        className="save-button">
                        Details
                    </motion.button>
                </Link>
                <motion.button
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.9 }}
                    className="save-button"
                    onClick={handleUnStar}>
                    UnStar
                </motion.button>
            </div>


        </div>

    )
};
export default SocialCard;