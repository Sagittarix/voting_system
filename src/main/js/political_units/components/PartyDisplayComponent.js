var React = require('react');
var InlineCsvUploadForm = require('../../components/tiny_components/InlineCsvUploadForm');

function PartyDisplayComponent(props) {
    var del = function() { props.delete(props.index, props.party.id) };
    var delCandidates = function() { props.deleteCandidates(props.party.id) };

    var display; var actions;
    if (!props.show) display = {display: 'none'};
    if (props.candidates.length > 0) {
      actions = <p onClick={delCandidates} className="remove-units-element" style={{ cursor: 'pointer' }}><span className="glyphicon glyphicon-remove-sign"></span> šalinti narius</p>
    } else {
      actions = <InlineCsvUploadForm
                    upload={props.upload}
                    associationId={props.party.id}
                />
    }

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
                    <p onClick={del} style={{ cursor: 'pointer', paddingTop: 10 }}><span className="glyphicon glyphicon-remove-sign"></span> šalinti partiją</p>
                </div>
                {props.candidates}
            </div>

        </div>
    );
}

module.exports = PartyDisplayComponent;
