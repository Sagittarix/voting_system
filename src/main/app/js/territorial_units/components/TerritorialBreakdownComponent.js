var React = require('react');
var NewDistrictAsideForm = require('./NewDistrictAsideForm');
var Helper = require('../../utils/Helper');

var TerritorialBreakdownComponent = React.createClass({
    getInitialState() {
        return ({ ASC: true, needToSort: false });
    },
    toggleSortOrder() {
        this.setState({ ASC: !this.state.ASC, needToSort: true });
    },
    sortDistricts() {
        return (this.state.needToSort) ? Helper.sort(this.props.districts, this.state.ASC) : this.props.districts;
    },
    render() {
        var rotation = (this.state.ASC) ? " Z-A" : "A-Z";
        return (
            <div className="container">
                {/*<nav className="navbar navbar-inverse" data-spy="affix" data-offset-top="197">*/}
                <div className="row grayed">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item active location1" style={{display: "inline-block", verticalAlign: "middle", width: "100%"}}>
                            <span id="district-list-header">Apygardų sąrašas</span>
                            <span
                                id="sorting-button"
                                className="btn btn-sm btn-success no-background"
                                style={{ color: '#FFFFFF', borderColor: '#FFFFFF'}}
                                onClick={this.toggleSortOrder}>
                                Rūšiuoti {rotation}
                            </span>
                        </div>
                        <div className="list-group-item" style={{ height: 'auto' }}>
                            {this.sortDistricts()}
                        </div>
                    </div>

                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            <NewDistrictAsideForm
                                counties={this.props.counties}
                                addCounty={this.props.addCounty}
                                changeName={this.props.changeName}
                                name={this.props.name}
                                create={this.props.create}
                                springErrors={this.props.springErrors}
                                clearCountyForm={this.props.clearCountyForm}
                                popupAlert={this.props.popupAlert}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    },
});

module.exports = TerritorialBreakdownComponent;
