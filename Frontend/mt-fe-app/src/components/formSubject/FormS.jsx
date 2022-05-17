import React, {useEffect, useState} from 'react';
import axios from '../../api/axiosAccessToken';
import {Button, Col, Container, Form, ModalTitle, Row} from "react-bootstrap";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import '../../components/formSubject/FormS.css'

const animatedComponents = makeAnimated();

const FormS = () => {
    const [disciplineOptions, setDisciplineOptions] = useState([])
    const [campusOptions, setCampusOptions] = useState([])
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        remark: "",
        disciplines: new Set(),
        campuses: new Set(),
        aStudents: null
    });

    useEffect(() => {
        axios
            .get('/Discipline')
            .then((response) => {
                console.log(response.data);
                setDisciplineOptions(response.data.map((discipline) => ({value: discipline.naam, label: discipline.naam})))
            })
            .catch(error => {
                console.log(error)
            })
        axios
            .get('/Campus')
            .then((response) => {
                console.log(response.data);
                setCampusOptions(response.data.map((campus) => ({value: campus.name, label: campus.name})))
            })
            .catch(error => {
                console.log(error)
            })
    }, []);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    }

    const handleDiscipline = disciplines => {
        setFormData({...formData, disciplines});
    }

    const handleCampus = disciplines => {
        setFormData({...formData, disciplines});
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Subjects/Post', {formData})
            .then(response => {
                console.log(response)
                alert("Thank you for your submission")
                window.location = '/Home'
            })
            .catch(error => {
                alert("Something went wrong, please enter your subject again.")
                window.location.reload()
            });
    }

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="title">
                    <ModalTitle>Title</ModalTitle>
                    <Form.Control
                        name={"title"}
                        type="text"
                        placeholder="Enter subject title"
                        value={formData.title}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId="description">
                    <ModalTitle>Description</ModalTitle>
                    <Form.Control
                        name={"description"}
                        as="textarea"
                        placeholder="Give a description about the subject"
                        style={{height: '100px'}}
                        value={formData.description}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId="remark">
                    <ModalTitle>Remarks</ModalTitle>
                    <Form.Control
                        name={"remark"}
                        as="textarea"
                        placeholder="Give some remarks about the subject"
                        style={{height: '100px'}}
                        value={formData.remark}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Row className="mb-3">
                    <Form.Group as={Col} controlId="disciplines">
                        <ModalTitle>Disciplines</ModalTitle>
                        <Select
                            name={"disciplines"}
                            value={formData.disciplines}
                            placeholder={"Select discipline(s)"}
                            closeMenuOnSelect={false}
                            isMulti
                            components={animatedComponents}
                            options={disciplineOptions}
                            onChange={handleDiscipline}
                        />
                    </Form.Group>

                    <Form.Group as={Col} controlId="campuses">
                        <ModalTitle>Campuses</ModalTitle>
                        <Select
                            name={"campuses"}
                            value={formData.campuses}
                            placeholder={"Select campus(es)"}
                            closeMenuOnSelect={false}
                            isMulti
                            components={animatedComponents}
                            options={campusOptions}
                            onChange={handleCampus}
                        />
                    </Form.Group>
                </Row>

                <Form.Group as={Col} controlId="aStudents">
                    <ModalTitle>Amount of students</ModalTitle>
                    <input
                        id={'radio1'}
                        name="aStudents"
                        type="radio"
                        value={1}
                        onChange={handleChange}
                    />
                    <label>1 student</label>
                    <br/>
                    <input
                        id={'radio2'}
                        name="aStudents"
                        type="radio"
                        value={2}
                        onChange={handleChange}
                    />
                    <label>2 students</label>
                </Form.Group>

                <Button variant="primary" type="submit"
                        disabled={!(formData.title && formData.description && formData.remark && formData.disciplines && formData.campuses && formData.aStudents)}
                >Submit</Button>
            </Form>
        </Container>
    );
};

export default FormS;
