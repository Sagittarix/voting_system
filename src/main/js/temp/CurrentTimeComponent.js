var CurrentTimeComponent = React.createClass({
	getInitialState: function() {
		return { time: '' };
	},
	componentWillMount: function() {
		setInterval(this.tick, 1000);
	},
	tick: function() {
		this.setState({ time: new Date().toLocaleTimeString() });
	},
	render: function() {
		return (
			<span style={{color: 'white'}}>{this.state.time}</span>
		);
	}
});

window.CurrentTimeComponent = CurrentTimeComponent;