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

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    }
}

export default BForm;
