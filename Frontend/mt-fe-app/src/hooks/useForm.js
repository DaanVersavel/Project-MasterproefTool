import {useState, useEffect} from "react"; //import hooks: return variable and function
import axios from 'axios';

const api = axios.create({
    baseURL: 'https://localhost:8080/Subject/Post',
})

const useForm = (callback, validateInfo) => {
    const [values, setValues] = useState({
        title: '',
        description:'',
        remarks: '',
        company: '',
        coordinator: '',
        promotor: '',
        disciplines: '',
        aStudents: ''
    });

    const [errors, setErrors] = useState({})

    const [isSubmitting, setIsSubmitting] = useState(false)

    const handleChange = e => {
        const {name, value} = e.target //destructuring
        setValues({
            ...values,
            [name]: value
        });
    }

    const handleSubmit = e => {
        e.preventDefault(); //When pressing submit, default is reloading page
        axios.post('/', { values })
            .then(res=> {
                console.log(res);
                console.log(res.data);
            })
        setErrors(validateInfo(values))
        setIsSubmitting(true)
    }

    useEffect(() => { //only submit when zero errors
      if(Object.keys(errors).length === 0 && isSubmitting){
          callback();
      }
    }, [errors]) //only trigger when it updates the errors

    return {handleChange, values, handleSubmit, errors}
}

export default useForm;



/*
    state={
       subjects: []
    }

    constructor() {
        super();
        this.setState({subjects : null});
    }

    getSubjects = async () => {
        try{
            let data = await api.get('/').then(({data}) => data);
            this.setState({subjects: data})
        }catch (err){
            console.log(err)
        }
    }

    creatSubject = async () => {
        let res = await api.post('/',{title: 'Test', id:4, author: 'test'})
            .catch(err=> console.log(err))
        console.log(res)
        await this.getSubjects();
    }


    deleteSubject = async (id) => {
        let date = await api.delete('/${id}')
        this.getSubjects();
    }

    updateSubjects = async (id, val) => {
        let data = await api.patch ('/${id}',{title: val})
        this.getSubjects();
    }

<button onClick={this.creatSubject}>creatSubject</button>
                {this.state.subjects.map(subject =>
                    <h2 key={subject.id}>
                        {subject.title}
                        <button onClick={()=>this.deleteSubject(subject.id)}>x</button>
                    </h2>)}
*/