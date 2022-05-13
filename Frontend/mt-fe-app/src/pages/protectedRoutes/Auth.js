import {createContext, useContext, useEffect, useState} from 'react'
import axios from "../../api/axiosAccessToken";

const AuthContext = createContext(null)

export const AuthProvider = ({children}) => {
    const [name, setName] = useState(null);
    const [role, setRole] = useState(null);

    /*useEffect(() => {
        axios
            .get('/User/whoami')
            .then((response) => {
                console.log(response);
                setRole(response.data['role'].roleName)
            })
            .catch(error => {
                console.log(error)
            })
    }, []);

    useEffect(() => {
        axios
            .get('/User/whoami')
            .then((response) => {
                console.log(response);
                setName(response.data['firstName'].concat(' ', response.data['surname']))
            })
            .catch(error => {
                console.log(error)
            })
    }, []);*/

    return (
        <AuthContext.Provider value={{name, role}}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    return useContext(AuthContext)
}