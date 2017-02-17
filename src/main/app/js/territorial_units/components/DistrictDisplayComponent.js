var React = require('react');
var ConfirmationWindow = require('../../components/tiny_components/ConfirmationWindow');

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

                    <ConfirmationWindow
                        title="Ar tikrai norite pašalinti apygardos duomenis?"
                        body="Kartu su apygarda bus pašalinti visi su ja susiję duomenys - jos apylinkių atstovai, kandidatų sąrašas ir tt. Duomenų atstatymas neįmanomas."
                        onConfirm={del}
                    >
                        <span style={{ cursor: 'pointer' }}>
                            <span className="glyphicon glyphicon-remove-sign"></span> Šalinti
                        </span>
                    </ConfirmationWindow>

                </div>
            </div>
            {counties}
        </div>
    );
};

module.exports = DistrictDisplayComponent;
