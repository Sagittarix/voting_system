var React = require('react');

var MM_CountyResultsDisplayComponent = React.createClass({
    render: function() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item active">
                            <span>Apylinkės kandidatų rezultatai (DAUGIAMANDAČIAI)</span>
                        </div>
                        <div className="list-group-item">
                            <p className="county-results">
                                Sugadintų biuletinių skaičius: &nbsp; {this.props.spoiled}
                            </p>
                        </div>
                        <div className="list-group-item" style={{ height: 'auto' }}>
                            {this.props.parties}
                        </div>
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            {this.props.representative}
                            {this.props.dateTime}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = MM_CountyResultsDisplayComponent;
