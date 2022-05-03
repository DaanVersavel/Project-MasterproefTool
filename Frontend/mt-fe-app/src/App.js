import React, {useEffect, useState} from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import BNavbar from "./components/navbar/BNavbar"

import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import Account from "./pages/account/Account"
import Contact from "./pages/contact/Contact"
import ListSubjects from "./pages/subjects/ListOfSubjects"
import AddSubjects from "./pages/subjects/AddSubjects"

export default function App () {
    const [user, setUser] = useState("");

    function configureUser(userdata) {
        setUser(userdata)
    }

    return (
        <Router>
            <BNavbar user={user} setUser={configureUser}/>
            <div className={"App"}>
                <Routes>
                    <Route path = '/Home' element = {<Home user={user} setUser={configureUser}/>} />
                    <Route path = '/SignUp' element = {<Register/>} />
                    <Route path = '/Subjects' element = {<ListSubjects/>} />
                    <Route path = '/Subjects/Post' element = {<AddSubjects/>} />
                    <Route path = '/Account' element = {<Account/>} />
                    <Route path = '/Contact' element = {<Contact/>} />
                </Routes>
            </div>
        </Router>
    );
}
