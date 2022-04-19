import React, { useState, useEffect, useRef } from 'react';
import { faCheck, faTimes, faInfoCircle } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import axios from "../../api/axios";
import './SignUp.css';

// Moet starten met upper of lowercase, gevolgd door 3-23 letters/getallen/koppeltekens/underscores
const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
// Geen spaties, bevat @, geen spaties, bevat ., geen spaties
const EMAIL_REGEX = /\S+@\S+\.\S+/;
// Ten minste 1 lower en 1 uppercase, 1 getal en 1 special character en tssn den 8 en 24 characters
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

const ExampleRegister = () => {
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [validName, setValidName] = useState(false);
    const [userFocus, setUserFocus] = useState(false);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [success, setSuccess] = useState(false);
    const [errMsg, setErrMsg] = useState('');

    useEffect(() => {
        userRef.current.focus();
    }, []) // telkens als de component gemount wordt

    useEffect(() => {
        setValidName(USER_REGEX.test(user));
    }, [user]) // telkens als de user state verandert

    useEffect(() => {
        setValidEmail(EMAIL_REGEX.test(email));
    }, [email])

    useEffect(() => {
        setValidPwd(PWD_REGEX.test(pwd));
        setValidMatch(pwd === matchPwd);
    }, [pwd, matchPwd])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd, matchPwd])

    const handleSubmit = async (e) => {
        e.preventDefault();
        // if button enabled with JS hack, prevent by checking entries
        const v1 = USER_REGEX.test(user);
        const v2 = PWD_REGEX.test(pwd);
        if (!v1 || !v2) {
            setErrMsg("Invalid Entry");
            return;
        }
        // actual submition
        try {
            const response = await axios.post("",
                JSON.stringify({ user, email, pwd }),
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );
            console.log(response.data);
            console.log(JSON.stringify(response))
            setSuccess(true);
            //clear state and controlled inputs
            //need value attrib on inputs for this
            setUser('');
            setEmail('');
            setPwd('');
            setMatchPwd('');
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 409) {
                setErrMsg('Username Taken');
            } else {
                setErrMsg('Registration Failed')
            }
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
                <div className='form-content-right'>
                    <h1 className="form-success">Account registered!</h1>
                    <span className='form-input-login'>
                        Return to <a href='/'>home</a> screen and login.
                    </span>
                </div>
            ) : (
                <div className='form-content-right'>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <form onSubmit={handleSubmit} className='form'>
                        <h1>
                            Create your account by filling out the information below.
                        </h1>


                        <div className='form-inputs'>
                            <label className='form-label'>
                                Username:
                                <FontAwesomeIcon icon={faCheck} className={validName ? "valid" : "hide"} />
                                <FontAwesomeIcon icon={faTimes} className={validName || !user ? "hide" : "invalid"} />
                            </label>
                            <input
                                className={"form-input"}
                                placeholder={"Enter your username"}
                                type={"text"}
                                id={"username"}
                                autoComplete={"off"}
                                onChange={(e) => setUser(e.target.value)}
                                required
                                aria-invalid = {validName ? "false" : "true"}
                                aria-describedby={"uidnote"}
                                onFocus={() => setUserFocus(true)}
                                onBlur={() => setUserFocus(false)}
                            />
                            <p id="uidnote" className={userFocus && user && !validName ? "instructions" : "offscreen"}>
                                <FontAwesomeIcon icon={faInfoCircle} />
                                4 to 24 characters.<br />
                                Must begin with a letter.<br />
                                Letters, numbers, underscores, hyphens allowed.
                            </p>
                        </div>


                        <div className='form-inputs'>
                            <label className='form-label'>
                                Email:
                                <FontAwesomeIcon icon={faCheck} className={validEmail ? "valid" : "hide"} />
                                <FontAwesomeIcon icon={faTimes} className={validEmail || !email ? "hide" : "invalid"} />
                            </label>
                            <input
                                className={"form-input"}
                                placeholder={"Enter your email"}
                                type={"email"}
                                id={"email"}
                                autoComplete={"off"}
                                onChange={(e) => setEmail(e.target.value)}
                                value={user}
                                aria-invalid = {validEmail ? "false" : "true"}
                                aria-describedby={"emailnote"}
                                onFocus={() => setEmailFocus(true)}
                                onBlur={() => setEmailFocus(false)}
                            />
                            <p id="emailnote" className={emailFocus && email && !validEmail ? "instructions" : "offscreen"}>
                                <FontAwesomeIcon icon={faInfoCircle} />
                                No whitespaces.<br />
                                Only valid emails allowed.<br />
                            </p>
                        </div>


                        <div className='form-inputs'>
                            <label className='form-label'>
                                Password:
                                <FontAwesomeIcon icon={faCheck} className={validPwd ? "valid" : "hide"} />
                                <FontAwesomeIcon icon={faTimes} className={validPwd || !pwd ? "hide" : "invalid"} />
                            </label>
                            <input
                                className='form-input'
                                placeholder='Enter your password'
                                type='password'
                                id='password'
                                onChange={(e) => setPwd(e.target.value)}
                                value={pwd}
                                required
                                aria-invalid={validPwd ? "false" : "true"}
                                aria-describedby="pwdnote"
                                onFocus={() => setPwdFocus(true)}
                                onBlur={() => setPwdFocus(false)}
                            />
                            <p id="pwdnote" className={pwdFocus && !validPwd ? "instructions" : "offscreen"}>
                                <FontAwesomeIcon icon={faInfoCircle} />
                                8 to 24 characters.<br />
                                Must include uppercase and lowercase letters, a number and a special character.<br />
                                Allowed special characters:
                                <span aria-label="exclamation mark">!</span>
                                <span aria-label="at symbol">@</span>
                                <span aria-label="hashtag">#</span>
                                <span aria-label="dollar sign">$</span>
                                <span aria-label="percent">%</span>
                            </p>
                        </div>


                        <div className='form-inputs'>
                            <label className='form-label'>
                                Confirm Password:
                                <FontAwesomeIcon icon={faCheck} className={validMatch && matchPwd ? "valid" : "hide"} />
                                <FontAwesomeIcon icon={faTimes} className={validMatch || !matchPwd ? "hide" : "invalid"} />
                            </label>
                            <input
                                className={"form-input"}
                                type={"password"}
                                id={"confirm_pwd"}
                                placeholder={"Confirm your password"}
                                onChange={(e) => setMatchPwd(e.target.value)}
                                value={matchPwd}
                                required
                                aria-invalid={validMatch ? "false" : "true"}
                                aria-describedby="confirmnote"
                                onFocus={() => setMatchFocus(true)}
                                onBlur={() => setMatchFocus(false)}
                            />
                            <p id="confirmnote" className={matchFocus && !validMatch ? "instructions" : "offscreen"}>
                                <FontAwesomeIcon icon={faInfoCircle} />
                                Must match the first password input field.
                            </p>
                        </div>

                        <button className='form-input-btn' disabled={!validName || !validPwd || !validMatch}>Sign up</button>

                        <span className='form-input-login'>
                        Already have an account? Login <a href='/Login'>here</a>
                        </span>
                    </form>
                </div>
            )}
        </>
    );
};

export default ExampleRegister;
