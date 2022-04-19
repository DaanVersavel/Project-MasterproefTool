import React, {useState} from "react";
import Login from "../../components/formLogin/Login"
import "./Home.css"

export default function Home(){
    const [user, setUser] = useState("")

    if(user) {
        return (
            <div className={"login-container"}>
                <div className={"center login-content"}>
                    <h1>Welcome</h1>
                    <h4>{user.firstName} {user.lastName}</h4>
                </div>
            </div>
        )
    }

    return(
        <div className={"login-container"}>
            <div className={"center login-content"}>
                <h1>Welcome</h1>
                <h4>Please sign in</h4>
                <Login/>
            </div>
        </div>
    );
}