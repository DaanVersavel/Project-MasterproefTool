import React from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import BNavbar from "./components/navbar/BNavbar"
import Home from "./pages/home/Home";
import Login from "./pages/login/Login"
import Account from "./pages/account/account"
import SignUp from "./pages/sign up/SignUp";

import Contact from "./pages/contact/Contact"
import AddSubjects from "./pages/subjects/AddSubjects"
import Subjects from "./pages/subjects/ListOfSubjects"


export default function App () {
    return (
        <Router>
            <BNavbar/>
            <div className={"App"}>
                <Routes>
                    <Route path = '/SignUp' element = {<SignUp/>} />
                    <Route path = '/Login' element = {<Login/>} />
                    <Route path = '/' element = {<Home/>} />
                    <Route path = '/Subjects' element = {<Subjects/>} />
                    <Route path = '/Subject/Post' element = {<AddSubjects/>} />
                    <Route path = '/Account' element = {<Account/>} />
                    <Route path = '/Contact' element = {<Contact/>} />
                    <Route path={'/User/users'} element = {} />
                </Routes>
            </div>


        </Router>
    );
}
