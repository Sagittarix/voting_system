var React = require('react');
var axios = require('axios');
var CountyDisplayComponent = require('../components/CountyDisplayComponent');
var AdminViewCandidateComponent = require('../components/tiny_components/AdminViewCandidateComponent');
var MM_PartyDisplayWithResultsComponent = require('../../counties_results/multimandate/components/MM_PartyDisplayWithResultsComponent');

var CountyDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({ showResults: false,
                  smDisplay: undefined,
                  parties: [],
                  county: this.props.county,
                  resultsConfirmed: false });
    },
    componentWillReceiveProps: function(newProps) {
        if (newProps.county != this.state.county) this.setState({ county: newProps.county });
    },
    componentDidMount: function() {
        var _this = this;
        axios.get('http://localhost:8080/api/party')
            .then(function(resp) {
                _this.setState({ parties: resp.data });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    toggleShowResults: function() {
        this.setState({ showResults: !this.state.showResults });
    },
    displaySMresults: function() {
        this.setState({ smDisplay: true });
    },
    displayMMresults: function() {
        this.setState({ smDisplay: false });
    },
    getResults: function(county, singleMandate) {
        var results;
        county.countyResults.forEach(cr => {
            if (cr.singleMandateSystem == singleMandate) results = cr;
        });
        return results;
    },
    prepareSMresults: function() {
      var results = this.getResults(this.state.county, true);
      if (results == undefined) {
          return results;
      }
      var preparedResults = [];

      preparedResults.push(
          <div className="list-group-item" key={results.candidateVotesList.length}>
              <p>Sugadinti biuleteniai: {results.spoiledBallots}</p>
          </div>
      );
      results.candidateVotesList.forEach((cv, idx) => {
          var partyName = (cv.candidate.partyName) ? cv.candidate.partyName : "Išsikėlęs pats";
          preparedResults.push(
              <AdminViewCandidateComponent
                  key={idx}
                  firstName={cv.candidate.firstName}
                  lastName={cv.candidate.lastName}
                  partyName={partyName}
                  votes={cv.votes}
              />
          );
      });
      return preparedResults;
    },
    prepareMMresults: function() {
        var results = this.getResults(this.state.county, false);
        var preparedResults = [];
        var parties = this.state.parties;

        if (results == undefined) return results;

        parties.forEach((p, idx) => {
            preparedResults.push(
                <MM_PartyDisplayWithResultsComponent
                    key={idx}
                    party={p}
                    results={results}
                />
            );
        });

        return (
            <div>
                <div className="list-group-item" key={results.candidateVotesList.length}>
                    <p>Sugadinti biuleteniai: {results.spoiledBallots}</p>
                </div>
                <div className="list-group-item">
                    {preparedResults}
                </div>
            </div>
        );
    },
    determineResults: function() {
        var results = undefined;
        if (this.state.smDisplay == undefined) {
            return results;
        } else if (this.state.smDisplay) {
            results = this.prepareSMresults();
        } else {
            results = this.prepareMMresults();
        }
        return results;
    },
    determineConfirmButton: function() {
        var confirmBtn;
        if (this.state.resultsConfirmed) {
            confirmBtn = (
                <button className="btn btn-default btn-sm floaters-right">
                    Patvirtinta &nbsp;
                    <span className="glyphicon glyphicon-ok-sign"></span>
                </button>
            );
        } else {
            confirmBtn = (
                <button className="btn btn-default btn-sm floaters-right">
                    Patvirtinti rezultatus &nbsp;
                    <span className="glyphicon glyphicon-exclamation-sign"></span>
                </button>
            );
        }
        return confirmBtn;
    },
    handleResultsDelete: function() {
        var _this = this;
        var delUrl = "http://localhost:8080/api/county-results/county/" + this.state.county.id + "";
        axios.delete(delUrl)
            .then(function(resp) {
                _this.setState({ smDisplay: undefined, resultsConfirmed: false, county: resp.data });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    render: function() {
        return (
            <CountyDisplayComponent
                index={this.props.index}
                show={this.state.showResults}
                toggleShow={this.toggleShowResults}
                county={this.state.county}
                displaySM={this.displaySMresults}
                displayMM={this.displayMMresults}
                results={this.determineResults()}
                smDisplay={this.state.smDisplay}
                confirmBtn={this.determineConfirmButton()}
                delete={this.handleResultsDelete}
            />
        );
    }
});

module.exports = CountyDisplayContainer;
