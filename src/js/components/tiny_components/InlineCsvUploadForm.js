var React = require('react');

var InlineCsvUploadForm = React.createClass({
    upload: function() {
        var file = this.refs.fileCSV.files[0];
        var fd = new FormData();
        fd.append('file', file);
        this.props.upload(fd, this.props.associationId);
    },
    render: function() {
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
            </div>
        )
    }
});

module.exports = InlineCsvUploadForm;
