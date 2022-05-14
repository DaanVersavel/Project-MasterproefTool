import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080",
    headers:{
        'content-type': 'json'
    }
})