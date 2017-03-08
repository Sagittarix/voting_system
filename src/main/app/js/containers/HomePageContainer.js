var React = require('react');
var HomePageComponent = require('../components/HomePageComponent');

var HomePageContainer = React.createClass({
    getInitialState: function () {
        return ({
            results : "RINKIMŲ REZULTATAI",
            representatives : "APYLINKIŲ ATSTOVAMS",
            admin : "ADMINISTRAVIMAS",
        })
    },
    render: function() {
        return <HomePageComponent
                results={this.state.results}
                representatives={this.state.representatives}
                admin={this.state.admin}
               />
    }
});

module.exports = HomePageContainer;
