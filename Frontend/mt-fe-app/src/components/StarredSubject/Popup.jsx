import React from 'react';
import "./Popup.css"

function Popup(props){
    //Als t$props getriggered wordt, toon je de popup, anders niet.
    return(props.trigger)?(
        <div className="popup">
            <div className="popup-inner">
                <button className="close-btn" onClick={()=>props.setTrigger(false)}>close</button>
                {props.children}
            </div>
        </div>
    ):"";
}
export default Popup;