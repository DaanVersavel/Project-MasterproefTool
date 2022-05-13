import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import axios from '../../api/axiosLogin'
import {useLocation, useNavigate} from "react-router-dom";
import qs from "qs";
import "./Login.css";

const Login = () => {
    const [data, setData] = useState({
        email: '',
        password: ''
    })

    const navigate = useNavigate()
    const location = useLocation()
    const redirectPath = location.state?.path || '/Home'

    const handleSubmit = e => {
        e.preventDefault()
        console.log(data)

        axios
            .post("/login", qs.stringify({
                email: data.email,
                password: data.password
            }))
            .then((response) => {
                console.log(response)
                sessionStorage.setItem('access_token', response.data['access_token'])
                sessionStorage.setItem('refresh_token', response.data['refresh_token'])
                navigate(redirectPath, {replace: true})
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
    }

    const handleChange = e => {
        const {id, value} = e.target;
        setData({...data, [id]: value});
    }

    return (
        <form className="logForm" onSubmit={handleSubmit}>
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
                    required
                    value={data.email}
                    onChange={handleChange}
                />
            </div>

            <div>
                <label htmlFor="password">
                    Password:
                </label>
                <br/>
                <input
                    className={"login-input"}
                    placeholder={"Enter your password"}
                    type={"password"}
                    id={"password"}
                    required
                    value={data.password}
                    onChange={handleChange}
                />
            </div>

            <Button className={"login-btn btn-block"} disabled={!(data.email && data.password)} variant="primary"
                    onClick={handleSubmit}>Log in</Button>
            <br/>
            <span>Don't have an account yet? <a href='/SignUp'>Sign up!</a></span>
        </form>
    )
};

export default Login;