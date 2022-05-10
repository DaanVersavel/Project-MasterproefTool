import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080",
    headers:{
        'content-type': 'application/x-www-form-urlencoded'
    }
})