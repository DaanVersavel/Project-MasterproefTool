import React from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';

import BNavbar from "./components/navbar/BNavbar"

import Home from "./pages/home/Home";
import Login from "./pages/login/Login"
import SignUp from "./pages/sign up/SignUp";
import Account from "./pages/account/account"
import Contact from "./pages/contact/Contact"
import ListSubjects from "./pages/subjects/Subjects"
import AddSubjects from "./pages/subjects

export default function App () {
    return (
        <Router>
            <BNavbar/>
            <div className={"App"}>
                <Routes>
                    <Route path = '/' element = {<Home/>} />
                    <Route path = '/Login' element = {<Login/>} />
                    <Route path = '/SignUp' element = {<SignUp/>} />
                    <Route path = '/Account' element = {<Account/>} />
                    <Route path = '/Contact' element = {<Contact/>} />
                    <Route path = '/Subjects' element = {<ListSubjects/>} />
                    <Route path = '/Subjects/Post' element = {<AddSubjects/>} />
                </Routes>
            </div>
        </Router>
    );
}
