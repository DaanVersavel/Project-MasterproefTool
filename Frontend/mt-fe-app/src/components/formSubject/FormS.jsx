import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import {Button, Col, FloatingLabel, Form, Row} from "react-bootstrap";

const FormS = () => {
    const [formData, setFormData] = useState({
        title: "",
        company: "",
        description: "",
        remark: "",
        coordinator: "",
        promoter: "",
        disciplines: "",
        aStudents: ""
    })

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(formData)
        axios
            .post('https://localhost:8080/Subject/Post', formData)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
    }

    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/listOfSubjects");
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Button onClick={handleClick}>
                View all available subjects
            </Button>

            <Row className="mb-3">
                <Form.Group as={Col} controlId="title">
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter subject title"
                        value={formData.title}
                        onChange={(e) => setFormData({...formData, title: e.target.value})}
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="company">
                    <Form.Label>Company</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter company name"
                        value={formData.company}
                        onChange={(e) => setFormData({...formData, company: e.target.value})}
                    />

                </Form.Group>
            </Row>

            <FloatingLabel controlId="description" label="Description">
                <Form.Control
                    as="textarea"
                    placeholder="Give a description about the subject"
                    style={{ height: '100px' }}
                    value={formData.description}
                    onChange={(e) => setFormData({...formData, description: e.target.value})}
                />
            </FloatingLabel>

            <FloatingLabel controlId="remark" label="Remark">
                <Form.Control
                    as="textarea"
                    placeholder="Give some remarks about the subject"
                    style={{ height: '80px' }}
                    value={formData.remark}
                    onChange={(e) => setFormData({...formData, remark: e.target.value})}
                />
            </FloatingLabel>

            <Row className="mb-3">
                <Form.Group as={Col} controlId="coordinator">
                    <Form.Label>Coordinator</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Name of the coordinator"
                        value={formData.coordinator}
                        onChange={(e) => setFormData({...formData, coordinator: e.target.value})}
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="promoter">
                    <Form.Label>Promoter</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Name of the promotor"
                        value={formData.promoter}
                        onChange={(e) => setFormData({...formData, promoter: e.target.value})}
                    />
                </Form.Group>
            </Row>

            <FloatingLabel controlId="disciplines" label="Disciplines">
                <Form.Select
                    aria-label="Floating label select example"
                    value={formData.disciplines}
                    onChange={(e) => setFormData({...formData, disciplines: e.target.value})}
                >
                    <option>Select the discipline for whom this subject maybe of interest</option>
                    <option value="1">Erasmus Mundus Japan - Master of Science in Imaging and Light in Extended Reality (Ghent et al)</option>
                    <option value="2">Master in de industriële wetenschappen: biochemie (Gent)</option>
                    <option value="3">Master in de industriële wetenschappen: bouwkunde (Gent)</option>
                    <option value="4">Master of Civil Engineering Technology (Ghent)</option>
                    <option value="5">Master in de industriële wetenschappen: chemie (Gent)</option>
                    <option value="6">Master in de industriële wetenschappen: elektromechanica (Gent)</option>
                    <option value="7">Master in de industriële wetenschappen: elektronica-ICT (Gent)</option>
                    <option value="8">Master in de industriële wetenschappen: energie (Gent)</option>
                    <option value="9">European Master of Food Science, Technology and Business (no new enrollments as from 2020-2021) (Ghent et al)</option>
                    <option value="10">European Master of Sustainable Food Systems Engineering, Technology and Business (Ghent et al)</option>
                    <option value="11">Educatieve master in de wetenschappen en technologie (verkort programma) (Gent)</option>
                </Form.Select>
            </FloatingLabel>


            <div title={"Amount of students"} id={"aStudents"}>
                <Form.Check
                    inline
                    label="1"
                    name="group1"
                    type={"radio"}
                    id={`radio1`}
                    value={formData.aStudents}
                    onChange={(e) => setFormData({...formData, aStudents: e.target.value})}
                />
                <Form.Check
                    inline
                    label="2"
                    name="group1"
                    type={"radio"}
                    id={`radio2`}
                    value={formData.aStudents}
                    onChange={(e) => setFormData({...formData, aStudents: e.target.value})}
                />
                <Form.Check
                    inline
                    name="group1"
                    label="3"
                    type={"radio"}
                    id={`radio3`}
                    value={formData.aStudents}
                    onChange={(e) => setFormData({...formData, aStudents: e.target.value})}
                />
            </div>

            <Button variant="primary" type="submit" >
                Submit
            </Button>
        </Form>
    );
};

export default FormS;
