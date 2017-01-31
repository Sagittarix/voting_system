var React = require('react');
var ReactRouter = require('react-router');
var axios = require('axios');
var DistrictDisplayContainer = require('./DistrictDisplayContainer');
var TerritorialBreakdownComponent = require('../components/TerritorialBreakdownComponent');
var AddedCountyDisplayComponent = require('../components/tiny_components/AddedCountyDisplayComponent');

var TerritorialBreakdownContainer = React.createClass({
    getInitialState: function() {
        return ({ districts: [], districtName: "", counties: [] });
    },
    componentDidMount: function() {
        var _this = this;
        axios.get('http://localhost:8080/api/district/')
            .then(function(resp) {
                _this.setState({ districts: resp.data });
                console.log(resp);
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    prepareDistricts() {
        var districts = [];
        this.state.districts.forEach((d, index) => {
            districts.push(<DistrictDisplayContainer
                                key={index}
                                index={index}
                                district={d}
                                delete={this.handleDistrictDestroy}
                           />)
        });
        return districts;
    },
    prepareCounties() {
        var counties = [];
        this.state.counties.forEach((c, index) => {
            counties.push(
                <AddedCountyDisplayComponent
                    key={index}
                    index={index}
                    name={c.name}
                    remove={this.handleAddedCountyRemove}
                />
            )
        });
        return counties;
    },
    handleNameChange: function(e) {
        this.setState({ districtName: e.target.value });
    },
    handleDistrictSubmit: function(e) {
        var _this = this;
        var body = {
            name: this.state.districtName,
            counties: this.state.counties
        }
        var districts = this.state.districts;
        axios.post('http://localhost:8080/api/district/', body)
            .then(function(resp) {
                console.log(resp);
                districts.push(resp.data);
                _this.setState({ districts: districts, districtName: "", counties: [] });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    handleDistrictDestroy(idx) {
        var districts = this.state.districts;
        districts.splice(idx, 1);
        this.setState({ districts:  districts});
    },
    handleAddCounty: function(body) {
        var counties = this.state.counties;
        counties.push(body);
        this.setState({ counties: counties });
    },
    handleAddedCountyRemove: function(idx) {
        var counties = this.state.counties;
        counties.splice(idx, 1);
        this.setState({ counties: counties });
    },
    render: function() {
        return <TerritorialBreakdownComponent
                  districts={this.prepareDistricts()}
                  delete={this.handleDistrictDestroy}
                  changeName={this.handleNameChange}
                  name={this.state.districtName}
                  create={this.handleDistrictSubmit}
                  addCounty={this.handleAddCounty}
                  counties={this.prepareCounties()}
               />
    }
});

module.exports = TerritorialBreakdownContainer;
