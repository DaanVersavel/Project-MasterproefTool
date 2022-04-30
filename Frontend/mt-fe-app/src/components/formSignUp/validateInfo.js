export default function validateInfo(values) {
    let errors = {};

    if (!values.firstName.trim()) {
        errors.firstName = 'First name required';
    }

    if (!values.lastName.trim()) {
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