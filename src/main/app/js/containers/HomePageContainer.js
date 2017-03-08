var React = require('react');
var HomePageComponent = require('../components/HomePageComponent');

var HomePageContainer = React.createClass({
    getInitialState: function () {
        return ({
            search : "KANDIDATO PAIEŠKA",
            countiesResults : "VIENMANDAČIAI REZULTATAI",
            districtsResults : "DAUGIAMANDAČIAI REZULTATAI",
            finalResults : "SUVESTINIAI REZULTATAI"
        })
    },
    render: function() {
        return <HomePageComponent
                    search={this.state.search}
                    countiesResults={this.state.countiesResults}
                    districtsResults={this.state.districtsResults}
                    finalResults={this.state.finalResults}
               />
    }
});

module.exports = HomePageContainer;
