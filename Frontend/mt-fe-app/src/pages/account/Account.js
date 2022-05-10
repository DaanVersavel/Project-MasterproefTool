import React from 'react';
import {useAuth} from "../../components/Auth";
import {useNavigate} from "react-router-dom";
import axios from "../../api/axiosAccessToken";

export default function Account() {
    const navigate = useNavigate()
    const auth = useAuth()
    const handleLogout = () => {
        /*axios
            .get('/logout')
            .then((response)=> {
                console.log(response);
            })
            .catch(error => {
                console.log(error)
            })*/

        localStorage.clear();
        auth.logout();
        navigate('/Login');
    }
    return (
        <div>
            Welcome {auth.user}.<button onClick={handleLogout}>Logout</button>
        </div>
    );
}