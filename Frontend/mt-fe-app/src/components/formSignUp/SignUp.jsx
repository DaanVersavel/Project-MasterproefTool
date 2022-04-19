import React, {useState} from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import './SignUp.css';
import KULBuilding from "../../KU-Leuven_branded.jpg"

const SignUp = () => {
    const [success, setSuccess] = useState(false);

    function succesfull() {
        setSuccess(true)
    }

    const { handleChange, handleSubmit, values, errors } = useForm(succesfull, validate);

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