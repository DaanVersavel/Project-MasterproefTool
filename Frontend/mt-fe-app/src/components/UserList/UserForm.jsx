import React,{useEffect,useState} from "react";
import axios from '../../../../mt-fe-app/src/api/axiosAccessToken';
import {Form, Button, Col, Row, ModalTitle, Container} from "react-bootstrap";
import Select from 'react-select';




const UserForm=()=>{

    const [options, setOptions] = useState([]);
    const [role, setRole] = useState("");
    const [formData, setFormData]=useState({
        firstName:"",
        surname:"",
        GSM:"",
        email:"",
        password:"",
        roles:"",
        campus:"",
        discipline:"",
        studentNumber:"",
        btwnummer:""
    });


    useEffect(() => {
        const fetchSubjects = async () =>{
            try {
                const {data: response} = await axios.get('/Role');
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

    const handleSelect = roles => {
        setFormData({...formData, roles});
    }


    const handleSubmitCoordinator = (e) => {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Coordinator', {formData},{

            })
            .then(response =>{
                console.log(response)
            })
            .catch(error =>{
                console.log(error)
            });
    }

    const handleSubmitCompany = (e)=> {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Company', {formData},{

            })
            .then(response =>{
                console.log(response)
            })
            .catch(error =>{
                console.log(error)
            });
    }

    const handleSubmitStudent = (e)=> {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Student', {formData},{

            })
            .then(response =>{
                console.log(response)
            })
            .catch(error =>{
                console.log(error)
            });
    }

    const handleSubmitPromotor = (e) => {
        e.preventDefault();
        console.log(formData);
        axios
            .post('/Promotor', {formData},{

            })
            .then(response =>{
                console.log(response)
            })
            .catch(error =>{
                console.log(error)
            });
    }

    let buttons;
    if(role==="ROLE_COÖRDINATOR"){
        buttons=(
            <Form onSubmit={handleSubmitCoordinator}>
                <Form.Group controlId={"campusvanCoördinator"}>
                    <ModalTitle>Campus</ModalTitle>
                    <Form.Control
                        name={"campus"}
                        type={"text"}
                        placeholder={"Enter name of campus."}
                        value={formData.campus}
                        onChange={handleChange}
                        />
                </Form.Group>
                <Form.Group controlId={"disciplinevanCoördinator"}>
                    <ModalTitle>Discipline</ModalTitle>
                    <Form.Control
                        name={"discipline"}
                        type={"text"}
                        placeholder={"Enter name of discipline."}
                        value={formData.discipline}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Button variant={"primary"} type={"submit"}>Submit</Button>
            </Form>
        )
    }
    else if(role==="ROLE_STUDENT"){
        buttons=(
            <Form onSubmit={handleSubmitStudent}>
                <Form.Group controlId={"campusvanStudent"}>
                    <ModalTitle>Campus</ModalTitle>
                    <Form.Control
                        name={"campus"}
                        type={"text"}
                        placeholder={"Enter name of campus."}
                        value={formData.campus}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group controlId={"studentNumber"}>
                    <ModalTitle>Student Number</ModalTitle>
                    <Form.Control
                        name={"studentNumber"}
                        type={"text"}
                        placeholder={"Enter number of student."}
                        value={formData.studentNumber}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group controlId={"disciplinevanCoördinator"}>
                    <ModalTitle>Discipline</ModalTitle>
                    <Form.Control
                        name={"discipline"}
                        type={"text"}
                        placeholder={"Enter name of discipline."}
                        value={formData.discipline}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Button variant={"primary"} type={"submit"}>Submit</Button>
            </Form>
        )
    }
    else if(role==="ROLE_COMPANY"){
        buttons=(
            <Form onSubmit={handleSubmitCompany}>
                <Form.Group controlId={"firstName"}>
                    <ModalTitle>First Name</ModalTitle>
                    <Form.Control
                        name={"firstName"}
                        type="text"
                        placeholder="Enter first name."
                        value={formData.firstName}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId={"surname"}>
                    <ModalTitle>Surname</ModalTitle>
                    <Form.Control
                        name={"surname"}
                        type="text"
                        placeholder="Enter surname."
                        value={formData.surname}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId={"GSM"}>
                    <ModalTitle>GSM</ModalTitle>
                    <Form.Control
                        name={"GSM"}
                        type="text"
                        placeholder="Enter phone number."
                        value={formData.GSM}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId={"email"}>
                    <ModalTitle>E-mail</ModalTitle>
                    <Form.Control
                        name={"email"}
                        type="email"
                        placeholder="Enter e-mail address."
                        value={formData.email}
                        onChange={handleChange}
                    />
                </Form.Group>

                <Form.Group controlId={"password"}>
                    <ModalTitle>Password</ModalTitle>
                    <Form.Control
                        name={"password"}
                        type="text"
                        placeholder="Enter password."
                        value={formData.password}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group controlId={"btwNummer"}>
                    <ModalTitle>BTW-nummer</ModalTitle>
                    <Form.Control
                        name={"btwnummer"}
                        type={"text"}
                        placeholder={"Enter BTW-number."}
                        value={formData.btwnummer}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Button variant={"primary"} type={"submit"}>Submit</Button>
            </Form>

        )

    }
    else if (role==="ROLE_PROMOTOR"){
        buttons=(
            <Form onSubmit={handleSubmitPromotor}>
                <Form.Group controlId={"campusvanPromotor"}>
                    <ModalTitle>Campus</ModalTitle>
                    <Form.Control
                        name={"campus"}
                        type={"text"}
                        placeholder={"Enter name of campus."}
                        value={formData.campus}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group controlId={"disciplinevanPromotor"}>
                    <ModalTitle>Discipline</ModalTitle>
                    <Form.Control
                        name={"discipline"}
                        type={"text"}
                        placeholder={"Enter name of discipline."}
                        value={formData.discipline}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Button variant={"primary"} type={"submit"}>Submit</Button>
            </Form>
        )
    }
    return(
        <Container>
            <Row className={"mb-3"}>
                <Form.Group as ={Col} controlId={"roles"}>
                    <ModalTitle>Rol</ModalTitle>
                    <Select
                        name={"roles"}
                        value={formData.roles}
                        placeholder={"Select which role you fulfill."}
                        closeMenuOnSelect={true}
                        isSearchable
                        options={options}
                        onChange={handleSelect}
                    />
                </Form.Group>
            </Row>
            {buttons}
        </Container>
    );
}
export default UserForm;