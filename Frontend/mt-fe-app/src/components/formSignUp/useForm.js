import {useState, useEffect} from 'react';
import axios from "../../api/axiosSignUp.js";

const useForm = (fetchData, access_token, successful, validate) => {
    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [role, setRole] = useState('');
    const [campus, setCampus] = useState('');
    const [discipline, setDiscipline] = useState('');
    const [values, setValues] = useState({
        firstName: '',
        surname: '',
        gsm: '',
        email: '',
        password: '',
    });
    const [password2, setPassword2] = useState('');

    const [studentValues, setStudentValues] = useState({
        studentNumber: '',
        campus: null,
        discipline: null
    })

    const [coordinatorValues, setCoordinatorValues] = useState({
        campus: null,
        discipline: null
    })

    const [promotorValues, setPromotorValues] = useState({
        campus: null,
        discipline: null
    })

    const [companyValues, setCompanyValues] = useState({
        companyName: '',
        vat: ''
    })

    const handleCampus = (selectedOption) => {
        setCampus(selectedOption)
    }

    const handleDiscipline = (selectedOption) => {
        setDiscipline(selectedOption)
    }

    const handleChange = e => {
        const {id, value} = e.target;
        setValues({...values, [id]: value});
    };

    const handleRole = (selectedOption) => {
        setRole(selectedOption);
        fetchData()
    }

    const handlePassword2 = (e) => {
        setPassword2(e.target.value)
    }

    const handlePhone = (value) => {
        setValues({...values, ['gsm']: value})
    }

    const handleChangeStudent = e => {
        const {id, value} = e.target;
        setStudentValues({...studentValues, [id]: value});
    };

    const handleChangeCompany = e => {
        const {id, value} = e.target;
        setCompanyValues({...companyValues, [id]: value});
    };

    const handleSubmit = e => {
        e.preventDefault();

        setErrors(validate(role, password2,  values, studentValues, coordinatorValues, promotorValues, companyValues));
        setIsSubmitting(true);

        if(role.value === 'ROLE_STUDENT') {
            setStudentValues({...studentValues, [campus]: campus.value})
            setStudentValues({...studentValues, [discipline]: discipline.value})
            const formData = {...values, ...studentValues}
            axios
                .post("/Student/Save", {formData}, {
                    headers: {
                        Authorization: `Bearer ${access_token}`,
                        'content-type': 'application/json'
                    }
                })
                .then((response) => {
                    console.log(response)
                    alert("You can now login!")
                })
                .catch(error => {
                    alert("Something went wrong, please try to register again.")
                    window.location.reload()
                });
        }
        if(role.value === 'ROLE_COÖRDINATOR') {
            setCoordinatorValues({...coordinatorValues, ['campus']: campus.value})
            setCoordinatorValues({...coordinatorValues, ['discipline']: discipline.value.value})
            const formData = {...values, ...coordinatorValues}
            console.log(formData)
            axios
                .post("/Coordinator/Save", {formData}, {
                    headers: {
                        Authorization: `Bearer ${access_token}`,
                        'content-type': 'application/json'
                    }
                })
                .then((response) => {
                    console.log(response)
                    alert("You can now login!")
                })
                .catch(error => {
                    alert("Something went wrong, please try to register again.")
                    window.location.reload()
                });
        }
        if(role.value === 'ROLE_PROMOTOR') {
            setPromotorValues({...promotorValues, ['campus']: campus.value})
            setPromotorValues({...promotorValues, ['discipline']: discipline.value})
            const formData = {...values, ...promotorValues}
            console.log(formData)
            axios
                .post("/Promotor/Save", {formData}, {
                    headers: {
                        Authorization: `Bearer ${access_token}`,
                        'content-type': 'application/json'
                    }
                })
                .then((response) => {
                    console.log(response)
                    alert("You can now login!")
                })
                .catch(error => {
                    alert("Something went wrong, please try to register again.")
                    window.location.reload()
                });
        }
        if(role.value === 'ROLE_COMPANY') {
            const formData = {...values, ...companyValues}
            console.log(formData)
            axios
                .post("/Company/Save", {formData}, {
                    headers: {
                        Authorization: `Bearer ${access_token}`,
                        'content-type': 'application/json'
                    }
                })
                .then((response) => {
                    console.log(response)
                    alert("You can now login!")
                })
                .catch(error => {
                    alert("Something went wrong, please try to register again.")
                    window.location.reload()
                });
        }
    };

    // only submit if zero errors
    useEffect(
        () => {
            if (Object.keys(errors).length === 0 && isSubmitting) {
                successful();
            }
        },
        [errors, isSubmitting, successful]
    );

    return {
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
        coordinatorValues, promotorValues,
        companyValues, errors
    };
};

export default useForm;