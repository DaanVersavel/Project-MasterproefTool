import "./FormSList"
import "./SocialCard.css"

const SocialCard=({subjectData})=>{
    return(
      <div className="card">
            <div className="card__title">{subjectData.title} </div>

            <div className="card-body">
                <div>{subjectData.description}</div>
                <div>Amount of students: {subjectData.aStudents}</div>
            </div>
      </div>

    )
};
export default SocialCard;