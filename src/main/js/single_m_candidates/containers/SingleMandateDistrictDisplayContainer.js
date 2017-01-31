var React = require('react');
var axios = require('axios');
var SingleMandateDistrictDisplayComponent = require('../components/SingleMandateDistrictDisplayComponent');
var CandidateCardComponent = require('../../components/CandidateCardComponent');

var SingleMandateDistrictDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({ showCandidates: false });
    },
    prepareCandidates: function() {
        var cand = [];
        this.props.district.candidates.forEach((c, index) => {
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
        this.props.deleteCandidates(this.props.district.id);
    },
    toggleShowCandidates: function() {
        this.setState({ showCandidates: !this.state.showCandidates });
    },
    render: function() {
        return (
            <SingleMandateDistrictDisplayComponent
                index={this.props.index}
                show={this.state.showCandidates}
                toggleShow={this.toggleShowCandidates}
                deleteCandidates={this.deleteCandidates}
                district={this.props.district}
                upload={this.props.upload}
                candidates={this.prepareCandidates()}
            />
        );
    }
});

module.exports = SingleMandateDistrictDisplayContainer;
