var React = require('react');

function NoMatch() {
	return (
		<div className="error-404">
		    <div className="error-code m-b-10 m-t-20">404 <i className="fa fa-warning"></i></div>
		    <h2 className="font-bold">Oops 404! That page can’t be found.</h2>

		    <div className="error-desc">
		        Sorry, but the page you are looking for was either not found or does not exist. <br/>
		        Try refreshing the page or click the button below to go back to the Homepage.
		        <div><br/>
		            <a href="/" className="btn btn-primary"><span className="glyphicon glyphicon-home"></span> Go back to Homepage</a>
		        </div>
		    </div>
		</div>
	);
}

module.exports = NoMatch;
