import "./FormSList"
import "./SocialCard.css"
import React, {useState} from "react";
import {motion} from "framer-motion";
import Pdf from "react-to-pdf"
import {Link} from "react-router-dom";
import axios from "../../api/axiosAccessToken";
import {AiOutlineFileText, AiOutlineStar, AiOutlineTeam, AiTwotoneBank} from "react-icons/ai";

const ref = React.createRef();



const SocialCard=({subjectData})=>{

    const [style, setStyle] = useState("star-button");
    const listDisciplines= subjectData.disciplines.map((d)=>
        <div key={d.naam}><AiTwotoneBank className="side-icon"/>{d.naam}</div>
    );


    function handleStarClick() {
        changeStyle()
        moveToStar()
    }

    const moveToStar=() =>{
        axios
            .put('/Student/StarredSave/'+subjectData.id,{subjectData},{
            }).then(response => {
                console.log(response);
            })

    };

    const changeStyle=()=>{
        setStyle("star-button2")

    };
    const id=subjectData.id;
    return(

      <div className="card">
        <div className="card__title">{subjectData.title}
            <button
                className={style}
                onClick={handleStarClick}>
                <AiOutlineStar/>
            </button>

        </div>

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
          <Pdf targetRef={ref} filename= {subjectData.title}>
              {({ toPdf }) => <button onClick={toPdf}>Pdf</button>}
          </Pdf>
        </div>
      </div>

    )
};

export default SocialCard;