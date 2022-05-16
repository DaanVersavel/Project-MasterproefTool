import {Navigate, useLocation} from 'react-router-dom'

export const RequireAuth = ({children}) => {
    const location = useLocation()

    if (sessionStorage.getItem('access_token')===null) {
        return <Navigate to='/Login' state={{path: location.pathname}}/>
    }
    return children
}