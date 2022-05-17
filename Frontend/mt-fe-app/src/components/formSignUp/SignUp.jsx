import React, {useEffect, useState} from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import Select from "react-select";
import axios from "../../api/axiosSignUp";
import axiosLogin from "../../api/axiosLogin";
import qs from "qs";
import PhoneInput from 'react-phone-number-input'

import './SignUp.css';
import 'react-phone-number-input/style.css'
import KULBuilding from "../../KU-Leuven_branded.jpg"
import {Button} from "react-bootstrap";

const SignUp = () => {
    const [success, setSuccess] = useState(false);
    const optionsRole = [
        {value: 'ROLE_STUDENT', label: 'Student'},
        {value: 'ROLE_COÖRDINATOR', label: 'Coordinator'},
        {value: 'ROLE_PROMOTOR', label: 'Promotor'},
        {value: 'ROLE_COMPANY', label: 'Company'}
    ]
    const [optionsDiscipline, setOptionsDiscipline] = useState([]);
    const [optionsCampus, setOptionsCampus] = useState([]);
    const [access_token, setAccess_token] = useState(null);

    function fetchData() {
        axios
            .get('/Discipline', {
                headers: {
                    Authorization: `Bearer ${access_token}`,
                    'content-type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response.data);
                setOptionsDiscipline(response.data.map((discipline) => ({
                    value: discipline.naam,
                    label: discipline.naam
                })))
            })
            .catch(error => {
                console.log(error)
            })
        axios
            .get('/Campus',{
                headers: {
                    Authorization: `Bearer ${access_token}`,
                    'content-type': 'application/json'
                }
            })
            .then(response => {
                console.log(response.data);
                setOptionsCampus(response.data.map((campus) => ({value: campus.name, label: campus.name})))
            })
            .catch(error => {
                console.log(error)
            })
    }

    useEffect(() => {
        const fetchAccess = async () => {
            axiosLogin
                .post("/login", qs.stringify({
                    email: 'login@gmail.com',
                    password: 'login123'
                }))
                .then((response) => {
                    console.log(response)
                    setAccess_token(response.data['access_token'])
                })
                .catch(error => {
                    console.log(error)
                })
        }
        fetchAccess();
    }, []);

    function successful() {
        setSuccess(true)
    }

    const {
        handleCampus,
        handleDiscipline,
        handleChange,
        handleRole,
        handlePassword2,
        handlePhone,
        handleChangeStudent,
        handleChangeCompany,
        handleSubmit,
        role, campus, discipline,
        password2, values, studentValues,
        companyValues, errors
    } = useForm(fetchData, access_token, successful, validate);

    let roleSignUp, roleSpecificSignUp;

    if (role.value === 'ROLE_STUDENT') {
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
                        value={campus}
                        onChange={handleCampus}
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
                        value={discipline}
                        onChange={handleDiscipline}
                    />
                    {errors.discipline && <p>{errors.discipline}</p>}
                </div>
            </>
        )
    } else if (role.value === 'ROLE_COÖRDINATOR' || role.value === 'ROLE_PROMOTOR') {
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
                        value={campus}
                        onChange={handleCampus}
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
                        value={discipline}
                        onChange={handleDiscipline}
                    />
                    {errors.discipline && <p>{errors.discipline}</p>}
                </div>
            </>
        )
    } else if (role.value === 'ROLE_COMPANY') {
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

    if (role) {
        roleSignUp = (
            <>
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
                        id={"surname"}
                        autoComplete={"off"}
                        required
                        value={values.surname}
                        onChange={handleChange}
                    />
                    {errors.surname && <p>{errors.surname}</p>}
                </div>


                <div className='form-inputs'>
                    <label className='form-label'>
                        Phone number:
                    </label>
                    <PhoneInput
                        className={"form-input"}
                        placeholder={"Enter your phone number"}
                        id={"gsm"}
                        defaultCountry="BE"
                        autoComplete={"off"}
                        required
                        value={values.gsm}
                        onChange={handlePhone}
                    />
                    {errors.gsm && <p>{errors.gsm}</p>}
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
                        autoComplete={"off"}
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
                        autoComplete={"off"}
                        required
                        value={password2}
                        onChange={handlePassword2}
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
                                    options={optionsRole}
                                    value={role}
                                    onChange={handleRole}
                                />
                                {errors.role && <p>{errors.role}</p>}
                            </div>

                            {roleSignUp}

                            {roleSpecificSignUp}

                            <Button className='form-input-btn' type='submit'
                                    disabled={!(role && values.firstName && values.surname && values.email && values.gsm && values.password && password2)}>
                                Sign up
                            </Button>

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