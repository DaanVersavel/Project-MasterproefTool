import React, {Component} from 'react';
import {Button, Form, FormControl, FormGroup, FormLabel} from 'react-bootstrap'

class BForm extends Component {
    render() {
        return (
            <Form>
                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text" placeholder="Enter title of the subject" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Description</Form.Label>
                    <Form.Control type="text" placeholder="Enter description of the subject" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Discipline</Form.Label>
                    <Form.Control type="text" placeholder="Enter discipline" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Remark</Form.Label>
                    <Form.Control type="text" placeholder="Enter a remark" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Company</Form.Label>
                    <Form.Control type="text" placeholder="Enter your company" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Coordinator</Form.Label>
                    <Form.Control type="text" placeholder="Enter the coordinator" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Promoter</Form.Label>
                    <Form.Control type="text" placeholder="Enter the promoter" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Boosted student</Form.Label>
                    <Form.Control type="text" placeholder="Enter the boosted student" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>A Students</Form.Label>
                    <Form.Control type="text" placeholder="Enter A students" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Campus</Form.Label>
                    <Form.Control type="text" placeholder="Enter campus" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Education</Form.Label>
                    <Form.Control type="text" placeholder="Enter education" />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    }
}

export default BForm;
