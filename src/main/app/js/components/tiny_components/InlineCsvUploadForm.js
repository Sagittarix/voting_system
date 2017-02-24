var React = require('react');
var Validations = require('../../utils/Validations');

var InlineCsvUploadForm = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [], springErrors: [] });
    },
    componentWillReceiveProps: function(newProps) {
        if (newProps.springErrors != this.state.springErrors) {
            this.setState({ springErrors: newProps.springErrors })
        }
    },
    upload: function(e) {
        e.preventDefault();
        var file = this.refs.fileCSV.files[0];
        var errors = Validations.validateCsv(file);

        if (errors.length > 0) {
            this.setState({ jsErrors: Validations.prepareJSerrors(errors, "Failo klaida"), springErrors: [] });
        } else {
            var fd = new FormData();
            fd.append('file',file);
            if (this.state.springErrors == []) this.refs.fileCSV.value = "";
            this.setState({ jsErrors: [] });
            this.props.upload(fd, this.props.associationId);
        }
    },
    springErrors: function() {
        return Validations.prepareSpringErrors(this.props.springErrors, {marginBottom: 10});
    },
    render: function() {
        var springErrors = (this.props.springErrors.length > 0) ? this.springErrors() : [];
        return (
            <div>
                <form id="inline-csv-form">
                    <div className="form-group browse-button-area">
                        <input type="file" ref="fileCSV" id="inputFile"/>
                    </div>
                    <div className="form-group upload-button-area">
                        <button type="submit" onClick={this.upload} className="btn btn-primary btn-sm">Siūsti</button>
                    </div>
                </form>
                <div id="inline-form-errors">
                    {this.state.jsErrors}
                    {springErrors}
                </div>
            </div>
        )
    }
});

module.exports = InlineCsvUploadForm;
