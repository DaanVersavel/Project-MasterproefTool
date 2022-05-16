import axios from "axios";

export default axios.create({
    //baseURL: "http://localhost:8080",
    baseURL: "https://masterprooftoolbackend.herokuapp.com",
    headers:{
        'content-type': 'application/x-www-form-urlencoded'
    }
})