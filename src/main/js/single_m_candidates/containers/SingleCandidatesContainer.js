var React = require('react');
var axios = require('axios');
var SingleMandateDistrictDisplayContainer = require('./SingleMandateDistrictDisplayContainer');
var SingleCandidatesComponent = require('../components/SingleCandidatesComponent');

var SingleCandidatesContainer = React.createClass({
    getInitialState: function() {
        return ({ districts: [] });
    },
    componentDidMount: function() {
        this.districtsAxiosGet();
    },
    districtsAxiosGet: function() {
        var _this = this;
        axios.get('http://localhost:8080/api/district/')
            .then(function(resp) {
                _this.setState({ districts: resp.data });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    prepareDistricts() {
        var districts = [];
        this.state.districts.forEach((d, index) => {
            districts.push(<SingleMandateDistrictDisplayContainer
                                key={index}
                                index={index}
                                district={d}
                                upload={this.handleCandidatesUpload}
                                deleteCandidates={this.deleteCandidates}
                           />)
        });
        return districts;
    },
    deleteCandidates: function(district_id) {
        var _this = this;
        var deleteUrl = "http://localhost:8080/api/district/" + district_id + "/candidates"
        axios.delete(deleteUrl)
            .then(function(resp) {
                _this.districtsAxiosGet();
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    handleCandidatesUpload: function(fd, districtId) {
        var _this = this;
        var uploadPath = "http://localhost:8080/api/district/" + districtId + "/candidates";
        axios.post(uploadPath, fd, { headers: { 'Content-Type': 'multipart/form-data' } })
            .then(function(resp) {
                _this.districtsAxiosGet();
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    render: function() {
        return <SingleCandidatesComponent
                  districts={this.prepareDistricts()}
               />
    }
});

module.exports = SingleCandidatesContainer;
