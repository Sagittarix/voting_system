var React = require('react');

function DistrictDisplayComponent(props) {
    var del = function() { props.delete(props.index) };
    var counties = (props.show) ? props.counties : [];
    var display = (props.showActions) ? {} : { display: 'none' };

    return (
	     <div className="unit">
            <div className="list-group-item active" id="unit-header" onMouseOver={props.onMouseOver} onMouseOut={props.onMouseOut}>
                <div onClick={props.toggleCountiesList} style={{ cursor: 'pointer' }} className="unit-name-area">
                    {props.name}
                </div>
                <div className="unit-actions-area" style={ display }>
                    <span onClick={del} style={{ cursor: 'pointer' }}>
                        <span className="glyphicon glyphicon-remove-sign"></span> šalinti
                    </span>
                </div>
            </div>
            {counties}
        </div>
    );
};

module.exports = DistrictDisplayComponent;
