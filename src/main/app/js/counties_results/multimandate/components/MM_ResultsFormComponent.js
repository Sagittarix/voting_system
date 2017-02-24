var React = require('react');
var SpoiledBallotsInputComponent = require('../../shared/SpoiledBallotsInputComponent');

function MM_ResultsFormComponent(props) {
    return (
        <form>
            <SpoiledBallotsInputComponent
                spoiled={props.spoiled}
                changeSpoiled={props.changeSpoiled}
            />
            <div className="list-group-item active">
                <span>Partijų sąrašas (DAUGIAMANDAČIAI)</span>
            </div>
            <div className="list-group-item" style={{ height: 'auto' }}>
                {props.parties}
            </div>
        </form>
    );
}

module.exports = MM_ResultsFormComponent;
