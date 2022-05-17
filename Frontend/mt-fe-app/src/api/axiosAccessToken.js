import axios from "axios";

export default axios.create({
    // baseURL: "http://localhost:8080",
    baseURL: "https://masterprooftoolbackend.herokuapp.com",
    headers: {
        Authorization: `Bearer ${sessionStorage.getItem('access_token')}`,
        'content-type': 'application/json'
    }
})
