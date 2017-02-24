var React = require('react');
var axios = require('axios');
var PartyDisplayComponent = require('../components/PartyDisplayComponent');
var CandidateCardComponent = require('../../components/CandidateCardComponent');

var PartyDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({ showCandidates: false,
                  springErrors: [],
                  party: this.props.party });
    },
    componentWillReceiveProps: function(newProps) {
        if (newProps.party != this.state.party) {
            this.setState({ party: newProps.party })
        }
    },
    prepareCandidates: function() {
        var cand = [];
        this.state.party.candidates.forEach((c, index) => {
            cand.push(
                  <CandidateCardComponent
                      key={index}
                      candidate={c}
                  />
            )
        });
        return cand;
    },
    deleteCandidates: function() {
        this.props.deleteCandidates(this.state.party.id);
    },
    deleteParty: function(index, party_id) {
        this.toggleShowCandidates();
        this.props.delete(index, party_id);
    },
    toggleShowCandidates: function() {
        this.setState({ showCandidates: !this.state.showCandidates });
    },
    uploadCandidates: function(fd, partyID) {
        var _this = this;
        var uploadUrl = "http://localhost:8080/api/party/" + partyID + "/candidates";
        axios.post(uploadUrl, fd, { headers: { 'Content-Type': 'multipart/form-data' } })
            .then(function(resp) {
                _this.setState({ springErrors: [], party: resp.data });
            })
            .catch(function(err) {
                console.log(err);
                _this.setState({ springErrors: err.response.data.errorsMessages });
            });
    },
    render: function() {
        return (
            <PartyDisplayComponent
                index={this.props.index}
                show={this.state.showCandidates}
                toggleShow={this.toggleShowCandidates}
                delete={this.deleteParty}
                deleteCandidates={this.deleteCandidates}
                party={this.state.party}
                upload={this.uploadCandidates}
                candidates={this.prepareCandidates()}
                springErrors={this.state.springErrors}
            />
        );
    }
});

module.exports = PartyDisplayContainer;
