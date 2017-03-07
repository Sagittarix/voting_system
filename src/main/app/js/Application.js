var React = require('react');
var axios = require('axios');
var NavigationBarComponent = require('./components/NavigationBarComponent');
var spring = require('./config/SpringConfig');

var Application = React.createClass({
    contextTypes: {
        router: React.PropTypes.object.isRequired
    },
	getInitialState() {
		return ({ currentUser: "" });
	},
	componentDidMount() {
		console.log("COMPONENT DID MOUNT");
		const _this = this;
		axios.post(spring.localHost.concat('/api/auth/username'))
		 	.then(resp => {
		 		if (resp.data != "") _this.setState({ currentUser: resp.data });
		 	})
		 	.catch(err => {
		 		console.log(err);
		 	});
	},
	render: function() {
		return (
			<div className="container">
				<NavigationBarComponent currentUser={ this.state.currentUser } />
				{this.props.children}
			</div>
		);
	}
});

module.exports = Application;
