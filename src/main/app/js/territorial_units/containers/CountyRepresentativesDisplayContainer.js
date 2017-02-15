/**
 * Created by osvaldas on 17.2.7.
 */
var React = require ('react');
var axios = require ('axios');
var CountyRepresentativesDisplayComponent = require('../components/CountyRepresentativesDisplayComponent');

var CountyRepresentativesDisplayContainer = React.createClass({

    getInitialState: function () {
        return {
            representatives: [],
            districts: [],
            newRepresentative: {
                firstName: "vardas",
                lastName: "pavarde",
                county: {
                    id: 0
                }
            }
        }
    },

    componentDidMount: function () {
        var self = this;
        //gets all representatives
        axios.get('http://localhost:8080//api/county-rep')
            .then(function(response){
              console.log("RESPONSE.DATA")
              console.log(response.data);
                self.setState({representatives: response.data});
            })
            .catch(function(error){
              console.log("ERROR")
                console.log(error);
            });
        // gets all districts
        axios.get('http://localhost:8080/api/district')
            .then(function (response){
                self.setState({districts: response.data});
            })
            .catch(function(error){
                console.log(error);
            });
    },

    handleDeleteRepresentative: function (repId) {
        var self = this;
        var address = 'http://localhost:8080/api/county-rep/';
        var addressEnding = repId.toString();
        var newAddress = address.concat(addressEnding);
        axios.delete(newAddress)
            .then(function(response){
                console.log("delete passed");
                console.log(response);
                // gets updated list and updates the state
                // ---------------------------------------
                axios.get('http://localhost:8080/api/county-rep')
                    .then(function(response){
                        self.setState({representatives: response.data});
                        console.log("new representatives list received and state updated")
                        console.log(response.data);
                    })
                    .catch(function(error){
                        console.log(error);
                    });
                // ---------------------------------------
            })
            .catch(function (error) {
                console.log("delete failed");
                console.log(error);
            });
    },

    newRep: function (name, surname, email, district, county) {
        //e.preventDefault();
        var self = this;
        var countyId = this.getCountyId(district, county);

        var RequestBody = {"firstName": name, "lastName": surname, "county": {"id": countyId}};

        axios.post('http://localhost:8080/api/county-rep', RequestBody)
            .then(function(response){
                console.log("POST RESPONSE");
                console.log(response.data);
                axios.get('http://localhost:8080//api/county-rep')
                    .then(function(response){
                        self.setState({representatives: response.data});
                        console.log(response.data);
                    })
                    .catch(function(error){
                        console.log(error);
                    });
            })
            .catch(function(error){
                console.log(error);
            });
        this.setState({newRepresentative: RequestBody});
    },

        // gets County ID of specific county that needs to be assigned a representative aacording to county name
    getCountyId: function (districtName, countyName) {
        var CountyId;
        this.state.districts.map(function(district, index){
            if(district.name == districtName){
                console.log("match");
                district.counties.map(function (county, index) {
                    if(county.name == countyName){
                        CountyId = county.id;
                        console.log(CountyId);
                    }
                });
            } else {
                console.log("county name did not match");
            }
        });
        return CountyId;
    },

    render: function () {
        return (
            <div>
                <CountyRepresentativesDisplayComponent
                    repData={this.state.representatives}
                    onDeleteRepresentative={this.handleDeleteRepresentative}
                    newRep={this.newRep}
                    districtsData={this.state.districts}
                />
            </div>
        )
    }
});

module.exports = CountyRepresentativesDisplayContainer;
