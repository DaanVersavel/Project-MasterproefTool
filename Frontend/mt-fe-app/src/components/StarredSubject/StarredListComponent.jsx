import React, {useEffect, useState} from 'react';
import SocialCard from "./SocialCardStarred";
import {Button, Container} from "react-bootstrap";
import axios from "../../api/axiosAccessToken";
import Select from "react-select";
import Popup from "./Popup";

const StarredListComponent = () => {

    const [loading, setLoading] = useState(true);
    const [subjects, setSubjects] = useState([]);
    const [choiceSubjects, setChoiceSubjects] = useState({
        firstChoice: "",
        secondChoice: "",
        thirdChoice:""
    });
    const[copySubjects,setCopySubjects] = useState([]);

    //triggers voor popup: gelukt en mislukt
    const [buttonPopupSucceed, setButtonPopupSucceed] = useState(false);
    const [buttonPopupFailed, setButtonPopupFailed] = useState(false);


    useEffect(() => {
        const fetchSubjects = async () =>{
            setLoading(true);
            try {
                const {data: response} = await axios.get('/Student/Starred',{

                });
                setSubjects(response);
                setCopySubjects(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }
        fetchSubjects()
    }, []);

    const handleSubmit=(e) =>{
        e.preventDefault();
        console.log("eerste keuze: ",choiceSubjects.firstChoice);
        console.log("tweede keuze: ",choiceSubjects.secondChoice);
        console.log("derde keuze: ",choiceSubjects.thirdChoice);
        if(choiceSubjects.firstChoice!==choiceSubjects.secondChoice && choiceSubjects.secondChoice!==choiceSubjects.thirdChoice && choiceSubjects.thirdChoice!=choiceSubjects.firstChoice){
            let firstChoiceId=choiceSubjects.firstChoice.id;
            let secondChoiceId=choiceSubjects.secondChoice.id;
            let thirdChoiceId=choiceSubjects.thirdChoice.id;

            axios.put(`/Student/Starred/FirstChoise/${firstChoiceId}`,{firstChoiceId},{
            })
            .then(response=>{
                console.log(response)
            }).catch(error => {
                console.log(error)
            });

            axios.put(`/Student/Starred/SecondChoise/${secondChoiceId}`,{secondChoiceId},{
            })
            .then(response=>{
                console.log(response)
            }).catch(error => {
                console.log(error)
            });

            axios.put(`/Student/Starred/ThirdChoise/${thirdChoiceId}`,{thirdChoiceId},{
            })
            .then(response=>{
                console.log(response)
            }).catch(error => {
                console.log(error)
            });
            setButtonPopupSucceed(true);
        }else{
            console.log("ongeldige keuze onderwerpen")
            setButtonPopupFailed(true);
        }
    }

    const handleFirstSelect = firstChoice => {
        setChoiceSubjects({...choiceSubjects,firstChoice})
    }

    const handleSecondSelect = secondChoice => {
        setChoiceSubjects({...choiceSubjects,secondChoice})
    }

    const handleThirdSelect = thirdChoice => {
        setChoiceSubjects({...choiceSubjects,thirdChoice})
    }
    return(
        <Container>
            {loading && <div>Loading</div>}
            {!loading && (
                <div>
                    <h1>List of Starred Subjects</h1>
                    <div className="cards-container">
                        {subjects.map((subject,index)=>(
                            <SocialCard subjectData={subject} key={index}/>
                        ))}
                    </div>
                </div>
            )}

            <table>
                <tbody>
                    <tr>
                        <td>First choice: </td>
                        <td>
                            <Select
                                name={"firstChoice"}
                                value={choiceSubjects.firstChoice}
                                placeholder={"Select a first choice"}
                                options={copySubjects}
                                onChange={handleFirstSelect}
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>Second choice: </td>
                        <td>
                            <Select
                                name={"secondChoice"}
                                value={choiceSubjects.secondChoice}
                                placeholder={"Select a second choice"}
                                options={copySubjects}
                                onChange={handleSecondSelect}
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>Third choice: </td>
                        <td>
                            <Select
                            name={"thirdChoice"}
                            value={choiceSubjects.thirdChoice}
                            placeholder={"Select a third choice"}
                            options={copySubjects}
                            onChange={handleThirdSelect}
                            />
                        </td>
                    </tr>
                </tbody>
            </table>
            <Button onClick={handleSubmit}>Submit</Button>
            <Popup trigger={buttonPopupSucceed} setTrigger={setButtonPopupSucceed}>
                <h3>Choices have been succesfully submitted.</h3>
            </Popup>
            <Popup trigger={buttonPopupFailed} setTrigger={setButtonPopupFailed}>
                <h3>Choices not submitted. Check if a subject isn't selected multiple times.</h3>
            </Popup>

        </Container>

    )
};
export default StarredListComponent;