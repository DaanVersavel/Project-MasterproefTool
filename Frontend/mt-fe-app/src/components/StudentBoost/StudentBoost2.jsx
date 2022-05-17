import React, {useCallback, useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
import Select from "react-select";

const StudentBoost2 = () =>{

    const [chosenSubject, setChosenSubject]=useState([]);
    const [options, setOptions]=useState([]);
    const [firstChoiceStudents,setFirstChoiceStudents]=useState([]);


    useEffect(()=> {
        axios
            .get('/Promotor/MySubjects')
            .then((response) => {
                console.log(response)
                setOptions(response.data.map((subject) => ({
                    value: subject.title,
                    label: subject.title,
                    id: subject.id
                })))
            })
            .catch(error => {
                console.error(error.message);
            })
    },      []);

    function showChosenSubject() {
        console.log(chosenSubject);
    }
    let temp;
    const handleChange =(value)=>{
        temp=value.id;
        console.log(temp);
        setChosenSubject(value)
        findStudents();
        console.log(firstChoiceStudents);
    }
    const findStudents=()=> {
        try{
            const {data:response} =  axios.get(`/Promotor/MySubjects/Students/${temp}`);
            setFirstChoiceStudents(response);
        }catch(error){
            console.error(error.message);
        }
    }
    return (
        <>
            <h1>List Subjects and their students</h1>
            <div>
                <Select
                    name={"subjectList"}
                    value={chosenSubject}
                    placeholder={"Select a subject."}
                    options={options}
                    onChange={handleChange}
                />


            </div>
        </>
    )
}
export default StudentBoost2;