var React = require('react');
var axios = require('axios');
var PartyDisplayComponent = require('../components/PartyDisplayComponent');
var CandidateCardComponent = require('../../components/CandidateCardComponent');

var PartyDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({ showCandidates: false });
    },
    prepareCandidates: function() {
        var cand = [];
        this.props.party.candidates.forEach((c, index) => {
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
        this.props.deleteCandidates(this.props.party.id);
    },
    toggleShowCandidates: function() {
        this.setState({ showCandidates: !this.state.showCandidates });
    },
    render: function() {
        return (
            <PartyDisplayComponent
                index={this.props.index}
                show={this.state.showCandidates}
                toggleShow={this.toggleShowCandidates}
                delete={this.props.delete}
                deleteCandidates={this.deleteCandidates}
                party={this.props.party}
                upload={this.props.upload}
                candidates={this.prepareCandidates()}
            />
        );
    }
});

module.exports = PartyDisplayContainer;
