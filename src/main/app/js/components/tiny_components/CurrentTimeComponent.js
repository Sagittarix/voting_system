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
						<span style={{color: 'white'}}>{this.state.time}</span>
				);
		}
});

module.exports = CurrentTimeComponent;
