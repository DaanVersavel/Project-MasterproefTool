import React from 'react';
import LoginForm from '../../components/formLogin/Login'
import "../login/Login.css"
import {useAuth} from "../../components/Auth";

export default function Login() {
    const auth = useAuth()
    auth.logout()

    return (
        <div className={"login-container"}>
            <div className={"center login-content"}>
                <h1>Welcome</h1>
                <h4>Please sign in</h4>
                <LoginForm/>
            </div>
        </div>
    );
}