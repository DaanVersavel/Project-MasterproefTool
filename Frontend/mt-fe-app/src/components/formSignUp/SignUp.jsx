import React, {useEffect, useState} from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import Select from "react-select";
import axios from "../../api/axiosBeforeLogin.js"
import PhoneInput from 'react-phone-number-input'

import './SignUp.css';
import 'react-phone-number-input/style.css'
import KULBuilding from "../../KU-Leuven_branded.jpg"


const SignUp = () => {
    const [success, setSuccess] = useState(false);

    const [optionsRole, setOptionsRole] = useState([]);
    useEffect(() => {
        axios
            .get('/Role')
            .then((response)=> {
                setOptionsRole(response.data);
                optionsRole.forEach(convertObjects(...optionsRole))
                console.log(response.data);
            })
            .catch(error => {
                console.log(error)
            })
    }, []);

    const [optionsDiscipline, setOptionsDiscipline] = useState([]);
    useEffect(() => {
        axios
            .get('/Discipline')
            .then((response)=> {
                setOptionsDiscipline(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log(error)
            })
    }, []);

    const [optionsCampus, setOptionsCampus] = useState([]);
    useEffect(() => {
        axios
            .get('/Campus')
            .then(response => {
                setOptionsCampus(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log(error)
            })
    }, []);

    function successful() {
        setSuccess(true)
    }

    const [readyRoleOptions, setReadyRoleOPtions] = useState([]);

    function convertObjects(student, coordinator, promotor, company){
        setReadyRoleOPtions([
            {value: student, label: 'Student'},
            {value: coordinator, label: 'Coordinator'},
            {value: promotor, label: 'Promotor'},
            {value: company, label: 'Company'}
        ])
    }

    const {
        handleChange, handleChangeStudent,
        handleChangeCoordinator, handleChangePromotor,
        handleChangeCompany, handleSubmit,
        values, studentValues,
        coordinatorValues, promotorValues,
        companyValues, errors
    } = useForm(successful, validate);

    let roleSignUp, roleSpecificSignUp;

    if (values.role === 'ROLE_STUDENT') {
        roleSpecificSignUp = (
            <>
                <div className='form-inputs'>
                    <label className='form-label'>
                        Student number:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter your student number"}
                        type={"text"}
                        id={"studentNumber"}
                        autoComplete={"off"}
                        required
                        value={studentValues.studentNumber}
                        onChange={handleChangeStudent}
                    />
                    {errors.studentNumber && <p>{errors.studentNumber}</p>}
                </div>

                <div className='form-inputs'>
                    <label className='form-label'>
                        Campus:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your campus"}
                        id={"campus"}
                        required
                        options={optionsCampus}
                        value={studentValues.campus}
                        onChange={handleChangeStudent}
                    />
                    {errors.campus && <p>{errors.campus}</p>}
                </div>

                <div className='form-inputs'>
                    <label className='form-label'>
                        Discipline:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your master discipline"}
                        id={"discipline"}
                        required
                        options={optionsDiscipline}
                        value={studentValues.discipline}
                        onChange={handleChangeStudent}
                    />
                    {errors.discipline && <p>{errors.discipline}</p>}
                </div>
            </>
        )
    }
    else if (values.role === 'ROLE_COÖRDINATOR') {
        roleSpecificSignUp = (
            <>
                <div className='form-inputs'>
                    <label className='form-label'>
                        Campus:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your campus"}
                        id={"campus"}
                        required
                        options={optionsCampus}
                        value={coordinatorValues.campus}
                        onChange={handleChangeCoordinator}
                    />
                    {errors.campus && <p>{errors.campus}</p>}
                </div>

                <div className='form-inputs'>
                    <label className='form-label'>
                        Discipline:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your discipline"}
                        id={"discipline"}
                        required
                        options={optionsDiscipline}
                        value={coordinatorValues.discipline}
                        onChange={handleChangeCoordinator}
                    />
                    {errors.discipline && <p>{errors.discipline}</p>}
                </div>
            </>
        )
    }
    else if (values.role === 'ROLE_PROMOTOR') {
        roleSpecificSignUp = (
            <>
                <div className='form-inputs'>
                    <label className='form-label'>
                        Campus:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your campus"}
                        id={"campus"}
                        required
                        options={optionsCampus}
                        value={promotorValues.campus}
                        onChange={handleChangePromotor}
                    />
                    {errors.campus && <p>{errors.campus}</p>}
                </div>

                <div className='form-inputs'>
                    <label className='form-label'>
                        Discipline:
                    </label>
                    <Select
                        className={"form-input"}
                        placeholder={"Select your discipline"}
                        id={"discipline"}
                        required
                        options={optionsDiscipline}
                        value={promotorValues.discipline}
                        onChange={handleChangePromotor}
                    />
                    {errors.discipline && <p>{errors.discipline}</p>}
                </div>
            </>
        )
    }
    else if (values.role === 'ROLE_COMPANY') {
        roleSpecificSignUp = (
            <>
                <div className='form-inputs'>
                    <label className='form-label'>
                        Company name:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter the name of your company"}
                        type={"text"}
                        id={"companyName"}
                        autoComplete={"off"}
                        required
                        value={companyValues.companyName}
                        onChange={handleChangeCompany}
                    />
                    {errors.companyName && <p>{errors.companyName}</p>}
                </div>

                <div className='form-inputs'>
                    <label className='form-label'>
                        VAT number:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter the VAT number of the company"}
                        type={"text"}
                        id={"vat"}
                        autoComplete={"off"}
                        required
                        value={companyValues.vat}
                        onChange={handleChangeCompany}
                    />
                    {errors.vat && <p>{errors.vat}</p>}
                </div>

            </>
        )
    }

    if (values.role) {
        roleSignUp = (
            <>
                {roleSpecificSignUp}

                <div className='form-inputs'>
                    <label className='form-label'>
                        First name:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter your first name"}
                        type={"text"}
                        id={"firstName"}
                        autoComplete={"off"}
                        required
                        value={values.firstName}
                        onChange={handleChange}
                    />
                    {errors.firstName && <p>{errors.firstName}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Last name:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter your last name"}
                        type={"text"}
                        id={"lastName"}
                        autoComplete={"off"}
                        required
                        value={values.lastName}
                        onChange={handleChange}
                    />
                    {errors.lastName && <p>{errors.lastName}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Phone number:
                    </label>
                    <PhoneInput
                        className={"form-input"}
                        placeholder={"Enter your phone number"}
                        id={"phone"}
                        defaultCountry="BE"
                        autoComplete={"off"}
                        required
                        value={values.phone}
                        onChange={handleChange}
                    />
                    {errors.phone && <p>{errors.phone}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Email:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Enter your email"}
                        type={"email"}
                        id={"email"}
                        autoComplete={"off"}
                        required
                        value={values.email}
                        onChange={handleChange}
                    />
                    {errors.email && <p>{errors.email}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Password:
                    </label>
                    <input
                        className='form-input'
                        placeholder='Enter your password'
                        type='password'
                        id='password'
                        required
                        value={values.password}
                        onChange={handleChange}
                    />
                    {errors.password && <p>{errors.password}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Confirm Password:
                    </label>
                    <input
                        className={"form-input"}
                        placeholder={"Confirm your password"}
                        type={"password"}
                        id={"password2"}
                        required
                        value={values.password2}
                        onChange={handleChange}
                    />
                    {errors.password2 && <p>{errors.password2}</p>}
                </div>
            </>
        )
    }

    return (
        <>
            <div className={"form-container"}>
                <div className="form-content-left">
                    <img src={KULBuilding} alt="kuleuven building" className={'form-img'}/>
                </div>
                {success ? (
                    <div className="form-content-right">
                        <h1 className="form-success">Account registered!</h1>
                        <span className='form-input-login'>
                        Return to the <a href='/'>home</a> screen and login.
                    </span>
                    </div>
                ) : (
                    <div className='form-content-right'>
                        <form onSubmit={handleSubmit} className='form' noValidate>
                            <h1>Create your account by filling out the information below.</h1>

                            <div className='form-inputs'>
                                <label className='form-label'>
                                    Role:
                                </label>
                                <Select
                                    className={"form-input"}
                                    placeholder={"Select what role you want to represent"}
                                    id={"role"}
                                    required
                                    options={readyRoleOptions}
                                    value={values.role}
                                    onChange={handleChange}
                                />
                                {errors.role && <p>{errors.role}</p>}
                            </div>

                            {roleSignUp}

                            <button className='form-input-btn' type='submit'>Sign up</button>

                            <span className='form-input-login'>
                                Already have an account? Login <a href='/'>here</a>!
                            </span>
                        </form>
                    </div>
                )}
            </div>
        </>
    );
};

export default SignUp;