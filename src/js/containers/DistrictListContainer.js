var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;
var helpers = require('../utils/helpers');

var DistrictContainer = require('./DistrictContainer');


var DistrictListContainer = React.createClass({
    propTypes: {
        
    }, 
    getInitialState: function() {
        return {
            districts: []
        };
    },
    componentDidMount: function() {
        helpers.getDistrictList()
                .then(function(result) {
                    this.setState({
                        districts: result.data
                    })
                }.bind(this))
    },
    render: function() {
        var districtList = [];
        this.state.districts.forEach(function(district, idx) {
            districtList.push(
                <DistrictContainer 
                    id={district.id}
                    name={district.name}
                    counties={district.counties}
                    candidates={district.candidates}
                    key={idx}/>
            )
        })
        return (
            <div>
                {districtList}
                <Link to="districts/new">
                    <button className="btn btn-primary"> 
                        Pridėti naują apygardą
                    </button>
                </Link>
            </div>
        );
    }
});

module.exports = DistrictListContainer;