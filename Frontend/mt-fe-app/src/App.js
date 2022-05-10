import React from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import BNavbar from "./components/navbar/BNavbar"
import {AuthProvider} from "./components/Auth";
import {RequireAuth} from "./components/RequireAuth";


import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import Login from "./pages/login/Login";
import Account from "./pages/account/Account"
import Contact from "./pages/contact/Contact"
import ListSubjects from "./pages/subjects/ListOfSubjects"
import AddSubjects from "./pages/subjects/AddSubjects"
import NoMatch from "./pages/noMatch/NoMatch";

export default function App () {
    return (
        <AuthProvider>
            <Router>
                <BNavbar/>
                <div className={"App"}>
                    <Routes>
                        <Route path = '/Home' element = {<RequireAuth><Home/></RequireAuth>} />
                        <Route path = '/Contact' element = {<Contact/>} />
                        <Route path = '/SignUp' element = {<Register/>} />
                        <Route path = '/Login' element = {<Login />} />
                        <Route path = '/Subjects' element = {<RequireAuth><ListSubjects/></RequireAuth>} />
                        <Route path = '/Subjects/Post' element = {<RequireAuth><AddSubjects/></RequireAuth>} />
                        <Route path = '/Account' element = {<RequireAuth><Account/></RequireAuth>} />
                        <Route path = '/*' element = {<NoMatch/>} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}