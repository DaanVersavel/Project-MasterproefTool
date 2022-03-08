export default function validateInfo(values){
    let errors={}

    if(!values.title.trim()){
        errors.title = "Title required"
    }
    if(!values.description){
        errors.description = "Description required"
    }
    if(!values.remarks){
        errors.remarks = "Remarks required"
    }
    if(!values.company.trim()){
        errors.company = "Company name required"
    }
    if(!values.coordinator.trim()){
        errors.coordinator = "Coordinator required"
    }
    if(!values.promotor.trim()){
        errors.promotor = "Promotor required"
    }
    if(!values.disciplines){
        errors.disciplines = "disciplines required"
    }
    if(!values.aStudents){
        errors.aStudents = "Amount of students required"
    }
    return errors;
}