var React = require('react');

function AdminResultsViewComponent(props) {
    var display = {display: 'none'};
    if (props.countiesSelect != undefined) display={};

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-8 units-list-area">
                    <div className="list-group-item active">
                        <span>Apylinkių rezultatai</span>
                    </div>
                    <div className="list-group-item" style={{ height: 'auto' }}>
                        {props.counties}
                    </div>
                </div>
                <div className="col-md-4 units-create-area">
                    <div className="col-md-11">
                        <div className="list-group-item active">
                            Susiaurinti rezultatus
                        </div>
                        <div className="list-group-item">
                            <p>Pasirinkite apygardą</p>
                            <p>{props.districtsSelect}</p>
                            <div className="counties" style={ display }>
                                <p>Pasirinkite apylinkę</p>
                                <p>{props.countiesSelect}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

module.exports = AdminResultsViewComponent;
