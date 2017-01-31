var React = require('react');

function AddedCountyDisplayComponent(props) {
    var remove = function() {
        props.remove((props.index));
    };
    return (
        <div style={{ padding: 3 }}>
            <p style={{ display: 'inline-block' }}>{props.name}</p><span className="glyphicon glyphicon-remove-sign" style={{ paddingLeft: 10, cursor: 'pointer' }} onClick={remove}></span>
        </div>
    )
}

module.exports = AddedCountyDisplayComponent;
