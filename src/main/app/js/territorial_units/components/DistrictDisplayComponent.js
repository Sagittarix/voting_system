var React = require('react');
var ConfirmAction = require('../../components/tiny_components/ConfirmAction');

function DistrictDisplayComponent(props) {
    var del = function() { props.delete(props.index) };
    var counties = []; var actions = [];
    var popup = function() { $('.popoverDistrict').popover({ trigger: "hover" }) };
    if (props.show) {
        counties = props.counties;
        actions = (
            <div>
                <div className="list-group-item">
                    <ConfirmAction
                        title="Ar tikrai norite pašalinti apygardą?"
                        body="Duomenų atstatymas neįmanomas."
                        onConfirm={props.delete}
                    >
                        <p className="remove-units-element" style={{ cursor: 'pointer' }}>
                            <span className="glyphicon glyphicon-remove-sign">
                            </span> &nbsp;
                            šalinti apygardą
                        </p>
                    </ConfirmAction>
                </div>
                <div className="list-group-item" style={{ textAlign: 'center' }}><strong>Apylinkės</strong></div>
            </div>
        );
    }

    return (
	     <div className="unit">
            <div className="list-group-item active" id="unit-header"
                onClick={props.toggleCountiesList}
                style={{ cursor: 'pointer' }}
            >
                <div className="unit-name-area">{props.name}</div>
            </div>
            {actions}
            {counties}
        </div>
    );
};

module.exports = DistrictDisplayComponent;
