var React = require('react');
var axios = require('axios');
var CountyResultsComponent = require('../components/CountyResultsComponent');
var ResultsDisplayComponent = require('../components/ResultsDisplayComponent');
var CandidateDisplayComponent = require('../components/CandidateDisplayComponent');
var MM_PartyComponent = require('../components/MM_PartyComponent')
var Validations = require('../../utils/Validations');
var Helpers = require('../../utils/Helper');
var spring = require('../../config/SpringConfig');

var CountyResultsContainer = React.createClass({
    getInitialState: function() {
        let resultType = (this.props.location.pathname.includes('vienmandaciai')) ?
            'single-mandate' : 'multi-mandate';
        let resultPostUrl =  spring.localHost.concat('/api/results/county/') + resultType;
        let header = (resultType === 'single-mandate') ?
            "Apylinkės kandidatų rezultatai (VIENMANDAČIAI)" : "Partijų sąrašas (DAUGIAMANDAČIAI)";

        return ({
            results: undefined,
            votees: undefined,
            activeCountyId: undefined,
            dictionary: new Map(),
            spoiled: "",
            springErrors: [],
            resultType: resultType,
            resultPostUrl: resultPostUrl,
            header: header
        });
    },
<<<<<<< HEAD
    componentWillMount() {
        this.determineResultType(this.props.location.pathname);
    },
    componentDidMount: function() {
        if (this.props.county) {
            this.getResultsOrVotees(this.props);
        }

        // refactor when login will be implemented

||||||| merged common ancestors
    componentWillMount() {
        this.resultType = this.props.location.pathname.includes('vienmandaciai') ?
                            'single-mandate' : 'multi-mandate';
        this.resultPostUrl =  spring.localHost.concat('/api/results/county/') + this.resultType;
        this.header = this.resultType === 'single-mandate' ?
                        "Apylinkės kandidatų rezultatai (VIENMANDAČIAI)" :
                        "Partijų sąrašas (DAUGIAMANDAČIAI)"
    },
    componentDidMount: function() {

        // refactor when login will be implemented
        
=======
    componentDidMount() {
        const _this = this;
        axios.post(spring.localHost.concat('/api/auth/principal'))
            .then(resp => {
                _this.getResultsOrVotees(resp.data);
            })
            .catch(err => {
                console.log(err);
            });
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
    },
    componentWillReceiveProps(newProps) {
<<<<<<< HEAD
        if (this.props.location.pathname != newProps.location.pathname) {
            this.determineResultType(newProps.location.pathname)
        }
        if (newProps.county) {
            this.getResultsOrVotees(newProps);
        }
||||||| merged common ancestors
        if (newProps.countyId !== null) {
            this.getResultsOrVotees(newProps);
        }
=======
        let resultType = (newProps.location.pathname.includes('vienmandaciai')) ?
            'single-mandate' : 'multi-mandate';
        let resultPostUrl =  spring.localHost.concat('/api/results/county/') + resultType;
        let header = (resultType === 'single-mandate') ?
            "Apylinkės kandidatų rezultatai (VIENMANDAČIAI)" : "Partijų sąrašas (DAUGIAMANDAČIAI)";

        this.setState({
            resultType: resultType,
            resultPostUrl: resultPostUrl,
            header: header
        });
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
    },
    determineResultType(pathname) {
        this.resultType = pathname.includes('vienmandaciai') ?
                          'single-mandate' : 
                          'multi-mandate';
    },
    getResultsOrVotees: function(props) {
<<<<<<< HEAD

        let _this = this
        let resultsUrl = spring.localHost.concat("/api/results/county/") + props.county.id + "/" + this.resultType;
||||||| merged common ancestors
        let _this = this
        let resultsUrl = spring.localHost.concat("/api/results/county/") + props.countyId + "/" + this.resultType;
=======
        let _this = this;
        let resultsUrl = spring.localHost.concat("/api/results/county/") + props.countyId + "/" + this.state.resultType;
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
        axios
            .get(resultsUrl)
            .then(function(response) {
                if (response.data) {
                    _this.setState({ results: response.data })
                } else {
<<<<<<< HEAD
                    let getUrl = _this.resultType === 'single-mandate' ?
                                    spring.localHost.concat('/api/district/') + props.district.id + '/candidates' :
                                    spring.localHost.concat('/api/party/');
||||||| merged common ancestors
                    let getUrl = _this.resultType === 'single-mandate' ?
                                    spring.localHost.concat('/api/district/') + props.districtId + '/candidates' :
                                    spring.localHost.concat('/api/party/');
=======
                    let getUrl = (_this.state.resultType === 'single-mandate') ?
                        spring.localHost.concat('/api/district/') + props.districtId + '/candidates' :
                        spring.localHost.concat('/api/party/');
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
                    _this.getVotees(getUrl);
                }
            })
            .catch(function(err) {
                console.log(err);
            });
    },
    getVotees(url) {
        let _this = this;
        axios
            .get(url)
            .then(function(response) {
<<<<<<< HEAD
                _this.setState({ 
                    results: undefined,
||||||| merged common ancestors
                _this.setState({ 
=======
                _this.setState({
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
                    votees: response.data,
                    dictionary: _this.formDictionary(response.data),
                })
            })
            .catch(function(err) {
                console.log(err)
            })
    },
    prepareVotees() {
        return this.state.resultType === 'single-mandate' ? this.prepareCandidates() : this.prepareParties();
    },
    prepareCandidates() {
        let candidates = this.state.votees.map((votee, idx) => {
            return <CandidateDisplayComponent
                key={idx}
                candidate={votee}
                changeVotes={this.handleChangeVotes}
                votes={this.state.dictionary.get(votee.id)}
            />
        });
        return candidates;
    },
    prepareParties() {
        let parties = this.state.votees.map((votee, idx) => {
            return <MM_PartyComponent
                key={idx}
                party={votee}
                changeVotes={this.handleChangeVotes}
                votes={this.state.dictionary.get(votee.id)}
            />
        });
        return parties
    },
    formDictionary: function(votees) {
        var mapped = new Map();
        votees.forEach(v => mapped.set(v.id, ""));
        return mapped;
    },
    clearForm: function() {
        var newDictionary = new Map();
        var tempDictionary = this.state.dictionary;
        tempDictionary.forEach(function(value, key) {
            newDictionary.set(key, "");
        });
        this.setState({ dictionary: newDictionary,
            springErrors: [],
            spoiled: "" });
    },
    handleChangeSpoiled: function(e) {
        e.preventDefault()
        this.setState({ spoiled: e.target.value })
    },
    handleChangeVotes: function(votee_id, votes) {
        var actualDict = this.state.dictionary;
        actualDict.set(votee_id, votes);
        this.setState({ dictionary: actualDict });
    },
    handleSubmitResults: function() {
        var _this = this;
        var map = this.state.dictionary;
        var voteList = [];
        for (var pair of map) voteList.push({ "unitId": pair[0], "votes": pair[1] });
        var errors = [];
        var body = {
            "spoiledBallots": this.state.spoiled,
            "countyId": this.props.county.id,
            "unitVotes": voteList
<<<<<<< HEAD
        }
        var resultPostUrl =  spring.localHost.concat('/api/results/county/') + this.resultType;
        axios.post(resultPostUrl, body)
||||||| merged common ancestors
        }
        axios.post(this.resultPostUrl, body)
=======
        };
        axios.post(this.resultPostUrl, body)
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
            .then(function(resp) {
                _this.setState({
                    springErrors: [],
                    dictionary: new Map(),
                    spoiled: undefined,
                    results: resp.data
                });
            })
            .catch(function(err) {
                console.log(err);
                errors.push(err.response.data.rootMessage);
                _this.setState({ springErrors: errors.concat(err.response.data.errorsMessages) });
            });
    },
    render: function() {
        var header = this.resultType === 'single-mandate' ?
                    "Apylinkės kandidatų rezultatai (VIENMANDAČIAI)" :
                    "Partijų sąrašas (DAUGIAMANDAČIAI)"

        var formOrResults;

        if (this.state.results) {
<<<<<<< HEAD
            formOrResults = <ResultsDisplayComponent
                                header={header}
                                representative={this.props.representative}
                                results={this.state.results}
                                createdOn={Helpers.dateTimeFormatWithMessage(
                                              this.state.results.createdOn,
                                              "Rezultatai pateikti"
                                          )}
                                confirmedOn={Helpers.dateTimeFormatWithMessage(
                                              this.state.results.confirmedOn,
                                              "Rezultatai patvirtinti"
                                          )}
                            />
||||||| merged common ancestors
            formOrResults = <ResultsDisplayComponent
                                header={this.header}
                                representative={this.props.representative}
                                results={this.state.results}
                                createdOn={Helpers.dateTimeFormatWithMessage(
                                              this.state.results.createdOn,
                                              "Rezultatai pateikti"
                                          )}
                                confirmedOn={Helpers.dateTimeFormatWithMessage(
                                              this.state.results.confirmedOn,
                                              "Rezultatai patvirtinti"
                                          )}
                            />
=======
            formOrResults = (
                <ResultsDisplayComponent
                    header={this.header}
                    results={this.state.results}
                    createdOn={Helpers.dateTimeFormatWithMessage(
                        this.state.results.createdOn,
                        "Rezultatai pateikti"
                    )}
                    confirmedOn={Helpers.dateTimeFormatWithMessage(
                        this.state.results.confirmedOn,
                        "Rezultatai patvirtinti"
                    )}
                />
            );
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
        } else if (this.state.votees) {
<<<<<<< HEAD
            formOrResults = <CountyResultsComponent
                                header={header}
                                representative={this.props.representative}
                                votees={this.prepareVotees()}
                                spoiled={this.state.spoiled}
                                dictionary={this.state.dictionary}
                                changeSpoiled={this.handleChangeSpoiled}
                                submitResults={this.handleSubmitResults}
                                springErrors={this.state.springErrors}
                                activeCountyId={this.props.county.id}
                                clearForm={this.clearForm}
                            />
||||||| merged common ancestors
            formOrResults = <CountyResultsComponent
                                header={this.header}
                                representative={this.props.representative}
                                votees={this.prepareVotees()}
                                spoiled={this.state.spoiled}
                                dictionary={this.state.dictionary}
                                changeSpoiled={this.handleChangeSpoiled}
                                submitResults={this.handleSubmitResults}
                                springErrors={this.state.springErrors}
                                activeCountyId={this.props.countyId}
                                clearForm={this.clearForm}
                            />
=======
            formOrResults = (
                <CountyResultsComponent
                    header={this.header}
                    votees={this.prepareVotees()}
                    spoiled={this.state.spoiled}
                    dictionary={this.state.dictionary}
                    changeSpoiled={this.handleChangeSpoiled}
                    submitResults={this.handleSubmitResults}
                    springErrors={this.state.springErrors}
                    clearForm={this.clearForm}
                />
            );
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
        } else {
            return <div></div>
        }
        return formOrResults;
    }
});

module.exports = CountyResultsContainer;
