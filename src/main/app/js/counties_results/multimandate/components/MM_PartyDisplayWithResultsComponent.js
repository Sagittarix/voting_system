var React = require('react');
//var CandidateDisplayComponent = require('../../shared/CandidateDisplayComponent');
var CandidateWithResultsDisplayComponent = require('./CandidateWithResultsDisplayComponent');
var Validations = require('../../../utils/Validations');

var MM_PartyDisplayWithResultsComponent = React.createClass({
    prepareCandidates: function() {
        var preparedCandidates = [];
        var candidates = this.props.party.candidates;
        var candidateVotesList = this.props.results.candidateVotesList;
        var cVotes = {};

        candidates.forEach((c, idx) => {
            candidateVotesList.forEach(cv => {
                if (cv.candidate.id === c.id) cVotes = cv;
            });
            preparedCandidates.push(
                <CandidateWithResultsDisplayComponent
                    key={idx}
                    candidate={c}
                    cVotes={cVotes}
                />
            );
        });

        return preparedCandidates;
    },
    render: function() {
        return (
            <div className="unit">
                <div className="list-group-item active">
                    <div onClick={this.toggleShowCandidates} style={{ cursor: 'pointer' }}>
                        {this.props.party.name}
                    </div>
                </div>
                <div>
                    {this.prepareCandidates()}
                </div>

            </div>
        );
    }
});

module.exports = MM_PartyDisplayWithResultsComponent;
