import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        Authorization: `Bearer ${sessionStorage.getItem('refresh_token')}`,
        'content-type': 'application/json'
    }
})