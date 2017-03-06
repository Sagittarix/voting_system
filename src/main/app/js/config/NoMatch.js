var React = require('react');

function NoMatch() {
	return (
		<div className="error-404" style={{textAlign:"center"}}>
		    <h2 className="error-code m-b-10 m-t-20">404 <i className="fa fa-warning"></i></h2>
		    <div className="font-bold">Klaida 404! Puslapis nerastas.</div>

		    <div className="error-desc">
				Atsiprašome, bet puslapio, kurio ieškote neradome. <br/>
				Atnaujinkite puslapį arba spauskite žemiau esantį mygtuką.
		        <div><br/>
		            <a href="/" className="btn btn-primary"><span className="glyphicon glyphicon-home"></span> Grįžti į pagrindinį puslapį</a>
		        </div>
		    </div>
		</div>
	);
}

module.exports = NoMatch;
