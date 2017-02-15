var React = require('react');
var CandidateDisplayComponent = require('../../shared/CandidateDisplayComponent');
var Validations = require('../../../utils/Validations');

var MM_PartyDisplayComponent = React.createClass({
    getInitialState: function() {
        return ({ showCandidates: false,
                  partyDictionary: this.formInitialDictionary(this.props.party.candidates),
                  jsErrors: [],
                  isMerged: false,
                  formChanged: true });
    },
    toggleShowCandidates: function() {
        if (this.state.jsErrors.length == 0) {
            this.setState({ showCandidates: !this.state.showCandidates });
        }
    },
    prepareCandidates: function() {
        var preparedCandidates = [];
        var candidates = this.props.party.candidates;
        var dictionary = this.state.partyDictionary;

        candidates.forEach((c, idx) => {
            preparedCandidates.push(
                <CandidateDisplayComponent
                    key={idx}
                    candidate={c}
                    changeVotes={this.handleChangeVotes}
                    votes={dictionary.get(c.id)}
                    formChanges={this.formChanges}
                />
            );
        });

        return preparedCandidates;
    },
    formInitialDictionary: function(candidates) {
        var mapped = new Map();
        candidates.forEach(c => {
            mapped.set(c.id, "");
        });
        return mapped;
    },
    handleChangeVotes: function(candidate_id, votes) {
        var actualDict = this.state.partyDictionary;
        actualDict.set(candidate_id, votes);
        this.setState({ dictionary: actualDict, formChanged: true });
    },
    checkAndSaveResults: function() {
        //e.preventDefault();
        var errors = Validations.checkErrorsPartyMMform(this.state.partyDictionary);

        if (errors.length > 0) {
            var style={ marginTop: 10 };
            this.setState({ jsErrors: Validations.prepareErrors(errors, style) });
        } else {
            this.setState({ jsErrors: [], showCandidates: false, isMerged: true, formChanged: false });
            this.props.mergeResults(this.state.partyDictionary);
        }
    },
    clearForm: function() {
        this.setState({ partyDictionary: this.formInitialDictionary(this.props.party.candidates),
                        jsErrors: [] });
    },
    render: function() {
        var saved = []; var clearBtn = []; var display = {};
        if (this.state.isMerged) {
            saved = <span className="glyphicon glyphicon-saved"></span>;
        }
        if (this.state.formChanged || !this.state.isMerged) {
            clearBtn = <button
                          className="btn btn-primary btn-sm"
                          onClick={this.clearForm}>
                          CLEAR-FORM
                       </button>
        }
        if (!this.state.showCandidates) display = {display: 'none'};

        return (
            <div className="unit">
                <div className="list-group-item active">
                    <div onClick={this.toggleShowCandidates} style={{ cursor: 'pointer' }}>
                        {this.props.party.name} &nbsp; {saved}
                    </div>
                </div>
                <div style={ display }>
                    <div className="list-group-item">
                        <button className="btn btn-primary btn-sm" disabled={!this.state.formChanged} onClick={this.checkAndSaveResults}>
                            CHECK-SAVE
                        </button>
                        {clearBtn}
                        {this.state.jsErrors}
                    </div>
                    {this.prepareCandidates()}
                </div>

            </div>
        );
    }
});

module.exports = MM_PartyDisplayComponent;
