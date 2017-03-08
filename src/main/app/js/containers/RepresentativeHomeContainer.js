var React = require('react');
var RepresentativePanelComponent = require('../components/RepresentativePanelComponent');
var axios = require('axios');
var spring = require('../config/SpringConfig');

var RepresentativeHomeContainer = React.createClass({
    getInitialState() {
        return {
            representative: false
        };
    },
    contextTypes: {
        router: React.PropTypes.object.isRequired
    },
    /*componentWillReceiveProps(newProps) {
        if (newProps.currentUser != this.state.currentUser) this.setState({ currentUser: newProps.currentUser });
    },*/
    componentDidMount() {
        const _this = this;
        let fd = new FormData();
        fd.append("role", "ROLE_REPRESENTATIVE");
        axios.post(spring.localHost.concat('/api/auth/role'), fd)
            .then(resp => {
                if (resp.data == false) {
                    _this.context.router.push('/')
                } else {
                    _this.setState({ representative: true });
                }
            })
            .catch(err => {
                console.log(err);
            });
    },
    render: function() {
        let displayer;
        if (this.state.representative) {
            displayer = (
                <div>
                    <RepresentativePanelComponent location={this.props.location} />
                    <div className="main-layout">
                        {this.props.children}
                    </div>
                </div>
            );
        } else {
            displayer = <div></div>
        }

        return displayer;
    }
});

module.exports = RepresentativeHomeContainer;