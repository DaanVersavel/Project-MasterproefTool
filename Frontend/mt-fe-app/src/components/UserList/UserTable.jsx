import React from 'react';
import "./UserTable.css";
import "./UserList";
import {Button} from "react-bootstrap";
import axios from '../../../../mt-fe-app/src/api/axiosAccessToken'


function UserTable({userData}) {
    const deleteHandler = (e,id) =>{
        axios.delete(`/User/delete/${id}`,{}
        ).then(response=>{
            console.log(response)
        }).catch(error => {
            console.log(error)
        });
    }


    return (
        <div className={"app-container"}>
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Surname</th>
                        <th>E-mail</th>
                        {/*<th>Role</th>*/}
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                {userData.map((user)=>
                <tr >
                    <td>{user.firstName}</td>
                    <td>{user.surname}</td>
                    <td>{user.email}</td>
                    {/*<td>{user.roles.roleName}</td>*/}
                        <td>
                            <button onClick={deleteHandler(user.id)}>Delete</button>
                        </td>
                </tr>
                )}

                </tbody>
            </table>
        </div>
    );
}

export default UserTable;