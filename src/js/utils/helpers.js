var axios = require('axios');
var BASE_URL = 'http://localhost:8080/';

function getDistrictById(districtId) {
    return axios.get(BASE_URL + 'api/district/' + districtId);
}

function getDistrictList() {
    return axios.get(BASE_URL + 'api/district');
}

function saveNewDistrict(newDistrict) {
    return axios.post(BASE_URL + 'api/district', newDistrict);
}

function deleteDistrict(districtId) {
    return axios.delete(BASE_URL + 'api/district/' + districtId);
}

function deleteSingleMandateCandidateList(districtId) {
    return axios.delete(BASE_URL + 'api/district/' + districtId + '/candidates');
}


module.exports = {getDistrictById, getDistrictList, saveNewDistrict, deleteDistrict, deleteSingleMandateCandidateList};