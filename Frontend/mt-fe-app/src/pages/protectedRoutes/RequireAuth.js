import {Navigate, useLocation} from 'react-router-dom'
import axios from "../../api/axiosAccessToken";
import axiosRefresh from "../../api/axiosRefreshToken"

export const RequireAuth = ({children}) => {
    const location = useLocation()

    if (sessionStorage.getItem('access_token')===null) {
        return <Navigate to='/Login' state={{path: location.pathname}}/>
    }

    // implementeer de fases
    /*var faseEenEind = new Date();
    var faseTweeEind = new Date();
    var faseDrieEind = new Date();

    var time = new Date().getTime();*/


    //controleer of access_token al die niet vervallen is
    /*if (
        axios
            .get('')
            .catch((error) => {
                axiosRefresh
                    .get('/User/refresh/token')
                    .then((response) => {
                        console.log(response)
                    })
                    .catch((error) => {
                        alert("Uw sessie is vervallen, gelieve uw opnieuw aan te melden.")
                        sessionStorage.clear()
                        return <Navigate to='/Login' state={{path: location.pathname}}/>
                    })
            })
    )*/

    return children
}