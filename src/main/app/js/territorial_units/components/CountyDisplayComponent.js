var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

function CountyDisplayComponent(props) {
    return (
        <div className="list-group-item">
            <span>{props.name}</span>
        </div>
    );
};

module.exports = CountyDisplayComponent;
