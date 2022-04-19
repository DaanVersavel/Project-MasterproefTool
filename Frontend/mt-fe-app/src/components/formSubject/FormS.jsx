import React, {useEffect, useState} from 'react';
import axios from '../../api/axios';
import {Form, Button, Col, Row, ModalTitle, Container} from "react-bootstrap";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';

const animatedComponents = makeAnimated();

const FormS = () => {
    const [aStudents, setAStudents] = useState(0);
    const [options, setOptions] = useState([])
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        remark: "",
        disciplines: new Set(),
        aStudents: aStudents
    });

    useEffect(() => {
        const fetchSubjects = async () =>{
            try {
                const {data: response} = await axios.get('/Disciplines');
                setOptions(response);
            } catch (error) {
                console.error(error.message);
            }
        }
        fetchSubjects()
    }, []);

    const handleChange = (e) => {
        const{name, value} = e.target;
        setFormData({...formData, [name]: value});
    }

    const handleSelect = disciplines => {
        setFormData({...formData, disciplines});
    }

    const handleRadio = (e) => {
        const{label, value} = e.target;
        setAStudents({...e,[label]: value});
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Subjects/Post', {formData},{
            })
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            });
    }

    return (
        <Container>
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="title" >
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
                    style={{ height: '100px' }}
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
                    style={{ height: '100px' }}
                    value={formData.remark}
                    onChange={handleChange}
                />
            </Form.Group>

            <Row className="mb-3">
                <Form.Group as={Col} controlId="disciplines">
                    <ModalTitle>Disciplines</ModalTitle>
                    <Select
                        name = {"disciplines"}
                        value={formData.disciplines}
                        placeholder={"Select discipline(s)"}
                        closeMenuOnSelect={false}
                        isMulti
                        isSearchable
                        components={animatedComponents}
                        options={options}
                        onChange={handleSelect}
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="aStudents">
                    <ModalTitle>Amount of students</ModalTitle>
                    <Form.Check
                        name="amount"
                        label="1 student"
                        inline type="radio"
                        value={1}
                        onChange={handleRadio}
                    />
                    <Form.Check
                        name="amount"
                        label="2 students"
                        inline type={"radio"}
                        value={2}
                        onChange={handleRadio}
                    />
                </Form.Group>
            </Row>

            <Button variant="primary" type="submit" >Submit</Button>
        </Form>
        </Container>
    );
};

export default FormS;
