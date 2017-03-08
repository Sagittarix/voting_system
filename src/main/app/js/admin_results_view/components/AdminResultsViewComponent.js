var React = require('react');
var Helper = require('../../utils/Helper');

var AdminResultsViewComponent = React.createClass({
    getInitialState() {
        return ({ ASC: true, needToSort: false });
    },
    toggleSortOrder() {
        this.setState({ ASC: !this.state.ASC, needToSort: true });
    },
    sortCounties() {
        return (this.state.needToSort) ? Helper.sort(this.props.counties, this.state.ASC) : this.props.counties;
    },
    render() {
        var display = {display: 'none'};
        if (this.props.countiesSelect != undefined) display={};
        var rotation = (this.state.ASC) ? " Z-A" : "A-Z";
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">
                        <div className="list-group-item active">
                            <span>Apylinkių rezultatai</span>
                            <span
                                className="btn btn-sm btn-primary no-background"
                                style={{ color: '#FFFFFF', borderColor: '#FFFFFF' }}
                                onClick={this.toggleSortOrder}>
                                Rušiuoti {rotation}
                            </span>
                        </div>
                        <div className="list-group-item" style={{ height: 'auto' }}>
                            {this.sortCounties()}
                        </div>
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            <div className="list-group-item active">
                                Susiaurinti rezultatus
                            </div>
                            <div className="list-group-item">
                                <p>Pasirinkite apygardą</p>
                                <p>{this.props.districtsSelect}</p>
                                <div className="counties" style={ display }>
                                    <p>Pasirinkite apylinkę</p>
                                    <p>{this.props.countiesSelect}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = AdminResultsViewComponent;
