var React = require('react');
var Validations = require('../../../utils/Validations');

var NewPartyAsideForm = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [], springErrors: [] });
    },
    componentWillReceiveProps: function(newProps) {
        if (newProps.springErrors != this.state.springErrors) {
            this.setState({ springErrors: newProps.springErrors })
        }
    },
    create: function() {
        //e.preventDefault();
        var file = this.refs.fileCSV.files[0];
        var errors = Validations.checkErrorsPartyAsideForm(this.props.name, file);

        if (errors.length > 0) {
            var style={ marginTop: 10 };
            this.setState({ jsErrors: Validations.prepareErrors(errors, style), springErrors: [] });
        } else {
            var fd = new FormData();
            fd.append('file',file);
            if (this.state.springErrors == []) this.refs.fileCSV.value = "";
            this.setState({ jsErrors: [] });
            this.props.create(fd);
        }
    },
    springErrors: function() {
        return Validations.prepareErrors(this.state.springErrors);
    },
    render: function() {
        return (
            <div>
                <form>
                    <div className="form-group">
                        <label htmlFor="inputCounty">Partijos pavadinimas</label>
                        <input type="text" className="form-control" id="inputCountyName" onChange={this.props.changeName} value={this.props.name}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputFile">Įkelti partijos sąrašą</label>
                        <input ref="fileCSV" type="file" id="inputFile"/>
                    </div>
                    <div className="form-group">
                        <button type="submit" onClick={this.create} className="btn btn-primary btn-md">Sukurti</button>
                    </div>
                </form>
                <div className="form-group errors-area">
                    {this.state.jsErrors}
                    {this.springErrors()}
                </div>
            </div>
        )
    }
});

module.exports = NewPartyAsideForm;
