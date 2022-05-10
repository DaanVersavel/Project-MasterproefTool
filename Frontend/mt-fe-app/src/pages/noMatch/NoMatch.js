import React from 'react';

export default function NoMatch() {
    setTimeout(function(){
        window.location.href = '/Home';
    }, 3000);

    return (
        <>
            <h1>Page has no match</h1>
            <h2>Redirecting...</h2>
        </>
    );
}

