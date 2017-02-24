var React = require('react');
var Helpers = require('../../utils/Helpers');

var CurrentTimeComponent = React.createClass({
		getInitialState: function() {
				return { time: '' };
		},
		componentWillMount: function() {
				setInterval(this.tick, 1000);
		},
		tick: function() {
				this.setState({ time: Helpers.currentTime() });
		},
		render: function() {
				return (
						<div style={{color: 'white'}}>{this.state.time}</div>
				);
		}
});

module.exports = CurrentTimeComponent;