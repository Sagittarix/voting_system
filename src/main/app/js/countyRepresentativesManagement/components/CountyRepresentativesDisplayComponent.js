/**
 * Created by osvaldas on 17.2.7.
 */
var React = require('react');
var NewRepresentativeSideFormContainer = require('../containers/NewRepresentativeSideFormContainer');
var CountyRepresentativeListLineComponent = require('./CountyRepresentativeListLineComponent');
var Helper = require('../../utils/Helper');
var Fields = require('../../utils/Fields');

var timer = null;

var CountyRepresentativesDisplayComponent = React.createClass ({
    getInitialState() {
        return ({
            ASCname: true,
            ASCcounty: true,
            field: "",
            needToSort: false,
            showFullListStyle: {visibility: "visible"},
            fullListShowing: true,
            query: '',
            showReps: false
        });
    },
    shouldComponentUpdate: function () {
        return true;
    },
    toggleRepsList: function () {
        this.setState({ showReps: !this.state.showReps });
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
        return (this.state.needToSort && collection.length > 0) ?
            Helper.sortRepresentatives(collection, this.state) : collection;
    },
    handleChangeQuery: function(value) {
        this.setState({ query: value.toLowerCase() });
    },
    onKeyUp() {
        var value = document.getElementById("representatives-search").value;
        var _this = this;

        $('#representatives-search').keyup(function() {
            clearTimeout(timer);
            timer = setTimeout(_this.handleChangeQuery.bind(_this, value), 1000);
        })
    },
    clearQuery: function() {
        document.getElementById('representatives-search').value = '';
        this.setState({ query: '' });
    },
    filterByQuery(rep) {
        const query = this.state.query;

        console.log(rep.county.name + "");
        console.log(rep.county.name.includes(query));

        return rep.username.includes(query) ||
            rep.email.includes(query) ||
            rep.county.name.toLowerCase().includes(query) ||
            rep.district.name.toLowerCase().includes(query);
    },
    render: function () {
        let CountyRepresentativesArray = [];
        let ArrayOfUniqueCombinationsOfDistrictAndCountyNames = [];
        let CountyRepresentativesEmailsArray = [];
        let queryMatchesAny = false;

        const filtered = (this.state.query.length > 0) ? this.props.repData.filter(this.filterByQuery) : [];
        let newCollection = this.props.repData;
        if (filtered.length > 0) {
            newCollection = filtered;
            queryMatchesAny = true;
        }

        newCollection.forEach(function (rep, index) {
            CountyRepresentativesArray.push(
                <CountyRepresentativeListLineComponent
                    unit={rep}
                    index={index}
                    id={rep.id}
                    key={index}
                    onDeleteRepresentative={this.props.onDeleteRepresentative}
                />
            );
            CountyRepresentativesEmailsArray.push(rep.email);
            var RepresentativeIsFromDistrict = rep.district.name;
            var RepresentativeIsFromCounty = rep.county.name;
            var UniqueCombinaitonOfDistrictAndCounty = RepresentativeIsFromDistrict.concat(RepresentativeIsFromCounty);
            ArrayOfUniqueCombinationsOfDistrictAndCountyNames.push(UniqueCombinaitonOfDistrictAndCounty);
        }.bind(this));

        var rotationName = (this.state.ASCname) ? " Z-A" : "A-Z";
        var rotationCounty = (this.state.ASCcounty) ? " Z-A" : "A-Z";
        var showOrHide = (this.state.showReps) ? CountyRepresentativesArray : [];
        showOrHide = (queryMatchesAny) ? CountyRepresentativesArray : showOrHide;

        let toggleShowWord = 'Rodyti';
        let eyeClass = 'open';
        if (this.state.showReps) {
            toggleShowWord = 'Slėpti';
            eyeClass = 'close';
        }

        return (
            <div>
                <div className="container">
                    <div className="row grayed">
                        <div className="col-md-8 units-list-area">
                            <div >
                                <div className="list-group-item active location3">
                                    <div style={{textAlign:"center"}}><b>RINKIMŲ APYLINKIŲ ATSTOVAI</b></div>
                                </div>
                                <div className="list-group-item active location3">
                                    <div>
                                        <div style={{height: "20px"}}>
                                            <div className="col-md-4">
                                                Atstovas &nbsp;
                                                <small style={{cursor: "pointer" }}
                                                       id="representatives-sorting-button"
                                                       onClick={this.toggleNameSortOrder}>
                                                    [Rūšiuoti {rotationName}]
                                                </small>
                                            </div>
                                            <div className="col-md-4">
                                                Apylinkė &nbsp;
                                                <small style={{cursor: "pointer" }}
                                                       id="county-sorting-button"
                                                       onClick={this.toggleCountySortOrder}>[Rūšiuoti {rotationCounty}]</small>
                                            </div>
                                            <div className="col-md-3">El. paštas</div>
                                            <div className="col-md-1">Trinti</div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div className="row list-group-item" id="reps-view-actions-bar">
                                        <div className="col-md-5 verticals">
                                            <form>
                                                <div className="input-group">
                                                    <input
                                                        className="form-control"
                                                        id="representatives-search"
                                                        placeholder="Ieškoti atstovo"
                                                        onChange={this.onKeyUp}
                                                    />
                                                    <span className="input-group-addon" style={{ padding: 0 }} onClick={this.clearQuery}>
                                               <button className="btn btn-secondary" type="button">
                                                   <span className="glyphicon glyphicon-remove-circle"></span>
                                               </button>
                                           </span>
                                                </div>
                                            </form>
                                        </div>
                                        <div className="col-md-3 verticals">
                                            <button id="show-hide-button" onClick={this.toggleRepsList}>
                                                <span className={"glyphicon glyphicon-eye-" + eyeClass}></span> &nbsp;
                                                {toggleShowWord + ' visus'}
                                            </button>
                                        </div>
                                    </div>
                                    <div>
                                        {this.sortRepresentatives(showOrHide)}
                                    </div>
                                </div>
                            </div>
                            </div>
                            <div className="col-md-4 units-create-area">
                                <div className="col-md-11">
                                    <NewRepresentativeSideFormContainer
                                        newRep={this.props.newRep}
                                        districtsData={this.props.districtsData}
                                        CountyRepresentativesEmailsArray={CountyRepresentativesEmailsArray}
                                        uniqueDistrictAndCountyNameCombinationArray={ArrayOfUniqueCombinationsOfDistrictAndCountyNames}
                                        springErrors={this.props.springErrors}
                                        popupAlert={this.props.popupAlert}
                                    />
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        )
    }
});

module.exports = CountyRepresentativesDisplayComponent;
