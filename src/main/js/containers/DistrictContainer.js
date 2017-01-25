var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;
var helpers = require('../utils/helpers');

var CandidateListContainer = require('./CandidateListContainer');
var CountyListContainer = require('./CountyListContainer');


var DistrictContainer = React.createClass({
    propTypes: {
        
    }, 
    getInitialState() {
        return {
            id: 0,
            name: "",
            counties: [],
            candidates: []
        };
    },
    componentWillMount: function() {
        if (this.props.params == null) {
            this.setState({
                id: this.props.id,
                name: this.props.name,
                counties: this.props.counties,
                candidates: this.props.candidates
            })
        }
    },
    componentDidMount: function() {
        if (this.props.params != null) {
            districtId = this.props.params.id;
            if (districtId !== 'new') {
                helpers.getDistrictById(districtId)
                    .then(function(result) {
                        this.setState({
                            id: result.data.id,
                            name: result.data.name,
                            counties: result.data.counties,
                            candidates: result.data.candidates
                        })
                    }.bind(this))
                    .catch(function(error) {
                        console.log(error)
                        console.log(error.response.data)
                        this.context.router.push('/districts')
                    }.bind(this))
            }
        }
    },
    handleDelete: function(e) {
        e.preventDefault()
        helpers.deleteDistrict(this.state.id)
            .then(function(result) {
                this.setState({id: 0})
            }.bind(this))
            .catch(function(error) {
                console.log(error)
                console.log(error.response.data)
            }.bind(this))
    },
    render: function() {
        return (
            <div>
            {this.state.id !== 0 &&
                <div className="panel panel-default">
                    <div className="panel-heading">
                            <h3 className="panel-title">
                                {this.state.name}
                                <button onClick={this.handleDelete} className="btn">Šalinti</button>
                            </h3>
                    </div>
                    <div className="panel-body">
                        <CountyListContainer counties={this.state.counties} districtId={this.state.id}/>
                        <CandidateListContainer candidates={this.state.candidates} districtId={this.state.id}/>
                    </div>
                </div>
            }
            </div>
        );
    }
});

DistrictContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

module.exports = DistrictContainer;