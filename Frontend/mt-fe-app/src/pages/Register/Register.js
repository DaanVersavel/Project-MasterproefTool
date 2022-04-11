import "./Register.css"
import React from "react";
import {useNavigate} from "react-router-dom";


export default function Register() {
    let navigate=useNavigate();
    const routeChange = () =>{
        let path= `/Login`;
        navigate(path);
    }

    return (
        <form className="register">
            <span className="registerTitle">Register</span>
            <form className="registerForm">
                <label>Name</label>
                <input className="registerInput" type="text" placeholder="Enter your username..." />
                <label>Email</label>
                <input className="registerInput" type="text" placeholder="Enter your email..." />
                <label>Password</label>
                <input className="registerInput" type="password" placeholder="Enter your password..." />
                <button className="registerButton">Register</button>
            </form>
            <button className="registerLoginButton" onClick={routeChange}>Login</button>
        </form>
    )
}