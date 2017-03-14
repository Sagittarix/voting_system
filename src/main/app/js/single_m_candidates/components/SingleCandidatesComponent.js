var React = require('react');
var Helper = require('../../utils/Helper');

var SingleCandidatesComponent = React.createClass({
    getInitialState() {
        return ({ ASC: true });
    },
    toggleSortOrder() {
        this.setState({ ASC: !this.state.ASC });
    },
    sortDistricts() {
        return Helper.sort(this.props.districts, this.state.ASC);
    },
    render() {
        var rotation = (this.state.ASC) ? " Z-A" : "A-Z";
        return (
            <div className="container">
                <div className="row grayed">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item location2" style={{display: "inline-block", verticalAlign: "middle", width: "100%", color: 'white', position: "relative", zIndex: "0" }}>
                            <span id="district-list-header">Apygardų sąrašas</span>
                            <span
                                id="sort-districts-button"
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

                    <div className="col-md-4 units-create-area"></div>
                </div>
            </div>
        );
    }
});

module.exports = SingleCandidatesComponent;
