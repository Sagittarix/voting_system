var React = require('react');
var partyName;

var CandidateWithResultsDisplayComponent = React.createClass({
    render: function() {
        partyName = (this.props.candidate.partyName == undefined) ? "Išsikėlęs pats" : this.props.candidate.partyName;
        return (
            <div className="unit narrowed">
                  <div className="list-group-item">
                    <div>
                      <div style={{height: 20}}>
                        <div className="col-md-3">{this.props.candidate.firstName} &nbsp; {this.props.candidate.lastName}</div>
                        <div className="col-md-4">{partyName}</div>
                        <div className="col-md-3">Balsų skaičius</div>
                        <div className="col-md-2">{this.props.cVotes.votes}</div>
                      </div>
                    </div>
                  </div>
            </div>
        );
    }
});

module.exports = CandidateWithResultsDisplayComponent;
