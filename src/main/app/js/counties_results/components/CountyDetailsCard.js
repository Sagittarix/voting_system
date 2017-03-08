var React = require('react');

var CountyDetailsCard = React.createClass({
    propTypes: {
        county: React.PropTypes.object.isRequired
    }, 
    render: function() {
        return (
            <div>
                <div className="list-group-item active">
                    APYLINKES DUOMENYS
                </div>
                <div className="list-group-item">
                    DUOMENYS
                </div>
            </div>
        );
    }
});

module.exports = CountyDetailsCard;