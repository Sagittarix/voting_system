var React = require('react');
var styles = {
    "image": {width: 20, height: 20}
}
function CandidateCardComponent(props) {
  return (
        <div className="well well-sm candidate-card">
                <div className="row candidate-card-low">

                    <div className="col-sm-6 col-md-8">
                        <h4>{props.candidate.firstName}&nbsp;{props.candidate.lastName}</h4>
                        <small><cite><img src="app/imgs/political_party.png" style={ styles.image }/>&nbsp; {props.candidate.partyName}</cite></small>
                        <p>
                            <img src="app/imgs/fingerprint.png" style={ styles.image }/>&nbsp; {props.candidate.personId}
                            <br />
                            <img src="app/imgs/books.png" style={ styles.image }/>&nbsp; Kandidato aprasymas
                        </p>
                        <button type="button" className="btn btn-default btn-sm" style={{ marginBottom: 10 }}>Detaliau</button>
                    </div>
                </div>
            </div>
  );
}

module.exports = CandidateCardComponent;
