//React Hook for keeping state inside function (returns state variable and function)
import React, { useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import BNavbar from './components/BNavbar.jsx'
import BForm from './components/BForm.jsx'

export default function App() {
    return (
        <React.Fragment className={"App"} >
            <BNavbar />
            <BForm />
        </React.Fragment>
    );
}
