var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var CountyRow = React.createClass({
    propTypes: {
        
    }, 
    render: function() {
        return (
            <tr>
                <td>{this.props.county.name}</td>
                <td>{this.props.county.voterCount}</td>
            </tr>
        );
    }
});

module.exports = CountyRow;