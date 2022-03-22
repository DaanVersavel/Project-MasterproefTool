import React from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import BNavbar from "./components/navbar/BNavbar"

import Home from "./pages/home/Home"
import SignUp from "./pages/sign up/SignUp"
import Contact from "./pages/contact/Contact"
import Subjects from "./pages/subjects/Subjects"
import List from "./pages/subjects/ListOfSubjects"


export default function App () {
    return (
        <Router>
            <BNavbar/>
            <div className={"App"}>
                <Routes>
                    <Route path = '/' element = {<Home/>} />
                    <Route path = '/subjects' element = {<Subjects/>} />
                    <Route path = '/sign-up' element = {<SignUp/>} />
                    <Route path = '/contact' element = {<Contact/>} />
                    <Route path = '/listOfSubjects' element = {<List/>} />
                </Routes>
            </div>
        </Router>
    );
}
