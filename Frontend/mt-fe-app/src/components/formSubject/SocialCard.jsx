import "./FormSList"
import "./SocialCard.css"
import {useState} from "react";
import {motion} from "framer-motion";
import Modal from "../modal/index.jsx";


const SocialCard=({subjectData})=>{

    //const ivm knop om scherm te openen.
    const [modalOpen, setModalOpen] = useState(false);

    const close = () => setModalOpen(false);
    const open = () => setModalOpen(true);






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
                  onClick={() =>  (modalOpen ? close() : open())}
              >
                  Details
              </motion.button>

              {modalOpen && <Modal modalOpen={modalOpen} handleClose={close} />}
          </div>

      </div>

    )
};
export default SocialCard;