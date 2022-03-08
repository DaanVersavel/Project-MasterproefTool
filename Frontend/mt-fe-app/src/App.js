import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';

import BNavbar from './components/navbar/BNavbar.jsx'
import Form from "./components/form/Form";


class App extends Component {
    render() {
        return (
            <div className={"App"}>
                <BNavbar />
                <Form />
            </div>
        );
    }
}

export default App;
