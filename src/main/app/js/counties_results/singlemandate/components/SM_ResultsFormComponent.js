var React = require('react');
var SpoiledBallotsInputComponent = require('../../shared/SpoiledBallotsInputComponent');

function SM_ResultsFormComponent(props) {
    return (
        <form>
            <SpoiledBallotsInputComponent
                spoiled={props.spoiled}
                changeSpoiled={props.changeSpoiled}
            />
            <div className="list-group-item active">
                <span>Apylinkės kandidatų sąrašas (VIENMANDAČIAI)</span>
            </div>
            <div className="list-group-item" style={{ height: 'auto' }}>
                {props.candidates}
            </div>
        </form>
    );
}

module.exports = SM_ResultsFormComponent;
