import {useState, useEffect} from 'react';
import axios from "../../api/axiosNoToken.js";

const useForm = (successful, validate) => {
    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const [values, setValues] = useState({
        role: '',
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        password: '',
        password2: ''
    });

    const [studentValues, setStudentValues] = useState({
        studentNumber: '',
        campus: '',
        discipline: ''
    })

    const [coordinatorValues, setCoordinatorValues] = useState({
        campus: '',
        discipline: ''
    })

    const [promotorValues, setPromotorValues] = useState({
        campus: '',
        discipline: ''
    })

    const [companyValues, setCompanyValues] = useState({
        companyName: '',
        vat: ''
    })

    const handleChange = e => {
        const {id, value} = e.target;
        setValues({...values, [id]: value});
    };

    const handleChangeStudent = e => {
        const {id, value} = e.target;
        setStudentValues({...studentValues, [id]: value});
    };

    const handleChangeCoordinator = e => {
        const {id, value} = e.target;
        setCoordinatorValues({...coordinatorValues, [id]: value});
    };

    const handleChangePromotor = e => {
        const {id, value} = e.target;
        setPromotorValues({...promotorValues, [id]: value});
    };

    const handleChangeCompany = e => {
        const {id, value} = e.target;
        setCompanyValues({...companyValues, [id]: value});
    };

    const handleSubmit = e => {
        e.preventDefault();
        console.log(values)

        setErrors(validate(values, studentValues, coordinatorValues, promotorValues, companyValues));
        setIsSubmitting(true);

        if(values.role === 'student') {
            axios
                .post("/Student/Save", {values, studentValues})
                .then((response) => {
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
                });
        }
        if(values.role === 'coordinator') {
            axios
                .post("/Coordinator/Save", {values, coordinatorValues})
                .then((response) => {
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
                });
        }
        if(values.role === 'promotor') {
            axios
                .post("/Promotor/Save", {values, promotorValues})
                .then((response) => {
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
                });
        }
        if(values.role === 'company') {
            axios
                .post("/Company/Save", {values, companyValues})
                .then((response) => {
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
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
        [errors]
    );

    return {
        handleChange, handleChangeStudent,
        handleChangeCoordinator, handleChangePromotor,
        handleChangeCompany, handleSubmit,
        values, studentValues,
        coordinatorValues, promotorValues,
        companyValues, errors
    };
};

export default useForm;