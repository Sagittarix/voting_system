var React = require('react');
var InlineCsvUploadForm = require('../../components/tiny_components/InlineCsvUploadForm');
var ConfirmAction = require('../../components/tiny_components/ConfirmAction');

function PartyDisplayComponent(props) {
    var del = function() { props.delete(props.index, props.party.id) };
    var delCandidates = function() { props.deleteCandidates(props.party.id) };

    var display, actions;
    if (!props.show) display = {display: 'none'};
    if (props.candidates.length > 0) {
      actions =
            <ConfirmAction
                title="Ar tikrai norite pašalinti apygardos kandidatų sąrašą?"
                body="Duomenų atstatymas neįmanomas."
                onConfirm={delCandidates}
            >
                <p className="remove-units-element confirmation-buttons">
                    <span className="glyphicon glyphicon-remove-sign">
                    </span> &nbsp;
                    šalinti narius
                </p>
            </ConfirmAction>
    } else {
      actions = <InlineCsvUploadForm
                    upload={props.upload}
                    associationId={props.party.id}
                    springErrors={props.springErrors}
                />
    }

    var confirmDeleteParty =
        <ConfirmAction
            title="Ar tikrai norite pašalinti partiją?"
            body="Duomenų atstatymas neįmanomas."
            onConfirm={del}
        >
            <p className="confirmation-buttons">
                <span className="glyphicon glyphicon-remove-sign">
                </span> &nbsp;
                šalinti partiją
            </p>
        </ConfirmAction>

    return (
        <div className="unit">
            <div className="list-group-item active">
                <div onClick={props.toggleShow} style={{ cursor: 'pointer' }}>
                    {props.party.name}
                </div>
            </div>
            <div style={ display }>
                <div className="list-group-item">
                    {actions}
                    {confirmDeleteParty}
                </div>
                {props.candidates}
            </div>

        </div>
    );
}

module.exports = PartyDisplayComponent;
