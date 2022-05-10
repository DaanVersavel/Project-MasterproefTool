import React from 'react';
import "./UserTable.css";
import "./UserList";
import {Button} from "react-bootstrap";
import axios from '../../../../mt-fe-app/src/api/axiosAccessToken'
import fetchUsers from './UserList.jsx'

function UserTable({userData}) {

    const deleteHandler = (e,id) =>{
        axios.delete("/Users/delete/{id}.json"
        ).then(fetchUsers);
    }

    return (
        <div className={"app-container"}>
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Surname</th>
                        <th>E-mail</th>
                        <th>Role</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody >
                {userData.map((user)=>
                <tr >
                    <td>{user.firstName}</td>
                    <td>{user.surname}</td>
                    <td>{user.email}</td>
                    <td>{user.roles.roleName}</td>
                        <td>
                            <Button onClick={() =>deleteHandler}>Delete</Button>
                        </td>
                </tr>
                )}

                </tbody>
            </table>
        </div>
    );
}

export default UserTable;