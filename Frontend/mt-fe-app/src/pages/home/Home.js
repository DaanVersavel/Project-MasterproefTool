import React from "react";
import "./Home.css";
export default function Home(){
    return(
        <div>
            <div className="headerTitles">
                <span className="headerTitleSm">KUL</span>
                <span className="headerTitleLg">Master Thesis Tool</span>
            </div>
            <img
                className="headerImg"
                src={require('../../kaartCampussen.png')}
                alt="Overview of location of campusses"
            />

            <button >Login</button>
        </div>
    );
}