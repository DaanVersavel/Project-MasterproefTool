import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import "./Login.css"
import axios from '../../api/axiosBeforeLogin'
import {useNavigate} from "react-router-dom";

const Login = () => {
    const [data, setData] = useState({
        email: '',
        password: ''
    })

    const [usersInBackend, setUsersInBackend] = useState([])
    const navigate = useNavigate();


    const handleSubmit = e => {
        e.preventDefault()
        console.log(data)

        axios
            .get('/User/users')
            .then(response => {
                setUsersInBackend(response.data);
            })
            .catch(error => {
                console.log(error)
            })

        if(usersInBackend.find(user => (user.email===data.email)&&(user.password===data.password))) {
            axios
                .get("/login")
                .then((response) => {
                    console.log(response)
                    localStorage.setItem('access_token', response.data.tokens.get('access_token'))
                    localStorage.setItem('refresh_token', response.data.tokens.get('refresh_token'))
                })
                .catch(error => {
                    console.log(error)
                });
            navigate('/');
        }
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

                <Button className={"login-btn btn-block"} disabled={!(data.email || data.password)}>Log in</Button>
                <br/>
                <span>Don't have an account yet? <a href='/SignUp'>Sign up!</a></span>
            </form>
        </>
    )
};

export default Login;