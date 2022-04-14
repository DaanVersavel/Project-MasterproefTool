import React from 'react';
import "./Login.css";
import {useState} from "react";
import LoginForm from "../../components/Login/LoginForm";


export default function Login() {
    const adminUser={
        email: "admin@admin.com",
        password: "admin123"
    }

    const[user, setUser] = useState({name:"",email:""});
    const [error, setError]=useState("");

    const Login=details =>{
        console.log(details);

        if(details.email==adminUser.email && details.password==adminUser.password){
            console.log("logged in.");
            setUser({
                name:details.name,
                email:details.email
            });
        }else{
            console.log("Details do not match.");
            setError("Details do not match.");
        }
    }

    const Logout=()=>{
        setUser({name:"",email:""});
    }

    return (
        <div className="header">
            {(user.email!="") ?(
                <div className={"welcome"}>
                    <h2>Welcome, <span>{user.name}</span></h2>
                    <button onClick={Logout}>Logout</button>
                </div>
            ):(
                <LoginForm Login={Login} error={error} />
            )}


        </div>

    );
}

