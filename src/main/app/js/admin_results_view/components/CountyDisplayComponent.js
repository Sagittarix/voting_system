var React = require('react');

function CountyDisplayComponent(props) {
    var display = (!props.show) ? {display: 'none'} : {};

    var smBtn = 'btn btn-default btn-sm';
    var mmBtn = 'btn btn-default btn-sm';
    if (props.smDisplay == undefined) {
        // do nothing
    } else if (props.smDisplay) {
        smBtn += ' active';
    } else {
        mmBtn += ' active';
    }

    var deleteBtn = undefined;
    var results = (props.smDisplay == undefined) ? "PASIRINKITE MANDATO TIPĄ" : "REZULTATŲ NĖRA";
    var confirmBtn = undefined;
    if (props.results != undefined) {
        deleteBtn = (
            <button className="btn btn-default btn-sm floaters-right" onClick={props.delete}>
                Pašalinti <span className="glyphicon glyphicon-remove"></span>
            </button>
        );
        confirmBtn = props.confirmBtn;
        results = props.results;
    }

    return (
        <div className="unit">
            <div className="list-group-item active">
                <div onClick={props.toggleShow} style={{ cursor: 'pointer' }}>
                    <strong>{props.county.name}</strong> (Apygarda: {props.county.districtName})
                </div>
            </div>
            <div style={ display }>
                <div className="list-group-item">
                    <button onClick={props.displaySM} className={smBtn}>1-MANDATINIAI</button>
                    <button onClick={props.displayMM} className={mmBtn}>M-MANDATINIAI</button>
                    {deleteBtn}
                    {confirmBtn}
                </div>
                {results}
            </div>

        </div>
    );
}

module.exports = CountyDisplayComponent;
