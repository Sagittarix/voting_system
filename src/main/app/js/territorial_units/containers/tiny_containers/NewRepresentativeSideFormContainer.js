/**
 * Created by osvaldas on 17.2.7.
 */
var React = require('react');
var NewRepresentativeSideFormComponent = require('../../components/tiny_components/NewRepresentativeSideFormComponent');

var OnlyDistricts = [];
var onlyFirstRequiredCounties = [];

var NewRepresentativeSideFormContainer = React.createClass ({

    getAllDistricts: function () {
        OnlyDistricts = [];
        var DistrictsInformation = this.props.districtsData;
        DistrictsInformation.map(function(district, index) {
            OnlyDistricts.push(
                district
            )
        });
    },

    getFirstPossibleCounties: function () {
        var firstDistrict;
        OnlyDistricts.map(function (district, index) {
            if(index == 0){
                firstDistrict = district.name;
            }
        });
        OnlyDistricts.map(function(district, index){

            if(district.name == firstDistrict){
                console.log("match");
                onlyFirstRequiredCounties = [];
                district.counties.map(function (county, index) {
                    onlyFirstRequiredCounties.push(county);
                });
            } else {
                console.log("no match");
            }
        });

    },

    render: function () {
        {this.getAllDistricts()}
        {this.getFirstPossibleCounties()}

        return (
            <div>
                <NewRepresentativeSideFormComponent
                    newRep={this.props.newRep}
                    OnlyDistricts={OnlyDistricts}
                    onlyFirstRequiredCounties={onlyFirstRequiredCounties}
                />
            </div>
        )
    }
});

module.exports = NewRepresentativeSideFormContainer;