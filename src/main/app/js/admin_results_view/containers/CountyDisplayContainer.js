var React = require('react');
var axios = require('axios');
var CountyDisplayComponent = require('../components/CountyDisplayComponent');
var AdminViewCandidateComponent = require('../components/tiny_components/AdminViewCandidateComponent');
// var MM_PartyDisplayWithResultsComponent = require('../../counties_results/components/MM_PartyDisplayWithResultsComponent');

var CountyDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({
            showResults: false,
            smDisplay: undefined,
            parties: this.props.parties,
            county: this.props.county,
            smResultsConfirmed: false,
            mmResultsConfirmed: false
        });
    },
    componentWillReceiveProps: function(newProps) {
        if (newProps.county != this.state.county || newProps.parties != this.state.parties) {
            this.setState({ county: newProps.county, parties: newProps.parties });
        }
    },
    componentDidMount: function() {
        var county = this.state.county;
        // var smResult = this.getResults(county, true);
        // var mmResult = this.getResults(county, false);

        this.setState({
            smResultsConfirmed: (smResult != undefined) ? smResult.confirmed : false,
            mmResultsConfirmed: (mmResult != undefined) ? mmResult.confirmed : false
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
      var preparedResults = [];

      if (results == undefined) return results;

      preparedResults.push(
          <div className="list-group-item" key={results.unitVotesList.length}>
              <p>Sugadinti biuleteniai: {results.spoiledBallots}</p>
          </div>
      );
      results.unitVotesList.forEach((cv, idx) => {
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

        if (results == undefined) return results;

        preparedResults.push(
            <div className="list-group-item" key={results.unitVotesList.length}>
                <p>Sugadinti biuleteniai: {results.spoiledBallots}</p>
            </div>
        );
        results.unitVotesList.forEach((uv, idx) => {
            preparedResults.push(
                <MM_PartyDisplayWithResultsComponent
                    key={idx}
                    party={uv.party}
                    pVotes={uv}
                />
            );
        });
        return preparedResults;
    },
    determineResults: function() {
        if (this.state.smDisplay == undefined) return undefined;
        return (this.state.smDisplay) ? this.prepareSMresults() : this.prepareMMresults();
    },
    determineConfirmButton: function() {
        var confirmBtn = (
            <button className="btn btn-default btn-sm floaters-right">
                Patvirtinta &nbsp;
                <span className="glyphicon glyphicon-ok-sign"></span>
            </button>
        );
        var smDisplay = this.state.smDisplay;

        if (smDisplay === undefined) return confirmBtn;

        if (smDisplay && !this.state.smResultsConfirmed) {
            confirmBtn = (
                <button className="btn btn-default btn-sm floaters-right" onClick={this.confirmResults.bind(this, true)}>
                    Patvirtinti rezultatus &nbsp;
                    <span className="glyphicon glyphicon-exclamation-sign"></span>
                </button>
            );
        } else if (!smDisplay && !this.state.mmResultsConfirmed) {
            confirmBtn = (
                <button className="btn btn-default btn-sm floaters-right" onClick={this.confirmResults.bind(this, false)}>
                    Patvirtinti rezultatus &nbsp;
                    <span className="glyphicon glyphicon-exclamation-sign"></span>
                </button>
            );
        }

        return confirmBtn;
    },
    confirmResults: function(singleMandate) {
        var _this = this;
        var params = new URLSearchParams();
        params.append('countyId', this.state.county.id);
        params.append('isSingleMandate', singleMandate);

        axios.post('http://localhost:8080/api/county-results/confirm', params)
            .then(function(resp) {
                var stateVar = (singleMandate) ? 'smResultsConfirmed' : 'mmResultsConfirmed';
                _this.setState({ county: resp.data, [stateVar]: true });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    handleResultsDelete: function(singleMandate) {
        var _this = this;

        axios({
            method: 'delete',
            url: 'http://localhost:8080/api/county-results/county/',
            params: {
              countyId: this.state.county.id,
              isSingleMandate: singleMandate
            }
        })
        .then(function(resp) {
            var updatedState = (singleMandate) ? 'smResultsConfirmed' : 'mmResultsConfirmed';
            _this.setState({ smDisplay: undefined, [updatedState]: false, county: resp.data });
        })
        .catch(function(err) {
            console.log(err);
        })
    },
    determineAllConfirmedButton: function() {
        var btn = (
            <span className="btn-sm confirmed-floaters">
                Patvirtinta &nbsp; <span className="glyphicon glyphicon-flag"></span>
            </span>
        );
        return (this.state.smResultsConfirmed && this.state.mmResultsConfirmed) ? btn : undefined;
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
                allConfirmedBtn={this.determineAllConfirmedButton()}
            />
        );
    }
});

module.exports = CountyDisplayContainer;
