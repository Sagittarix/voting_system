var React = require('react');
var axios = require('axios');
var DistrictDisplayComponent = require('../components/DistrictDisplayComponent');
var CountyDisplayComponent = require('../components/CountyDisplayComponent');
var NewCountyInlineForm = require('../components/tiny_components/NewCountyInlineForm');
var NewCountyFormButton = require('../components/tiny_components/NewCountyFormButton');

var DistrictDisplayContainer = React.createClass({
    getInitialState: function() {
        return ({ showCounties: false,
                  showInlineForm: false,
                  countyName: "",
                  countyAddress: "",
                  voterCount: undefined,
                  counties: [],
                  springErrors: [] });
    },
    componentDidMount: function() {
        this.setState({ counties: this.props.district.counties });
    },
    toggleCountiesList: function() {
        this.setState({ showCounties: !this.state.showCounties });
    },
    prepareCounties: function() {
        var stateCounties = this.state.counties;
        var counties = [];

        stateCounties.forEach((c, index) => {
            counties.push(
                <CountyDisplayComponent
                    key={index}
                    index={index}
                    county={c}
                    delete={this.handleCountyDelete}
                />
            );
        });
        counties.push(
            <div className="list-group-item" key={stateCounties.length}>
                {this.toggleInlineForm()}
            </div>
        );
        return counties;
    },
    toggleShowInlineState: function() {
        this.setState({
            showInlineForm: !this.state.showInlineForm,
            countyName: "",
            voterCount: undefined,
            countyAddress: ""
        });
    },
    handleCountyCreate() {
        var _this = this;
        var body = {
            name: this.state.countyName,
            voterCount: this.state.voterCount,
            address: this.state.countyAddress,
            districtId: this.props.district.id
        };
        axios.post('http://localhost:8080/api/county/', body)
            .then(function(resp) {
                var counties = _this.state.counties;
                counties.push(resp.data);

                _this.setState({
                    showInlineForm: false,
                    countyName: "",
                    voterCount: undefined,
                    countyAddress: "",
                    countyBackendErrors: [],
                    springErrors: [],
                    counties: counties
                });
            })
            .catch(function(err) {
                _this.setState({ springErrors: err.response.data.errorsMessages })
            });
    },
    handleCountyDelete: function(index) {
        var _this = this;
        var counties = this.state.counties;

        axios({
            method: 'delete',
            url: 'http://localhost:8080/api/county/',
            params: { id: counties[index].id }
        })
        .then(function(resp) {
            counties.splice(index, 1);
            _this.setState({ counties: counties });
        })
        .catch(function(err) {
            console.log(err);
        })
    },
    handleCountyCancel: function() {
        this.setState({ showInlineForm: !this.state.showInlineForm });
    },
    handleNameChange: function(e) {
        this.setState({ countyName: e.target.value })
    },
    handleVoterCount: function(e) {
        this.setState({ voterCount: e.target.value })
    },
    handleAddressChange: function(e) {
        this.setState({ countyAddress: e.target.value })
    },
    handleDistrictDestroy() {
        var _this = this;
        var deletePath = "http://localhost:8080/api/district/" + this.props.district.id + "";
        axios.delete(deletePath)
            .then(function(resp) {
                _this.setState({ showCounties: false });
                _this.props.remove(_this.props.index);
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    toggleInlineForm() {
        if (this.state.showInlineForm) {
            return <NewCountyInlineForm
                      cancel={this.handleCountyCancel}
                      submit={this.handleCountyCreate}
                      changeName={this.handleNameChange}
                      changeVoterCount={this.handleVoterCount}
                      changeAddress={this.handleAddressChange}
                      name={this.state.countyName}
                      count={this.state.voterCount}
                      address={this.state.countyAddress}
                      springErrors={this.state.springErrors}
                   />
        } else {
            return <NewCountyFormButton
                renderCountyForm={this.toggleShowInlineState}
            />
        }
    },
    render: function() {
        return <DistrictDisplayComponent
                    name={this.props.district.name}
                    counties={this.prepareCounties()}
                    toggleCountiesList={this.toggleCountiesList}
                    show={this.state.showCounties}
                    delete={this.handleDistrictDestroy}
               />
    }
});

module.exports = DistrictDisplayContainer;
