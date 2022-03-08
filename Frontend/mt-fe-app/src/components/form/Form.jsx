import React, { useState } from 'react';

import FormSubject from "./FormSubject";
import FormSuccess from "./FormSucces";
import './Form.css'

const Form = () => {
    const [isSubmitted, setIsSubmitted] = useState(false);

    function submitForm(){
        setIsSubmitted(true)
    }
    return (
        <>
            <div className="form-container">
                <span className={'close-btn'}>x</span>
                <div className="form-content-left">
                    <img src="" alt="form image" className={'form-img'}/>
                </div>
                {!isSubmitted ? (<FormSubject submitForm={submitForm} />) : (<FormSuccess />)}
            </div>
        </>
    );
}

export default Form;