import React from 'react';
import SignUp from "../../components/formSignUp/SignUp";
import {useAuth} from "../../components/Auth";

export default function Register() {
    const auth = useAuth()
    auth.logout()

    return (
        <div>
            <SignUp/>
        </div>
    );
}

