var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;
var axios = require('axios');
var ReactTable = require('react-table').default;
var spring = require('../config/SpringConfig');
var ChartContainer = require('./chart_components/ChartContainer');
var DataProcessor = require('./chart_components/DataProcessor');


var hide = { display: 'none' };

var ConsolidatedResultsView = React.createClass({
    getInitialState() {
        return ({ results: undefined });
    },
    componentWillMount() {
        axios.get(spring.localHost.concat('/api/results/consolidated/'))
            .then(function(response) {
                this.setState({
                    results: response.data,
                    chartData: DataProcessor.cleanConsolidatedResultsDataForChart(response.data.partyMandates),
                });
                console.log(response.data)
            }.bind(this))
            .catch(err => {
                console.log(err);
            });
    },

    render() {
        return (
            <div>
                {this.state.chartData 
                    && 
                    <ChartContainer 
                        data={this.state.chartData} 
                        showTooltip={false}
                        showPercent={false}
                    />
                }

            </div>
        );
    }
});

module.exports = ConsolidatedResultsView;