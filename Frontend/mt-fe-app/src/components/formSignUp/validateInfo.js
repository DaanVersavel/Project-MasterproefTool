export default function validateInfo(values, studentValues, coordinatorValues, promoterValues, companyValues) {
    let errors = {};

    if (!values.role) {
        errors.role = 'Role required';
    }

    if (!studentValues.studentNumber) {
        errors.studentNumber = 'Role required';
    }

    if ((!studentValues.campus && values.role==='student')
        || (!coordinatorValues.campus && values.role==='coordinator')
        || (!promoterValues.campus && values.role==='promoter')) {
        errors.campus = 'Campus required';
    }

    if ((!studentValues.campus && values.role==='student')
        || (!coordinatorValues.campus && values.role==='coordinator')
        || (!promoterValues.campus && values.role==='promoter')) {
        errors.disciplineS = 'Discipline required';
    }

    if (!companyValues.companyName) {
        errors.companyName = 'Company name required';
    }

    if (!companyValues.vat) {
        errors.vat = 'VAT number required';
    }

    if (!values.firstName) {
        errors.firstName = 'First name required';
    }

    if (!values.lastName) {
        errors.lastName = 'Last name required';
    }

    if (!values.email) {
        errors.email = 'Email required';
    }

    if (!values.phone) {
        errors.phone = 'Phone number required';
    }

    if (!values.password) {
        errors.password = 'Password is required';
    }

    if (!values.password2) {
        errors.password2 = 'Password is required';
    } else if (values.password2 !== values.password) {
        errors.password2 = 'Passwords do not match';
    }

    return errors;
}