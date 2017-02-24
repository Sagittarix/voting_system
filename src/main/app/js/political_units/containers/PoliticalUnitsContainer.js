var React = require('react');
var axios = require('axios');
var PoliticalUnitsComponent = require('../components/PoliticalUnitsComponent');
var PartyDisplayContainer = require('./PartyDisplayContainer');
var CandidateCardComponent = require('../../components/CandidateCardComponent');

var PoliticalUnitsContainer = React.createClass({
    getInitialState: function() {
        return ({ parties: [],
                  partyName: "",
                  springErrors: [] });
    },
    componentDidMount: function() {
        this.partiesAxiosGet();
    },
    partiesAxiosGet: function() {
        var _this = this;
        axios.get('http://localhost:8080/api/party')
            .then(function(resp) {
                _this.setState({ parties: resp.data });
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    prepareParties() {
        var parties = [];
        this.state.parties.forEach((p, idx) => {
            parties.push(
                <PartyDisplayContainer
                    key={idx}
                    index={idx}
                    party={p}
                    delete={this.deleteParty}
                    deleteCandidates={this.deleteCandidates}
                    partiesAxiosGet={this.partiesAxiosGet}
                />
            );
        });
        return parties;
    },
    deleteCandidates: function(party_id) {
        var _this = this;
        var deleteUrl = "http://localhost:8080/api/party/" + party_id + "/candidates"
        axios.delete(deleteUrl)
            .then(function(resp) {
                _this.partiesAxiosGet();
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    handleNameChange: function(e) {
        this.setState({ partyName: e.target.value });
    },
    handlePartySubmit: function(fd) {
        var party = { name: this.state.partyName }
        fd.append("party", JSON.stringify(party));
        var _this = this;
        var parties = this.state.parties;

        axios.post('http://localhost:8080/api/party', fd, { headers: { 'Content-Type': 'multipart/form-data' } })
            .then(function(resp) {
                parties.push(resp.data);
                _this.setState({ parties: parties, partyName: "" });
            })
            .catch(function(err) {
                console.log(err);
                console.log(err.response);
                _this.setState({ springErrors: err.response.data.errorsMessages });
            });
    },
    deleteParty(idx, party_id) {
        var _this = this;
        var deleteURL = "http://localhost:8080/api/party/" + party_id + "";
        axios.delete(deleteURL)
            .then(function(resp) {
                var parties = _this.state.parties;
                parties.splice(idx, 1);
                _this.setState({ parties: parties });
            })
            .catch(function(err) {
               console.log(err);
            });

    },
    deleteCandidates: function(party_id) {
        var _this = this;
        var deleteURL = "http://localhost:8080/api/party/" + party_id + "/candidates";
        axios.delete(deleteURL)
            .then(function(resp) {
                _this.partiesAxiosGet();
            })
            .catch(function(err) {
               console.log(err);
            });
    },
    render: function() {
        return <PoliticalUnitsComponent
                  parties={this.prepareParties()}
                  changeName={this.handleNameChange}
                  name={this.state.partyName}
                  create={this.handlePartySubmit}
                  springErrors={this.state.springErrors}
               />
    }
});

module.exports = PoliticalUnitsContainer;
