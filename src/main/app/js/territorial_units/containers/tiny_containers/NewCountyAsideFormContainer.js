var React = require('react');
var NewCountyAsideFormComponent = require('../../components/tiny_components/NewCountyAsideFormComponent');
var NewCountyFormButton = require('../../components/tiny_components/NewCountyFormButton');
var Validations = require('../../../utils/Validations');

var  NewCountyAsideFormContainer = React.createClass({
    getInitialState: function() {
        return ({ showAsideForm: false, countyName: "", voterCount: undefined });
    },
    handleSwitchState: function() {
        this.setState({ showAsideForm: !this.state.showAsideForm });
    },
    handleCountyCancel: function() {
      this.setState({ showAsideForm: !this.state.showAsideForm, countyName: "", voterCount: undefined });
      this.props.reportErrors([]);
    },
    handleCountyAdd() {
        console.log(this.state.countyName);
        var errors = Validations.checkErrorsCountyForm(this.state.countyName, this.state.voterCount);
        if (errors.length != 0) {
            this.props.reportErrors(errors);
        } else {
            var body = {
                name: this.state.countyName,
                voterCount: this.state.voterCount
            };
            this.props.addCounty(body);
            this.setState({ showAsideForm: false, countyName: "", voterCount: undefined });
            this.props.reportErrors([]);
        }
    },
    handleNameChange: function(e) {
        this.setState({ countyName: e.target.value});
    },
    handleVoterCountChange: function(e) {
        this.setState({ voterCount: e.target.value});
    },
    render: function() {
        if (this.state.showAsideForm) {
            return <NewCountyAsideFormComponent
                cancel={this.handleCountyCancel}
                add={this.handleCountyAdd}
                changeName={this.handleNameChange}
                changeCount={this.handleVoterCountChange}
                name={this.state.countyName}
                count={this.state.voterCount}
            />
        } else {
            return <NewCountyFormButton
                renderCountyForm={this.handleSwitchState}
            />
        }
    }
});

module.exports = NewCountyAsideFormContainer;
