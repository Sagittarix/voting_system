var React = require('react');

var ReactRouter = require('react-router');
var NewDistrictAsideForm = require('./tiny_components/NewDistrictAsideForm');

function TerritorialBreakdownComponent(props) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-8 units-list-area">
                    <div className="list-group-item active">
                        <span>Apygardų sąrašas</span>
                    </div>
                    <div className="list-group-item" style={{ height: 'auto' }}>
                        {props.districts}
                    </div>
                </div>

                <div className="col-md-4 units-create-area">
                    <div className="col-md-11">
                        <NewDistrictAsideForm
                            counties={props.counties}
                            addCounty={props.addCounty}
                            changeName={props.changeName}
                            name={props.name}
                            create={props.create}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

module.exports = TerritorialBreakdownComponent;
