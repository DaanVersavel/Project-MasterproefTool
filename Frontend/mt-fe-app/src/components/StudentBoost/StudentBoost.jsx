import React, {useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
import Select from "react-select";
import BoostTable from "./BoostTable";

const StudentBoost = () =>{

    const [firstChoiceStudents, setFirstChoiceStudents]=useState([]);
    const [options, setOptions] = useState([]);
    const [presentSubject,setPresentSubject] = useState({
        id:""
    });
    let temp;

    let buttons;

    useEffect(() => {
        axios
            .get('/Promotor/MySubjects')
            .then((response) => {
                console.log(response)
                setOptions(response.data.map((subject) => ({value: subject.title, label: subject.title, id: subject.id})))
            })
            .catch (error => {
                console.error(error.message);
            })

    }, []);

    const handleSelect = (selectedOption) => {
            temp=selectedOption.id;
            console.log("temp: ",temp)
            setPresentSubject(temp);
        findStudents();
        console.log(firstChoiceStudents)
        //makeList();
    }

    const findStudents=()=> {
        try{
            const {data:response} =  axios.get(`/Promotor/MySubjects/Students/${temp}`);
            setFirstChoiceStudents(response);
        }catch(error){
            console.error(error.message);
        }
    }

    function makeList() {
        buttons=(
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name </th>
                        <th>Boost</th>
                    </tr>
                </thead>
                <tbody>
                {firstChoiceStudents.map((user)=>
                    <tr>
                        <td>{user.firstName}</td>
                        <td>{user.surname}</td>
                        <td>
                            <button onClick={BoostHandler(user)}>Boost</button>
                        </td>
                    </tr>
                )}
                </tbody>
            </table>
        )
    }

    function BoostHandler(user) {
        axios.put(`/Promotor/MySubjects/${temp}/${user.keyId}`,{},{
        }).then(response=>
            console.log(response)
        )
    }

    return (
        <>
            <h1>List of Subjects and their Students:</h1>
            <div>
                <Select
                    name={"firstChoice"}
                    value={presentSubject}
                    placeholder={"Select a subject"}
                    options={options}
                    onChange={handleSelect}
                />
            </div>
            {buttons}

        </>
    )
}
export default StudentBoost;