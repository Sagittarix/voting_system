var React = require('react');
var InlineCsvUploadForm = require('../../components/tiny_components/InlineCsvUploadForm');
var ConfirmationWindow = require('../../components/tiny_components/ConfirmationWindow');

function PartyDisplayComponent(props) {
    var del = function() { props.delete(props.index, props.party.id) };
    var delCandidates = function() { props.deleteCandidates(props.party.id) };

    var display; var actions;

    if (!props.show) display = {display: 'none'};
    if (props.candidates.length > 0) {
        actions =
            <ConfirmationWindow
                title="Ar tikrai norite pašalinti apygardos kandidatų sąrašą?"
                body="Duomenų atstatymas neįmanomas."
                onConfirm={delCandidates}
            >
                <p className="remove-units-element" style={{ cursor: 'pointer' }}>
                    <span className="glyphicon glyphicon-remove-sign">
                    </span>
                    šalinti narius
                </p>
            </ConfirmationWindow>
    } else {
      actions = <InlineCsvUploadForm
                    upload={props.upload}
                    associationId={props.party.id}
                    springErrors={props.springErrors}
                />
    }

    var confirmDeleteParty =
        <ConfirmationWindow
            title="Ar tikrai norite pašalinti partiją?"
            body="Duomenų atstatymas neįmanomas."
            onConfirm={del}
        >
            <p style={{ cursor: 'pointer', paddingTop: 10 }}>
                <span className="glyphicon glyphicon-remove-sign">
                </span>
                šalinti partiją
            </p>
        </ConfirmationWindow>

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
