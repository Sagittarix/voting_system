var React = require('react');

function CandidateCardComponent(props) {
  return (
    <div className="list-group-item">
        <h4 className="card-title">{props.candidate.firstName}&nbsp;{props.candidate.lastName}</h4>
        <h6 className="card-subtitle mb-2 text-muted">{props.candidate.personId}</h6>
        <p className="card-text">CANDIDATO APRASYMAS</p>
    </div>
  );
}

module.exports = CandidateCardComponent;
