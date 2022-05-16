import "./FormSList"
import "./SocialCard.css"
import React, {useState, useRef} from "react";
import {motion} from "framer-motion";
import {Link} from "react-router-dom";
import axios from "../../api/axiosAccessToken";
import {AiOutlineFileText, AiOutlineStar, AiTwotoneBank} from "react-icons/ai";
import '@progress/kendo-theme-default/dist/all.css';
import { Button } from "@progress/kendo-react-buttons";
import { PDFExport, savePDF } from "@progress/kendo-react-pdf";

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

    const handlePDFClick=(event) =>{
        ReactToPdfComponent.current.save();

    }

    const ReactToPdfComponent=useRef(null);
    return(

      <div className="card">
          <PDFExport ref={ReactToPdfComponent} paperSize={"A4"}>
            <div className="card__title">{subjectData.title}
                <div
                    className={style}
                    onClick={handleStarClick}>
                    <AiOutlineStar/>
                </div>

            </div>
            <div className="card-body">
                <div><AiOutlineFileText className={"side-icon"}/>{subjectData.description}</div>
                <div>{listDisciplines}</div>
            </div>
          </PDFExport>
        <div>
            <Link to={'/Subjects/Details/?id='+id} >
              <motion.button
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.9 }}
                  className="save-button">
                  Details
              </motion.button>
            </Link>
            <div >
                <Button onClick={handlePDFClick}className="save-button" primary={true}>Download PDF</Button>
            </div>
        </div>

      </div>

    )
};

export default SocialCard;