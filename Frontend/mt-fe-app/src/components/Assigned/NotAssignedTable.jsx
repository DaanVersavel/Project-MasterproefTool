import React from 'react';

const NotAssignedTable = (notAssSubjects) => {
    return (
        <div>
            <h1>JOOOO</h1>
            <table className="table">
                <thead>
                <tr>
                    <th>Title</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>{notAssSubjects.title}</td>
                </tr>
                </tfoot>
            </table>
        </div>
    )
}

export default NotAssignedTable;