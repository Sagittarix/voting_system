var React = require('react');
var NewPartyAsideForm = require('../containers/NewPartyAsideForm');
var Helper = require('../../utils/Helper');

var PoliticalUnitsComponent = React.createClass({
    getInitialState() {
        return ({ ASC: true });
    },
    toggleSortOrder() {
        this.setState({ ASC: !this.state.ASC });
    },
    sortParties() {
        return Helper.sort(this.props.parties, this.state.ASC);
    },
    render() {
        var rotation = (this.state.ASC) ? " Z-A" : "A-Z";
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item list-group-item-success">
                            <span>Partijų sąrašas</span>
                            <span
                                className="btn btn-sm btn-success no-background"
                                onClick={this.toggleSortOrder}>
                                Rušiuoti {rotation}
                            </span>
                        </div>
                        <div className="list-group-item" style={{ height: 'auto' }}>
                            {this.sortParties()}
                        </div>
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            <NewPartyAsideForm
                                create={this.props.create}
                                springErrors={this.props.springErrors}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = PoliticalUnitsComponent;
