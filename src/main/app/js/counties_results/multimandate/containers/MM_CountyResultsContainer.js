var React = require('react');
var axios = require('axios');
var MM_CountyResultsComponent = require('../components/MM_CountyResultsComponent');
var MM_CountyResultsDisplayComponent = require('../components/MM_CountyResultsDisplayComponent');
var CandidateDisplayComponent = require('../../shared/CandidateDisplayComponent');
var MM_PartyDisplayComponent = require('../components/MM_PartyDisplayComponent');
var MM_PartyDisplayWithResultsComponent = require('../components/MM_PartyDisplayWithResultsComponent');
var Validations = require('../../../utils/Validations');
var Helpers = require('../../../utils/Helpers');

var MM_CountyResultsContainer = React.createClass({
    getInitialState: function() {
        return ({ parties: [],
                  activeCountyId: undefined,
                  representative: {},
                  dictionary: new Map(),
                  spoiled: undefined,
                  springErrors: [],
                  mergedResults: 0,
                  MMresults: {}
                });
    },
    componentDidMount: function() {

        // refactor when login will be implemented

        var _this = this;
        var getUrl = "http://localhost:8080/api/county-rep/" + this.props.params.id + "";
        axios.get(getUrl)
            .then(function(resp) {
                var results = _this.getMMresults(resp.data.county);
                _this.setState({ representative: resp.data,
                                 activeCountyId: resp.data.county.id,
                                 MMresults: results });
            })
            .catch(function(err) {
                console.log(err);
            });
        this.getParties();
    },
    getMMresults: function(county) {
        var results = {};
        county.countyResults.forEach(mm => {
            if (!mm.singleMandateSystem) results = mm;
        });
        return results;
    },
    getParties: function() {
        var _this = this;
        var getUrl = "http://localhost:8080/api/party/";
        axios.get(getUrl)
            .then(function(resp) {
              var initialDictionary = _this.formInitialDictionary(resp.data);
                _this.setState({ parties: resp.data,
                                 dictionary: initialDictionary
                               });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    prepareParties: function() {
        var preparedParties = [];
        var parties = this.state.parties;

        parties.forEach((p, idx) => {
            preparedParties.push(
                <MM_PartyDisplayComponent
                    key={idx}
                    party={p}
                    mergeResults={this.mergeResults}
                />
            );
        });

        return preparedParties;
    },
    preparePartiesWithResults: function() {
        var preparedParties = [];
        var parties = this.state.parties;

        parties.forEach((p, idx) => {
            preparedParties.push(
                <MM_PartyDisplayWithResultsComponent
                    key={idx}
                    party={p}
                    results={this.state.MMresults}
                />
            );
        });

        return preparedParties;
    },
    formInitialDictionary: function(parties) {
        var mapped = new Map();
        parties.forEach(p => {
            p.candidates.forEach(c => {
                mapped.set(c.id, undefined);
            });
        });
        return mapped;
    },
    prepareRepresentative() {
        return (
            <div>
                <div className="list-group-item active">
                    Prisijungęs kaip
                </div>
                <div className="list-group-item">
                    <span>{this.state.representative.firstName}</span> &nbsp;
                    <span>{this.state.representative.lastName}</span>
                </div>
            </div>
        );
    },
    handleChangeSpoiled: function(e) {
        this.setState({ spoiled: e.target.value })
    },
    handleSubmitMMresults: function() {
        //e.preventDefaults();
        var _this = this;
        var map = this.state.dictionary;
        var candidatesVotes = [];
        for (var pair of map) {
            candidatesVotes.push({ "candidateId": pair[0], "votes": pair[1] });
        }
        var body = {
            "spoiledBallots": this.state.spoiled,
            "countyId": this.state.activeCountyId,
            "singleMandateSystem": false,
            "candidatesVotes": candidatesVotes
        }
        axios.post('http://localhost:8080/api/county-results/', body)
            .then(function(resp) {
                _this.setState({ springErrors: [],
                                 dictionary: new Map(),
                                 spoiled: undefined,
                                 MMresults: resp.data });
            })
            .catch(function(err) {
                console.log(err);
                _this.setState({ springErrors: err.response.data.errorsMessages });
            });
    },
    mergeResults: function(partyDictionary, e) {
        //e.preventDefault();
        var actualDict = this.state.dictionary;
        partyDictionary.forEach(function(value, key) {
            actualDict.set(key, value);
        });
        var merged = this.state.mergedResults;
        merged += 1;
        this.setState({ dictionary: actualDict, mergedResults: merged, springErrors: [] });
    },
    prepareSpringErrors: function() {
        var style={"marginTop": 10}
        return Validations.prepareErrors(this.state.springErrors, style);
    },
    render: function() {
        var formOrResults;
        if (Object.keys(this.state.MMresults).length > 0) {
            formOrResults = <MM_CountyResultsDisplayComponent
                                representative={this.prepareRepresentative()}
                                spoiled={this.state.MMresults.spoiledBallots}
                                parties={this.preparePartiesWithResults()}
                                dateTime={Helpers.createdOn(this.state.MMresults.createdOn)}
                            />
        } else {
            formOrResults = <MM_CountyResultsComponent
                                representative={this.prepareRepresentative()}
                                parties={this.prepareParties()}
                                spoiled={this.state.spoiled}
                                dictionary={this.state.dictionary}
                                changeSpoiled={this.handleChangeSpoiled}
                                submitMMresults={this.handleSubmitMMresults}
                                springErrors={this.prepareSpringErrors()}
                                activeCountyId={this.state.activeCountyId}
                                mergedResults={this.state.mergedResults}
                                partiesCount={this.state.parties.length}
                            />
        }
        return formOrResults;
    }
});

module.exports = MM_CountyResultsContainer;
