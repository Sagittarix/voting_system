var React = require('react');
var ReactRouter = require('react-router');
var NewPartyAsideForm = require('./tiny_components/NewPartyAsideForm');
//var InlineCsvUploadForm = require('../../components/tiny_components/InlineCsvUploadForm');

function PoliticalUnitsComponent(props) {
    var candidates = [];
    if (props.activePartyId != undefined) {
        if (props.activeCandidates.length > 0 && props.show) {
            candidates = props.activeCandidates;
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-8 units-list-area">
                    <div className="list-group-item active">
                        <span>Partijų sąrašas</span>
                    </div>
                    <div className="list-group-item" style={{ height: 'auto' }}>
                        {props.parties}
                    </div>
                </div>
                <div className="col-md-4 units-create-area">
                    <div className="col-md-11">
                        <NewPartyAsideForm
                          create={props.create}
                          changeName={props.changeName}
                          name={props.name}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

module.exports = PoliticalUnitsComponent;
