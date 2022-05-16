import "./StarredListComponent"
import "./SocialCardStarred.css"
import {motion} from "framer-motion";
import React, {useRef} from "react";
import {Link} from "react-router-dom";
import {AiOutlineFileText, AiTwotoneBank} from "react-icons/ai";
import axios from "../../api/axiosAccessToken";
import {Button} from "@progress/kendo-react-buttons";
import '@progress/kendo-theme-default/dist/all.css';
import { PDFExport, savePDF } from "@progress/kendo-react-pdf";

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

    const handlePDFClick=(event) =>{
        ReactToPdfComponent.current.save();

    }

    const ReactToPdfComponent=useRef(null);

    return(
        <div className="card">
            <PDFExport ref={ReactToPdfComponent} paperSize={"A4"}>
                <div className="card__title">{subjectData.title} </div>
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
                <motion.button
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.9 }}
                    className="save-button"
                    onClick={handleUnStar}>
                    UnStar
                </motion.button>
                <div >
                    <Button onClick={handlePDFClick}className="save-button" primary={true}>Download PDF</Button>
                </div>
            </div>


        </div>

    )
};
export default SocialCard;