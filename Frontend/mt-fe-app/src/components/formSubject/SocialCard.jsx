import "./FormSList"
import "./SocialCard.css"
import React from "react";
import {motion} from "framer-motion";
import Pdf from "react-to-pdf"
import {useNavigate} from "react-router-dom";
import axios from "../../api/axiosAccessToken";


const ref = React.createRef();

const SocialCard=({subjectData})=>{

    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/Subjects/Details",{id:subjectData.id});
    }

    const moveToStar=() =>{
        axios
            .post('/Student/StarredSave/'+subjectData.id,{subjectData},{
                headers:{
                    'Authorization':'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb3R0ZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFX1NUVURFTlQiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2xvZ2luIiwiZXhwIjoxNjUyMTc3NjgxfQ.3l45RoUlVkr9o6uxKYjRp-7vGMnRUj5XLijVn5HgTws'

                }
            }).then(response => {
                console.log(response);
            })

    }

    return(
      <div className="card">
        <div className="card__title">{subjectData.title}
            <motion.button
                whileHover={{ scale: 1.1 }}
                whileTap={{ scale: 0.9 }}
                className={"save-button"} onClick={moveToStar}>
                Star
            </motion.button>
        </div>
        <div className="card-body">
            <div>{subjectData.description}</div>
            <div>{subjectData.disciplines}</div>
            <div>Amount of students: {subjectData.aStudents}</div>
        </div>
        <div>
          <motion.button
              whileHover={{ scale: 1.1 }}
              whileTap={{ scale: 0.9 }}
              className="save-button"
          onClick={handleClick}>
              Details
          </motion.button>
          <Pdf targetRef={ref} filename= {subjectData.title}>
              {({ toPdf }) => <button onClick={toPdf}>Pdf</button>}
          </Pdf>
        </div>
      </div>

    )
};

export default SocialCard;