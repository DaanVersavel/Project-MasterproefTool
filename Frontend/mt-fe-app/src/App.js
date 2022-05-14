import React from 'react';
import './App.css';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import BNavbar from "./components/navbar/BNavbar"
import {AuthProvider} from "./pages/protectedRoutes/Auth";
import {RequireAuth} from "./pages/protectedRoutes/RequireAuth";
import UserList from "./components/UserList/UserList";
import Details from "./pages/details/Details"
import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import Login from "./pages/login/Login";
import Account from "./pages/account/Account"
import ListSubjects from "./pages/subjects/ListOfSubjects"
import AddSubjects from "./pages/subjects/AddSubjects"
import ReviewSubjects from "./pages/subjects/ReviewSubjects";
import ManageUsers from "./pages/users/ManageUsers";
import StarredSubjects from "./pages/subjects/StarredSubjects";
import NoMatch from "./pages/noMatch/NoMatch";

export default function App () {
    return (
        <AuthProvider>
            <Router>
                <BNavbar/>
                <div className={"App"}>
                    <Routes>
                        <Route path = '/Home' element = {<RequireAuth><Home/></RequireAuth>} />
                        <Route path = '/Subjects' element = {<RequireAuth><ListSubjects/></RequireAuth>} />
                        <Route path = '/Subjects/Post' element = {<RequireAuth><AddSubjects/></RequireAuth>} />
                        <Route path = '/Account' element = {<RequireAuth><Account/></RequireAuth>} />
                        <Route path = '/User/users' element={<RequireAuth><UserList/></RequireAuth>} />
                        <Route path = '/User/usermgmt' element = {<RequireAuth><ManageUsers/></RequireAuth>} />
                        <Route path = '/Subjects/Review' element={<RequireAuth><ReviewSubjects/></RequireAuth>} />
                        <Route path = '/Student/Starred' element={<RequireAuth><StarredSubjects/></RequireAuth>} />

                        <Route path = '/*' element = {<NoMatch/>} />
                        <Route path = '/SignUp' element = {<Register/>} />
                        <Route path = '/Login' element = {<Login />} />
                        <Route path = '/Subjects/Details/*' element={<RequireAuth><Details/></RequireAuth>} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}