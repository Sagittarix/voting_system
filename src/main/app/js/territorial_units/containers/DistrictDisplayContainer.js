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
                  voterCount: undefined,
                  counties: [],
                  hoverState: false,
                  springErrors: [] });
    },
    componentDidMount: function() {
        this.setState({ counties: this.props.district.counties });
    },
    toggleCountiesList: function() {
        this.setState({ showCounties: !this.state.showCounties });
    },
    prepareCounties: function() {
        var counties = [];
        this.state.counties.forEach((c, index) => {
            counties.push(<CountyDisplayComponent
                key={index}
                name={c.name}
            />);
        });
        counties.push(
            <div className="list-group-item">
                {this.toggleInlineForm()}
            </div>
        );
        return counties;
    },
    addCountyToState: function(nCounty) {
        var counties = this.state.counties;
        counties.push(nCounty);
        this.setState({ counties: counties });
    },
    toggleShowInlineState: function() {
        this.setState({
            showInlineForm: !this.state.showInlineForm,
            countyName: "",
            voterCount: undefined
        });
    },
    handleCountyCreate() {
        //e.preventDefault();
        var _this = this;
        var body = {
            name: this.state.countyName,
            voterCount: this.state.voterCount,
            districtId: this.props.district.id
        };
        axios.post('http://localhost:8080/api/county/', body)
            .then(function(resp) {
                _this.setState({ showInlineForm: false, countyName: "", voterCount: undefined, countyBackendErrors: [] });
                _this.addCountyToState(resp.data);
                _this.setState({ springErrors: [] })
            })
            .catch(function(err) {
                _this.setState({ springErrors: err.response.data.errorsMessages })
            });
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
    handleDistrictDestroy(idx) {
        var _this = this;
        var deletePath = "http://localhost:8080/api/district/" + this.props.district.id + "";
        axios.delete(deletePath)
            .then(function(resp) {
                _this.props.delete(idx);
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
                      name={this.state.countyName}
                      count={this.state.voterCount}
                      springErrors={this.state.springErrors}
                   />
        } else {
            return <NewCountyFormButton
                renderCountyForm={this.toggleShowInlineState}
            />
        }
    },
    toggleHoverState: function() {
        this.setState({ hoverState: !this.state.hoverState });
    },
    render: function() {
        return <DistrictDisplayComponent
                    name={this.props.district.name}
                    counties={this.prepareCounties()}
                    toggleCountiesList={this.toggleCountiesList}
                    show={this.state.showCounties}
                    delete={this.handleDistrictDestroy}
                    index={this.props.index}
                    showActions={this.state.hoverState}
                    onMouseOver={this.toggleHoverState}
                    onMouseOut={this.toggleHoverState}
               />
    }
});

module.exports = DistrictDisplayContainer;
