var React = require('react');
var SM_ResultsFormComponent = require('./SM_ResultsFormComponent');
var Validations = require('../../../utils/Validations');

var SM_CountyResultsComponent = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [] });
    },
    submitResults: function(e) {
        e.preventDefault();
        var errors = Validations.checkErrorsSMform(this.props.dictionary,
                                               this.props.spoiled);
        if (errors.length > 0) {
            this.setState({ jsErrors: errors });
        } else {
            this.setState({ jsErrors: [] });
            this.props.submitSMresults();
        }
    },
    clearForm: function() {
        this.setState({ jsErrors: [] });
        this.props.clearForm();
    },
    prepareJSerrors: function() {
        return Validations.prepareJSerrors(this.state.jsErrors, "Klaida rezultatuose", {marginTop: 15});
    },
    render: function() {
        var jsErrors = (this.state.jsErrors.length > 0) ? this.prepareJSerrors() : [];
        var springErrors = (this.props.springErrors.length > 0) ? this.props.springErrors : [];

        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">
                        <SM_ResultsFormComponent
                            spoiled={this.props.spoiled}
                            changeSpoiled={this.props.changeSpoiled}
                            candidates={this.props.candidates}
                        />
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            {this.props.representative}
                            <div style={{ marginTop: 30 }}>
                                <button className="btn btn-default btn-md county-results-form-btns" onClick={this.submitResults}>
                                    SIŲSTI REZULTATUS
                                </button>
                                <button className="btn btn-default btn-md county-results-form-btns" onClick={this.clearForm}>
                                    IŠVALYTI FORMĄ
                                </button>
                            </div>
                            {jsErrors}
                            {springErrors}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = SM_CountyResultsComponent;
