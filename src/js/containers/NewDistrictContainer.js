var React = require('react');
var Formsy = require('formsy-react');
var Form = Formsy.Form;
var MyInput = require('./MyInput')

var CountyInputs = require('./CountyInputs')
var helpers = require('../utils/helpers');


var NewDistrictContainer = React.createClass({
    propTypes: {
        
    }, 
    getInitialState() {
        return { fields: [], canSubmit: false, inputs: []};
    },
    addInput: function(e) {
        e.preventDefault();
        var input = {};
        input.id = Date.now();
        inputs = this.state.inputs;
        this.setState({ inputs: this.state.inputs.concat(input) });
    },
    removeInput: function(pos) {
        const inputs = this.state.inputs;
        this.setState({ inputs: inputs.slice(0, pos).concat(inputs.slice(pos+1)) })
    },
    enableButton() {
        this.setState({ canSubmit: true });
    },
    disableButton() {
        this.setState({ canSubmit: false });
    },
    submit(data) {
        helpers.saveNewDistrict(data)
            .then(function(result) {
                this.context.router.push('/districts')
            }.bind(this))
            .catch(function(error) {
                console.log(error)
                console.log(error.response.data);
            }.bind(this))
    },
    render: function() {
        const { fields, canSubmit, inputs } = this.state;

        return (
            <div>
                <div className="panel panel-default">
                    <div className="panel-heading">
                            <h3 className="panel-title">
                                Nauja apygarda
                            </h3>
                    </div>

                    <div className="panel-body">
                        <Form onSubmit={this.submit} onValid={this.enableButton} onInvalid={this.disableButton} className="many-fields">
                            <MyInput
                                value=""
                                type="text"
                                name={'name'}
                                placeholder="Pavadinimas"
                            />
                            <CountyInputs data={inputs} onRemove={this.removeInput} />
                            <button className="btn" onClick={this.addInput}>Pridėti apylinkę</button>
                            <div>
                                <button className="btn btn-primary" type="submit">Saugoti</button>
                            </div>
                        </Form>
                    </div>
                </div>
            </div>
        );
    }
});

NewDistrictContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

module.exports = NewDistrictContainer;