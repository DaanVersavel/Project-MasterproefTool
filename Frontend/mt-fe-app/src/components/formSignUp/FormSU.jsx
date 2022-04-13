import React, { useState } from 'react';
import FormSubmition from "./FormSignUp";
import FormSuccess from "./FormSuccess";
import './FormSU.css'
import KULBuilding from "../../KU-Leuven_branded.jpg"

const FormSU = () => {
    const [isSubmitted, setIsSubmitted] = useState(false);

    function submitForm(){
        setIsSubmitted(true)
    }
    return (
        <>
            <div className="form-container">
                {/*<span className={'close-btn'}>x</span>*/}
                <button className={'close-btn'} type={"button"}>x</button>
                <div className="form-content-left">
                    <img src={KULBuilding} alt="kuleuven building" className={'form-img'}/>
                </div>
                {!isSubmitted ? (<FormSubmition submitForm={submitForm} />) : (<FormSuccess />)}
            </div>
        </>
    );
}

export default FormSU;