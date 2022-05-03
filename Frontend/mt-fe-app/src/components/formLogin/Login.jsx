import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import axios from '../../api/axiosBeforeLogin'
import qs from 'qs'
import {useNavigate} from "react-router-dom";
import "./Login.css"

const Login = () => {
    const [data, setData] = useState({
        email: '',
        password: ''
    })

    const navigate = useNavigate();

    const handleSubmit = e => {
        e.preventDefault()
        console.log(data)

        axios
            .post("/login",qs.stringify({
                email: data.email,
                password: data.password
            }))
            .then((response) => {
                console.log(response)
                localStorage.setItem('access_token', response.data.get("access_token"))
                localStorage.setItem('refresh_token', response.data.get("refresh_token"))
            })
            .catch(error => {
                console.log(error)
            })
        navigate('/Home')
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

            <Button className={"login-btn btn-block"} disabled={!(data.email && data.password)} variant="primary" type="submit">Log in</Button>
            <br/>
            <span>Don't have an account yet? <a href='/SignUp'>Sign up!</a></span>
        </form>
    )
};

export default Login;