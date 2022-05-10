import React from "react";
import axios from "../../api/axiosAccessToken";
import {useAuth} from "../../components/Auth";

export default function Home(){
    const auth = useAuth()

    /*axios
        .get('/whoami')
        .then((response)=> {
            console.log(response);
        })
        .catch(error => {
            console.log(error)
        })*/

    return(
        <h1>Welcome {auth.user}</h1>
    );
}