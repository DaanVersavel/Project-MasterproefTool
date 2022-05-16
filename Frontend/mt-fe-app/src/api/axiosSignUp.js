import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        authorization: `Bearer ${sessionStorage.getItem('access_signup')}`,
        'content-type': 'application/json'
    }
})