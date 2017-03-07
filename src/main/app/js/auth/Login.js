const React = require('react');
const axios = require('axios');
const LoginComponent = require('./LoginComponent');
const spring = require('../config/SpringConfig');

const Login = React.createClass({
    contextTypes: {
        router: React.PropTypes.object.isRequired
    },
    getInitialState() {
        return ({ username: "", password: "", loginError: false });
    },
    doLogin(e) {
        e.preventDefault();
        const _this = this;
        let fd = new FormData();
        fd.append("username", this.state.username);
        fd.append("password", this.state.password);

        axios.post(
                spring.localHost.concat('/login'),
                fd,
                { headers: { 'Content-Type': 'multipart/form-data' } }
            )
            .then(resp => {
                if (resp.data === "REPRESENTATIVE") {
                    _this.props.router.push("/atstovui");
                } else if (resp.data === "ADMIN") {
                    _this.props.router.push("/administravimas");
                } else {
                    _this.props.router.push("/prisijungti");
                }
            })
            .catch(err => {
                console.log(err);
                _this.setState({ loginError: true });
            })
    },
    handlePasswordChange(e) {
        this.setState({ password: e.target.value })
    },
    handleNameChange(e) {
        this.setState({ username: e.target.value })
    },
    prepareloginError() {
        let error = [];
        if (this.state.loginError) {
            error =(
                <div className="form-group alert alert-danger login">
                    <label>Neteisingi prisijungimo duomenys</label>
                </div>
            );
        }
        return error;
    },
    render: function() {
        return (
            <LoginComponent
                username={this.state.username}
                password={this.state.password}
                changeName={this.handleNameChange}
                changePassword={this.handlePasswordChange}
                doLogin={this.doLogin}
                loginError={this.prepareloginError()}
            />
        );
    }
});

module.exports = Login;