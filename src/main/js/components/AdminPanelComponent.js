var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var AdminPanelComponent = React.createClass({
	render: function() {
		return (
			<div>
				<div className="menu">
					<ul className="nav nav-tabs" id="bootstrap-overrides-nav-tabs">
						<li className="tab"><Link to="administravimas/teritorinis-suskirstymas">Teritoriniai vienetai</Link></li>
						<li className="tab"><Link to="administravimas/apygardu-kandidatai">Apygardu kandidatai</Link></li>
						<li className="tab"><Link to="administravimas/apylinkiu-atstovai">Apylinkių atstovai</Link></li>
						<li className="tab"><Link to="administravimas/politinis-suskirstymas">Politiniai vienetai</Link></li>
						<li className="tab"><Link to="administravimas/apylinkiu-rezultatai">Apylinkių rezultatai</Link></li>
					</ul>
				</div>
				<div className="main-layout">
					{this.props.children}
				</div>
			</div>
        );
    }
});

module.exports = AdminPanelComponent;
