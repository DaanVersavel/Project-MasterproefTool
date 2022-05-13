import React from 'react';

export default function NoMatch() {
    setTimeout(function(){
        window.location.href = '/Home';
    }, 100);

    return (
        <h2>Redirecting...</h2>
    );
}

