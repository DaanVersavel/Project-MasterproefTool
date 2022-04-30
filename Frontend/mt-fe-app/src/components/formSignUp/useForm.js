import { useState, useEffect } from 'react';
import axios from "../../api/axios.js";

const useForm = (callback, validate) => {
    const [values, setValues] = useState({
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        password: '',
        password2: ''
    });

    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);


    const handleChange = e => {
        const { id, value } = e.target;
        setValues({...values, [id]: value});
    };

    const handleSubmit = e => {
        e.preventDefault();
        console.log(values)

        setErrors(validate(values));
        setIsSubmitting(true);

        axios
            .post("/User/users/save", {values})
            .then((response) => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            });
    };

    // only submit if zero errors
    useEffect(
        () => {
            if (Object.keys(errors).length === 0 && isSubmitting) {
                callback();
            }
        },
        [errors]
    );

    return { handleChange, handleSubmit, values, errors };
};

export default useForm;