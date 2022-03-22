import React, { useState } from 'react';
import FormSubmition from "./FormSignUp";
import FormSuccess from "./FormSucces";
import './FormSU.css'

const FormSU = () => {
    const [isSubmitted, setIsSubmitted] = useState(false);

    function submitForm(){
        setIsSubmitted(true)
    }
    return (
        <>
            <div className="form-container">
                <span className={'close-btn'}>x</span>
                <div className="form-content-left">
                    {/*<img src="" alt="form image" className={'form-img'}/>*/}
                </div>
                {!isSubmitted ? (<FormSubmition submitForm={submitForm} />) : (<FormSuccess />)}
            </div>
        </>
    );
}

export default FormSU;