var React = require('react');

var NewPartyAsideForm = React.createClass({
    create: function() {
        var file = this.refs.fileCSV.files[0];
        var fd = new FormData();
        fd.append('file',file);
        this.props.create(fd);
    },
    render: function() {
        return (
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
        )
    }
});

module.exports = NewPartyAsideForm;
