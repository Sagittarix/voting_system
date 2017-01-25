var React = require('react');
var ReactRouter = require('react-router');
var axios = require('axios')
var Link = ReactRouter.Link;
var helpers = require('../utils/helpers')

var CandidateRow = require('../components/CandidateRow');
var FileUploadContainer = require('./FileUploadContainer');


var CandidateListContainer = React.createClass({
    propTypes: {
        
    },
    getInitialState() {
        return {
            url: 'http://localhost:8080/api/district/' + this.props.districtId + '/candidates',
            candidates: this.props.candidates,
            notifications: ''
        };
    },
    componentWillReceiveProps(nextProps) {
        this.setState({
            candidates: nextProps.candidates,
            url: 'http://localhost:8080/api/district/' + nextProps.districtId + '/candidates'
        });
    },
    handleDeleteCandidateList: function(e) {
        helpers.deleteSingleMandateCandidateList(this.props.districtId)
            .then(function(response) {
                this.setState({candidates: []})
            }.bind(this))
            .catch(function(error) {
                console.log(error.response.data);
                this.setState({
                    notifications: error.response.data.message
                })                    
            })
    },
    handleUploadFile: function(promise) {
        promise
            .then(function(response) {
                this.setState({
                    candidates: response.data.candidates,
                    notifications: ""
                })
            }.bind(this))
            .catch(function(error) {
                console.log(error.response.data);
                this.setState({
                    notifications: error.response.data.message
                })
            }.bind(this))
        
    },
    render: function() {
        var candidateList = [];
        this.state.candidates.forEach(function(candidate, idx) {
            candidateList.push(<CandidateRow candidate={candidate} key={idx}/>)
        })
        return (
            <div>
                {candidateList.length > 0
                    && 
                    <div>
                        <table className="table table-condensed table-hover">
                            <thead>
                                <tr className="">
                                    <td>Vardas</td>
                                    <td>Pavardė</td>
                                    <td>Asmens Kodas</td>
                                    <td>Iškėlusi partija</td>
                                </tr>
                            </thead>
                            <tbody>
                                {candidateList}
                            </tbody>
                        </table>
                        <button type="button" className="btn btn-primary" onClick={this.handleDeleteCandidateList}>Šalinti kandidatų sąrašą</button>
                    </div>
                }
                {candidateList.length == 0
                    && 
                    <div>
                        <p style={{"color": "red"}}>Kandidatų sąrašas tuščias</p>
                        <FileUploadContainer onUpload={this.handleUploadFile} uploadToUrl={this.state.url}/>
                        <p style={{"color": "red"}}>{this.state.notifications}</p>
                    </div>
                }
            </div>
        );
    }
});

module.exports = CandidateListContainer;