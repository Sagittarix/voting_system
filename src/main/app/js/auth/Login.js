const React = require('react');
const axios = require('axios');
const LoginComponent = require('./LoginComponent');

const Login = React.createClass({
    getInitialState() {
        return ({ username: "", password: "" });
    },
    doLogin(e) {
        e.preventDefault();
        const loginData = {
            "username": this.state.username,
            "password": this.state.password
        };
        axios.post('http://localhost:8080/api/auth/login', JSON.stringify(loginData))
            .then(resp => {
                console.log(resp.data);
            })
            .catch(err => {
                console.log(err);
            })
    },
    handlePasswordChange(e) {
        this.setState({ password: e.target.value })
    },
    handleNameChange(e) {
        this.setState({ username: e.target.value })
    },
    render: function() {
        return (
            <LoginComponent
                username={this.state.username}
                password={this.state.password}
                changeName={this.handleNameChange}
                changePassword={this.handlePasswordChange}
                doLogin={this.doLogin}
            />
        );
    }
});

module.exports = Login;