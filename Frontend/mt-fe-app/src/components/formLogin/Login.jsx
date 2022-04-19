import React, {useState} from 'react';
import axios from '../../api/axios'
import {Button} from "react-bootstrap";
import "./Login.css"

const Login = ({setUser}) => {
    const [data, setData] = useState({
        email: '',
        password: ''
    })

    const handleSubmit = e => {
        e.preventDefault()
        console.log(data)

        axios
            .post("", {data})
            .then((response) => {
                console.log(response)
                localStorage.setItem('token', response.data.token)

            })
            .catch(error => {
                console.log(error)
            });
    }

    const handleChange = e => {
        const [id, value] = e.target.value;
        setData({...data, [id]: value});
    }

    return(
        <>
            <form className={"logForm"} onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">
                        Email:
                    </label>
                    <br/>
                    <input
                        className={"login-input"}
                        placeholder={"Enter your email"}
                        type={"email"}
                        id={"email"}
                        onChange={handleChange}
                        value={data.email}
                        required
                    />
                </div>

                <div>
                    <label htmlFor="pwd">
                        Password:
                    </label>
                    <br/>
                    <input
                        className={"login-input"}
                        placeholder={"Enter your password"}
                        type={"password"}
                        id={"pwd"}
                        onChange={handleChange}
                        value={data.password}
                        required
                    />
                </div>

                <Button className={"login-btn btn-block"}>Log in</Button>
                <br/>
                <span>Don't have an account yet? <a href='/SignUp'>Sign up!</a></span>
            </form>
        </>
    )
};

export default Login;