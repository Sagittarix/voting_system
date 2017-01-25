var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var CandidateRow = React.createClass({
    propTypes: {
        
    }, 
    render: function() {
        return (
            <tr>
                <td>{this.props.candidate.firstName}</td>
                <td>{this.props.candidate.lastName}</td>
                <td>{this.props.candidate.personId}</td>
                <td>{this.props.candidate.partyName}</td>
            </tr>
        );
    }
});

module.exports = CandidateRow;