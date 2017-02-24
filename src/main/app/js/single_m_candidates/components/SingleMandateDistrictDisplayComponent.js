var React = require('react');
var InlineCsvUploadForm = require('../../components/tiny_components/InlineCsvUploadForm');
var ConfirmAction = require('../../components/tiny_components/ConfirmAction');

function SingleMandateDistrictDisplayComponent(props) {
    var display; var actions; var candidates;
    if (!props.show) {
        display = {display: 'none'};
        candidates = props.candidates;
    }
    if (props.district.candidates.length > 0) {
        actions =
            <ConfirmAction
                title="Ar tikrai norite pašalinti apygardos kandidatų sąrašą?"
                body="Duomenų atstatymas neįmanomas."
                onConfirm={props.deleteCandidates}
            >
                <p className="remove-units-element" style={{ cursor: 'pointer' }} >
                    <span className="glyphicon glyphicon-remove-sign">
                    </span> &nbsp;
                    šalinti kandidatus
                </p>
            </ConfirmAction>
    } else {
        actions = <InlineCsvUploadForm
                      upload={props.upload}
                      associationId={props.district.id}
                      springErrors={props.springErrors}
                  />
    }
    return (
        <div className="unit">
            <div className="list-group-item active">
                <div onClick={props.toggleShow} style={{ cursor: 'pointer' }}>
                    {props.district.name}
                </div>
            </div>
            <div style={ display }>
                <div className="list-group-item">
                    {actions}
                </div>
                {props.candidates}
            </div>
        </div>
    );
}

module.exports = SingleMandateDistrictDisplayComponent;
