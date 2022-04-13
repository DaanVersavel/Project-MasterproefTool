import React, {useState} from 'react';
import "./Login.css";
import LoginForm from "../../components/login/LoginForm";

export default function Login() {
    const adminUser={
        email: "admin@admin.com",
        password: "admin123"
    }

    const[user, setUser] = useState({name:"",email:""});
    const[error, setError]=useState("");

    const login = details =>{
        console.log(details);
        if(details.email===adminUser.email && details.password===adminUser.password){
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

    const logout=()=>{
        setUser({name:"",email:""});
    }

    return (
        <div className="header">
            {(user.email!=="") ?
                (<div className={"welcome"}>
                    <h2>Welcome, <span>{user.name}</span></h2>
                    <button onClick={logout}>Logout</button>
                </div>):
                (<LoginForm login={login} error={error} />)
            }
        </div>

    );
}

