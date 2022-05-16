import axios from '../../api/axiosAccessToken';
import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import UserTable from "./UserTable";

const UserList = () => {
    const [loading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);
    const [allUsers, setAllUsers] = useState([]);


    useEffect(() => {
        const fetchUsers = async () => {
            setLoading(true);
            try {
                const {data: response} = await axios.get('/User/users');
                setUsers(response);
                setAllUsers(response);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }

        fetchUsers()
    }, []);
    const filterCards = event => {
        const value = event.target.value.toLowerCase();
        const filteredUsers = allUsers.filter(appUser => (`${appUser.firstName} ${appUser.surname}`.toLowerCase().includes(value)));
        setUsers(filteredUsers);
    };

    return(
      <div>
          {loading && <div>Loading</div>}
          {!loading && (
          <div>
                <h1>List of Registered Users</h1>
                <input className={"search-box"} placeholder={"Search..."} onInput={filterCards}/>
              <div>
                <UserTable userData={users} />
                </div>
          </div>
          )}
      </div>
    );
}
export default UserList;