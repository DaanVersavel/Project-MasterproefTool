import "./FormSList"
import "./SocialCard.css"
import {motion} from "framer-motion";


const SocialCard=({subjectData})=>{

    return(
      <div className="card">
            <div className="card__title">{subjectData.title} </div>
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
              >
                  Details
              </motion.button>
          </div>

      </div>

    )
};
export default SocialCard;