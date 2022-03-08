import React from 'react'

import useForm from "../../hooks/useForm"; //import custom hook
import validateInfo from "./validateInfo"; //logic for errors
import './Form.css'

const FormSubject = ({submitForm}) => {
    const {handleChange, values, handleSubmit, errors} = useForm(submitForm, validateInfo); //Field
    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>To apply a subject, please fill in the information below.</h1>
                <div className="form-inputs">
                    <label htmlFor="title" className="form-label">
                        Title
                    </label>
                    <input
                        id={'title'}
                        type="text"
                        name={'title'}
                        className="form-input"
                        placeholder={"Enter the title of the subject"}
                        value={values.title}
                        onChange={handleChange}
                    />
                    {errors.title && <p>{errors.title}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="description" className="form-label">
                        Description
                    </label>
                    <input
                        id={'description'}
                        type="textarea"
                        name={'description'}
                        className="form-input"
                        placeholder={"Enter a description of the subject"}
                        value={values.description}
                        onChange={handleChange}
                    />
                    {errors.description && <p>{errors.description}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="remarks" className="form-label">
                        Remarks
                    </label>
                    <input
                        id={'remarks'}
                        type="textarea"
                        name={'remarks'}
                        className="form-input"
                        placeholder={"Give some remarks about the subject"}
                        value={values.remarks}
                        onChange={handleChange}
                    />
                    {errors.remarks && <p>{errors.remarks}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="company" className="form-label">
                        Company
                    </label>
                    <input
                        id={'company'}
                        type="text"
                        name={'company'}
                        className="form-input"
                        placeholder={"Enter the name of your company"}
                        value={values.company}
                        onChange={handleChange}
                    />
                    {errors.company && <p>{errors.company}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="coordinator" className="form-label">
                        Coordinator
                    </label>
                    <input
                        id={'coordinator'}
                        type="text"
                        name={'coordinator'}
                        className="form-input"
                        placeholder={"Enter the coordinators' name"}
                        value={values.coordinator}
                        onChange={handleChange}
                    />
                    {errors.coordinator && <p>{errors.coordinator}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="promotor" className="form-label">
                        Promotor
                    </label>
                    <input
                        id={'promotor'}
                        type="text"
                        name={'promotor'}
                        className="form-input"
                        placeholder={"Enter the promotors' name"}
                        value={values.promotor}
                        onChange={handleChange}
                    />
                    {errors.promotor && <p>{errors.promotor}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="disciplines" className="form-label">
                        Disciplines
                    </label>
                    <input
                        id={'disciplines'}
                        type="select"
                        name={'disciplines'}
                        className="form-input"
                        placeholder={"Select the disciplines to whom you want to offer the subject"}
                        value={values.disciplines}
                        onChange={handleChange}
                    />
                    {errors.disciplines && <p>{errors.disciplines}</p>}
                </div>
                <div className="form-inputs">
                    <label htmlFor="aStudents" className="form-label">
                        Amount of students
                    </label>
                    <input
                        id={'aStudents'}
                        type="number"
                        name={'aStudents'}
                        className="form-input"
                        placeholder={"Enter the amount of students needed for this subject"}
                        value={values.aStudents}
                        onChange={handleChange}
                    />
                    {errors.aStudents && <p>{errors.aStudents}</p>}
                </div>
                <button className="form-input-btn" type="submit">Submit</button>
                <span className="form-input-questions">
                        Don't know what to fill in? Press <a href="#">here</a>
                </span>
            </form>
        </div>
    );
}

export default FormSubject;