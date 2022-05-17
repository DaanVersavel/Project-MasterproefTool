import axios from "axios";

export default axios.create({
    // baseURL: "http://localhost:8080",
    baseURL: "https://masterprooftoolbackend.herokuapp.com",
    headers: {
        authorization: `Bearer ${sessionStorage.getItem('refresh_token')}`,
        'content-type': 'application/json'
    }
})