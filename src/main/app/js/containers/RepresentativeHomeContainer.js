var React = require('react');
var RepresentativePanelComponent = require('../components/RepresentativePanelComponent');
var axios = require('axios');
var spring = require('../config/SpringConfig');

var RepresentativeHomeContainer = React.createClass({
    propTypes: {
        
    }, 
    getInitialState() {
        return {
            representative: undefined
        };
    },
    componentDidMount() {
        const url = spring.localHost.concat("/api/county-rep/") + this.props.params.id;

        axios.get(url)
            .then(function(response) {
                this.setState({ 
                    representative: response.data,
                    county: response.data.county,
                    district: response.data.district
                 });
            }.bind(this))
            .catch(function(err) {
                console.log(err);
            });
    },

    render: function() {

        const {representative, county, district } = this.state
        
        let childrenWithProps = React.Children.map(this.props.children, (child) => 
            React.cloneElement(child, {
                representative: representative,
                county: county,
                district: district
            })
        );
        
        if (!representative) {
            return <div></div>
        }

        return (
            <div>
                <RepresentativePanelComponent repId={representative.id}/>
                <div className="main-layout">
                    {childrenWithProps}
                </div>
            </div>
        );
    }
});

module.exports = RepresentativeHomeContainer;