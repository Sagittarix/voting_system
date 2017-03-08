/**
 * Created by osvaldas on 17.2.7.
 */
var React = require('react');
var NewRepresentativeSideFormContainer = require('../containers/NewRepresentativeSideFormContainer');
var CountyRepresentativeListLineComponent = require('./CountyRepresentativeListLineComponent');
var Helper = require('../../utils/Helper');
var Fields = require('../../utils/Fields');

var CountyRepresentativesDisplayComponent = React.createClass ({
    getInitialState() {
        return ({
            ASCname: true,
            ASCcounty: true,
            field: "",
            needToSort: false
        });
    },
    toggleNameSortOrder() {
        this.setState({
            ASCname: !this.state.ASCname,
            ASCcounty: this.state.ASCname,
            field: Fields.name,
            needToSort: true
        });
    },
    toggleCountySortOrder() {
        this.setState({
            ASCname: this.state.ASCcounty,
            ASCcounty: !this.state.ASCcounty,
            field: Fields.county,
            needToSort: true
        });
    },
    sortRepresentatives(collection) {
        return (this.state.needToSort) ? Helper.sortRepresentatives(collection, this.state) : collection;
    },
    render: function () {
        var CountyRepresentativesArray = [];
        var ArrayOfUniqueCombinationsOfDistrictAndCountyNames = [];
        var CountyRepresentativesEmailsArray = [];
        var _this = this;

        this.props.repData.forEach(function (rep, index) {
            CountyRepresentativesArray.push(
                <CountyRepresentativeListLineComponent
                    unit={rep}
                    index={index}
                    id={rep.id}
                    key={index}
                    onDeleteRepresentative={_this.props.onDeleteRepresentative}
                />
            );
            CountyRepresentativesEmailsArray.push(rep.email);
            var RepresentativeIsFromDistrict = rep.district.name;
            var RepresentativeIsFromCounty = rep.county.name;
            var UniqueCombinaitonOfDistrictAndCounty = RepresentativeIsFromDistrict.concat(RepresentativeIsFromCounty);
            ArrayOfUniqueCombinationsOfDistrictAndCountyNames.push(UniqueCombinaitonOfDistrictAndCounty);
        });
        var rotationName = (this.state.ASCname) ? " Z-A" : "A-Z";
        var rotationCounty = (this.state.ASCcounty) ? " Z-A" : "A-Z";
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item active">
                            <div style={{textAlign:"center"}}><b>RINKIMŲ APYLINKIŲ ATSTOVAI</b></div>
                        </div>
                        <div className="list-group-item active">
                            <div>
                                <div style={{height: "20px"}}>
                                    <div className="col-md-4">
                                        Atstovas &nbsp;
                                        <small onClick={this.toggleNameSortOrder}>[Rūšiuoti {rotationName}]</small>
                                    </div>
                                    <div className="col-md-4">
                                        Apylinkė &nbsp;
                                        <small onClick={this.toggleCountySortOrder}>[Rūšiuoti {rotationCounty}]</small>
                                    </div>
                                    <div className="col-md-3">El. paštas</div>
                                    <div className="col-md-1">Trinti</div>
                                </div>
                            </div>
                        </div>
                        {this.sortRepresentatives(CountyRepresentativesArray)}
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            <NewRepresentativeSideFormContainer
                                newRep={this.props.newRep}
                                districtsData={this.props.districtsData}
                                CountyRepresentativesEmailsArray={CountyRepresentativesEmailsArray}
                                uniqueDistrictAndCountyNameCombinationArray={ArrayOfUniqueCombinationsOfDistrictAndCountyNames}
                                springErrors={this.props.springErrors}
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

module.exports = CountyRepresentativesDisplayComponent;
