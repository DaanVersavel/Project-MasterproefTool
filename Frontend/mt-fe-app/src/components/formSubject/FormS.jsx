import React, {useState} from 'react';
import axios from 'axios';
import {Button, Col, FloatingLabel, Form, Row} from "react-bootstrap";

const FormS = () => {
    const [amountStudents, setAmountStudents] = useState(1)
    const [formData, setFormData] = useState({
        title: "",
        company: "",
        description: "",
        remark: "",
        coordinator: "",
        promoter: "",
        disciplines: "",
        aStudents: amountStudents
    })

    const handleChange = (e) => {
        const{name, value} = e.target;
        setFormData({...formData, [name]: value})
    }

    const handleRadio = (e) => {
        setAmountStudents({amountStudents: e.target.value})
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(formData)
        axios
            .post('http://localhost:8080/Subject/Post', formData)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Row className="mb-3">
                <Form.Group as={Col} controlId="title" >
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                        name={"title"}
                        type="text"
                        placeholder="Enter subject title"
                        value={formData.title}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="company">
                    <Form.Label>Company</Form.Label>
                    <Form.Control
                        name={"company"}
                        type="text"
                        placeholder="Enter company name"
                        value={formData.company}
                        onChange={handleChange}
                    />

                </Form.Group>
            </Row>

            <FloatingLabel controlId="description" label="Description">
                <Form.Control
                    name={"description"}
                    as="textarea"
                    placeholder="Give a description about the subject"
                    style={{ height: '100px' }}
                    value={formData.description}
                    onChange={handleChange}
                />
            </FloatingLabel>

            <FloatingLabel controlId="remark" label="Remark">
                <Form.Control
                    name={"remark"}
                    as="textarea"
                    placeholder="Give some remarks about the subject"
                    style={{ height: '80px' }}
                    value={formData.remark}
                    onChange={handleChange}
                />
            </FloatingLabel>

            <Row className="mb-3">
                <Form.Group as={Col} controlId="coordinator">
                    <Form.Label>Coordinator</Form.Label>
                    <Form.Control
                        name={"coordinator"}
                        type="text"
                        placeholder="Name of the coordinator"
                        value={formData.coordinator}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="promoter">
                    <Form.Label>Promoter</Form.Label>
                    <Form.Control
                        name={"promoter"}
                        type="text"
                        placeholder="Name of the promoter"
                        value={formData.promoter}
                        onChange={handleChange}
                    />
                </Form.Group>
            </Row>

            <FloatingLabel controlId="disciplines" label="Disciplines">
                <Form.Select
                    name = "disciplines"
                    aria-label="Floating label select example"
                    value={formData.disciplines}
                    onChange={handleChange}
                >
                    <option>Select the discipline for whom this subject maybe of interest</option>
                    <option value="1">Erasmus Mundus Japan - Master of Science in Imaging and Light in Extended Reality (Ghent et al)</option>
                    <option value="bc">Master in de industriële wetenschappen: biochemie (Gent)</option>
                    <option value="bk">Master in de industriële wetenschappen: bouwkunde (Gent)</option>
                    <option value="cet">Master of Civil Engineering Technology (Ghent)</option>
                    <option value="c">Master in de industriële wetenschappen: chemie (Gent)</option>
                    <option value="em">Master in de industriële wetenschappen: elektromechanica (Gent)</option>
                    <option value="elict">Master in de industriële wetenschappen: elektronica-ICT (Gent)</option>
                    <option value="e">Master in de industriële wetenschappen: energie (Gent)</option>
                    <option value="fs,t & b">European Master of Food Science, Technology and Business (no new enrollments as from 2020-2021) (Ghent)</option>
                    <option value="sfse, t & b">European Master of Sustainable Food Systems Engineering, Technology and Business (Ghent)</option>
                    <option value="edu">Educatieve master in de wetenschappen en technologie (verkort programma) (Gent)</option>
                </Form.Select>
            </FloatingLabel>


            <div id={"aStudents"}>
                <Form.Check
                    inline
                    name="group1"
                    label="1 student"
                    type={"radio"}
                    id={`aS1`}
                    value={1}
                    onChange={handleRadio}
                />
                <Form.Check
                    inline
                    name="group1"
                    label="2 students"
                    type={"radio"}
                    id={`aS2`}
                    value={2}
                    onChange={handleRadio}
                />
                <Form.Check
                    inline
                    name="group1"
                    label="3 students"
                    type={"radio"}
                    id={`aS3`}
                    value={3}
                    onChange={handleRadio}
                />
            </div>

            <Button variant="primary" type="submit" >
                Submit
            </Button>
        </Form>
    );
};

export default FormS;
