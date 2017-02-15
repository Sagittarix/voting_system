var React = require('react');
var Validations = require('../../../utils/Validations');

var NewCountyInlineForm = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [] });
    },
    submit: function() {
        //e.preventDefault();
        var errors = Validations.checkErrorsCountyForm(this.props.name, this.props.count);
        if (errors.length > 0) {
            var style={ marginTop: 10 };
            this.setState({ jsErrors: Validations.prepareErrors(errors, style) });
        } else {
            this.setState({ jsErrors: [] });
            this.props.submit();
        }
    },
    springErrors: function() {
        return Validations.prepareErrors(this.props.springErrors);
    },
    render: function() {
        return (
            <div className="inline-add-county-form">
                <form>
                    <div className="form-group">
                        <input type="text" onChange={this.props.changeName} className="form-control" value={this.props.name} placeholder="Apylinkės pav."/>
                    </div>
                    <div className="form-group">
                        <input type="number" onChange={this.props.changeVoterCount} className="form-control" value={this.props.count} placeholder="Gyv. skaičius" min={1}/>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary btn-sm" onClick={this.submit}>Sukurti</button>
                        <button className="btn btn-warning btn-sm" onClick={this.props.cancel}>Atšaukti</button>
                    </div>
                </form>
                <div id="inline-form-errors">
                    {this.state.jsErrors}
                    {this.springErrors()}
                </div>
            </div>
        )
    }
});


module.exports = NewCountyInlineForm;
