var React = require('react');
var SM_ResultsFormComponent = require('./SM_ResultsFormComponent');
var Validations = require('../../../utils/Validations');

var SM_CountyResultsComponent = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [] });
    },
    submitResults: function() {
        //e.preventDefault();
        var errors = Validations.checkErrorsSMform(this.props.dictionary,
                                               this.props.spoiled);
        if (errors.length > 0) {
            this.setState({ jsErrors: Validations.prepareErrors(errors) });
        } else {
            this.setState({ jsErrors: [] });
            this.props.submitSMresults();
        }
    },
    clearForm: function() {
        this.setState({ jsErrors: [] });
        this.props.clearForm();
    },
    render: function() {
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
                            <button className="btn btn-primary btn-md" onClick={this.submitResults}>
                                SIŲSTI REZUS
                            </button>
                            <button className="btn btn-primary btn-sm" onClick={this.clearForm}>
                                CLEAR-FORM
                            </button>
                            {this.state.jsErrors}
                            {this.props.springErrors}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = SM_CountyResultsComponent;
