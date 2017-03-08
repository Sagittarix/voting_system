var React = require('react');
var NavigationBarComponent = require('./components/NavigationBarComponent');

var Application = React.createClass({
	render: function() {
		return (
			<div className="container">
				<NavigationBarComponent/>
				{this.props.children}
			</div>
		);
	}
});

module.exports = Application;
