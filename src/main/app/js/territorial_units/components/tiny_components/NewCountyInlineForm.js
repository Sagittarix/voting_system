var React = require('react');
var Validations = require('../../../utils/Validations');

var NewCountyInlineForm = React.createClass({
    getInitialState: function() {
        return ({ jsErrors: [] });
    },
    submit: function() {
        var errors = Validations.checkErrorsCountyForm(this.props.name, this.props.count, this.props.address);
        if (errors.length > 0) {
            var style={ marginTop: 10 };
            this.setState({ jsErrors: Validations.prepareJSerrors(errors, ("Klaida registruojant apylinkę " + this.props.name), style) });
        } else {
            if (this.state.jsErrors.length > 0) this.setState({ jsErrors: [] });
            this.props.submit();
        }
    },
    springErrors: function() {
        return (this.props.springErrors.length > 0) ? Validations.prepareSpringErrors(this.props.springErrors) : [];
    },
    render: function() {
        return (
            <div>
                <form className="inline-add-county-form" style={{ minHeight: 45 }}>
                    <div className="form-group">
                        <input type="text"
                            onChange={this.props.changeName}
                            className="form-control"
                            value={this.props.name}
                            placeholder="Apylinkės pav."
                        />
                    </div>
                    <div className="form-group">
                        <input type="number"
                            onChange={this.props.changeVoterCount}
                            className="form-control"
                            value={this.props.count}
                            placeholder="Gyv. skaičius"
                            min={1}
                        />
                    </div>
                    <div className="form-group">
                        <input type="text"
                            onChange={this.props.changeAddress}
                            className="form-control"
                            value={this.props.address}
                            placeholder="Adresas"
                            min={1}
                        />
                    </div>
                    <div className="form-group">
                        <button id="inline-create-btn" className="btn btn-default btn-sm" onClick={this.submit}>
                            Sukurti
                        </button>
                        <button id="inline-cancel-btn" className="btn btn-default btn-sm" onClick={this.props.cancel}>
                            Atšaukti
                        </button>
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
