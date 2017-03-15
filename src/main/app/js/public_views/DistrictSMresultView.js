var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;
var axios = require('axios');
var ReactTable = require('react-table').default;
var spring = require('../config/SpringConfig');
var Helper = require('../utils/Helper');
var ChartContainer = require('./chart_components/ChartContainer');
var DataProcessor = require('./chart_components/DataProcessor');

var hide = {
    display: 'none'
};

var DistrictSMresultView = React.createClass({
    getInitialState() {
        return ({ collection: {}, chartData: undefined, chartMetadata: undefined });
    },
    componentWillMount() {
        axios.get(
                spring.localHost
                      .concat('/api/results/district/')
                      .concat(1 + '')                       // blogai imamas id
                      .concat('/single-mandate')
            )
            .then(function(resp) {
                this.setState({ 
                    collection: resp.data,
                    chartData: DataProcessor.cleanSingleMandateVotingDataForChart(resp.data.votes),
                    chartMetadata: { 
                        total: resp.data.totalBallots,
                        valid: resp.data.validBallots
                    }
                });
                console.log(resp.data)
            }.bind(this))
            .catch(err => {
                console.log(err);
            });
    },
    prepareData() {
        if (Object.keys(this.state.collection).length == 0) return [];
        var rows = [];
        let totalPercentageOfTotalBallots = 0.0;

        this.state.collection.votes.forEach(v => {
            const candName = <Link to="">{v.candidate.firstName.concat(' ').concat(v.candidate.lastName)}</Link>;
            const partyName = (v.candidate.party == null) ?
                ('Išsikėlęs pats') : (<Link to="">{v.candidate.party.name}</Link>);
            const percFromValid = v.voteCount / (this.state.collection.validBallots * 1.0) * 100;
            const percFromTotal = v.voteCount / (this.state.collection.totalBallots * 1.0) * 100;
            totalPercentageOfTotalBallots += percFromTotal;

            rows.push(
                {
                    candidate: candName,
                    partyName: partyName,
                    voteCount: v.voteCount,
                    votesFromValid: percFromValid.toFixed(2),
                    votesFromTotal: percFromTotal.toFixed(2)
                }
            );
        });

        // let sortedRows = Helper.sortSMresultDesc(rows);

        return rows;
    },
    prepareCountiesData() {
        if (Object.keys(this.state.collection).length == 0) return [];
        var rows = [];
        let totalVoterCount = 0;
        let grandTotalBallots = 0;
        let percentGrandTotalBallots  = 0.0;
        let totalSpoiledBallots = 0;
        let percentTotalSpoiledBallots = 0.0;
        let totalValidBallots = 0;
        let percentTotalValidBallots = 0.0;

        this.state.collection.countyResults.forEach(r => {
            const county = <Link to={"apylinkes-vienmandaciai-rezultatai/" + r.county.id}>{r.county.name}</Link>;
            const voterCount = r.voterCount;
            const totalBallotsAndPercent = r.totalBallots + " (" + ((r.totalBallots / (r.voterCount * 1.0) * 100).toFixed(2)) + "%)";
            const spoiledBallotsAndPercent = r.spoiledBallots + " (" + ((r.spoiledBallots / (r.totalBallots * 1.0) * 100).toFixed(2)) + "%)";
            const validBallotsAndPercent = r.validBallots + " (" + ((r.validBallots / (r.totalBallots * 1.0) * 100).toFixed(2)) + "%)";

            totalVoterCount += voterCount;
            grandTotalBallots += r.totalBallots;
            percentGrandTotalBallots = parseFloat((grandTotalBallots / (totalVoterCount * 1.0) * 100).toFixed(2));
            totalSpoiledBallots += r.spoiledBallots;
            percentTotalSpoiledBallots = parseFloat((totalSpoiledBallots / (grandTotalBallots * 1.0) * 100).toFixed(2));
            totalValidBallots += r.validBallots;
            percentTotalValidBallots = parseFloat((totalValidBallots / (grandTotalBallots * 1.0) * 100).toFixed(2));

            rows.push(
                {
                    county: county,
                    voterCount: voterCount,
                    totalBallotsAndPercent: totalBallotsAndPercent,
                    spoiledBallotsAndPercent: spoiledBallotsAndPercent,
                    validBallotsAndPercent: validBallotsAndPercent
                }
            );
        });

        let sortedRows = Helper.sortSMresultDesc(rows);

        sortedRows.push(
            {
                county: <strong style={{ float: 'right', marginRight: 10 }}>Iš viso:</strong>,
                voterCount: <strong>{totalVoterCount}</strong>,
                totalBallotsAndPercent: <strong>{grandTotalBallots + " / " + percentGrandTotalBallots + "%"}</strong>,
                spoiledBallotsAndPercent: <strong>{totalSpoiledBallots + " / " + percentTotalSpoiledBallots + "%"}</strong>,
                validBallotsAndPercent: <strong>{totalValidBallots + " / " + percentTotalValidBallots + "%"}</strong>
            }
        );

        return rows;
    },
    getPercentage(value, divisor) {
        return (value * 1.0 / divisor * 1.0) * 100
    },
    getColumns() {
        
        let data = this.state.collection
        let summary = {
            candidate: '',
            partyName: 'Iš viso:',
            voteCount: data.validBallots,
            votesFromValid: 100.00,
            votesFromTotal: this.getPercentage(data.validBallots, data.totalBallots)
        }

        return (
            [
                {
                    header: 'Kandidatas',
                    accessor: 'candidate',
                    headerStyle: { fontWeight: 'bold' },
                    style: { marginLeft: 5 },
                    id: 1
                },
                {
                    header: 'Iškėlė',
                    accessor: 'partyName',
                    headerStyle: { fontWeight: 'bold' },
                    style: { marginLeft: 5 },
                    footer: summary.partyName,
                    footerStyle: { fontWeight: 'bold', float: 'right'},  // KAZKODEL NEKLAUSO
                    id: 2
                },
                {
                    header: 'Balsai',
                    accessor: 'voteCount',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    footer: summary.voteCount,

                    id: 3
                },
                {
                    header: '% nuo galiojančių biuletenių',
                    accessor: 'votesFromValid',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    footer: summary.votesFromValid,
                    id: 4
                },
                {
                    header: '% nuo dalyvavusių rinkėjų',
                    accessor: 'votesFromTotal',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    footer: summary.votesFromTotal,
                    id: 5
                }
            ]
        );
    },
    getCountyColumns() {

        let data = this.state.collection
        let summary = {
            county: 'Iš viso',
            voterCount: data.voterCount,
            totalBallotsAndPercent: data.totalBallots,
            spoiledBallotsAndPercent: data.spoiledBallots,
            validBallotsAndPercent: data.validBallots,
        }

        return (
            [
                {
                    header: 'Apylinkė',
                    accessor: 'county',
                    headerStyle: { fontWeight: 'bold' },
                    style: { marginLeft: 5 },
                    id: 1
                },
                {
                    header: 'Rinkėjų skaičius',
                    accessor: 'voterCount',
                    headerStyle: { fontWeight: 'bold' },
                    style: { marginLeft: 5 },
                    id: 2
                },
                {
                    header: 'Dalyvavo',
                    accessor: 'totalBallotsAndPercent',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    id: 3
                },
                {
                    header: 'Negaliojantys biuleteniai',
                    accessor: 'spoiledBallotsAndPercent',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    id: 4
                },
                {
                    header: 'Galiojantys biuleteniai',
                    accessor: 'validBallotsAndPercent',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    id: 5
                }
            ]
        );
    },
    getDistrictName() {
        return (Object.keys(this.state.collection).length == 0) ?
            '' : this.state.collection.votes[0].candidate.district.name;
    },
    getPercentOfTotal() {
        return (this.state.collection.totalBallots * 1.0 / this.state.collection.voterCount * 100).toFixed(2);
    },
    getPercentOfSpoiled() {
        return (this.state.collection.spoiledBallots * 1.0 / this.state.collection.voterCount * 100).toFixed(2);
    },
    getOptions() {
        const array = [5, 10, 20];
        if (Object.keys(this.state.collection).length > 0) {
            const max = this.state.collection.votes.length + 1;
            array.push(max);
            return Array.from(new Set(array.filter(i => { return i <= max })));
        } else {
            return array;
        }
    },
    getCountyOptions() {
        const array = [5, 10, 20];
        if (Object.keys(this.state.collection).length > 0) {
            const max = this.state.collection.countyResults.length + 1;
            array.push(max);
            return Array.from(new Set(array.filter(i => { return i <= max })));
        } else {
            return array;
        }
    },
    render() {
        return (
            <div>
                <h4 className="h4-election">2017 m. kovo 16 d. Lietuvos Respublikos Seimo rinkimai</h4>
                <h3>{this.getDistrictName()} apygarda</h3>
                <div className="row narrowed" style={{ margin: '30px 0px 30px 0px' }}>
                    <div className="col-md-4"></div>
                    <div className="col-md-5">
                        <p className="small-p">Apylinkių skaičius - <strong>{this.state.collection.totalCounties}</strong></p>
                        <p className="small-p">Pagal gautus iš apylinkių duomenis:</p>
                        <p className="small-p indented">
                            rinkėjų sąraše įrašyta rinkėjų – <strong>{this.state.collection.voterCount}</strong>,
                            rinkimuose dalyvavo – <strong>{this.state.collection.totalBallots}&nbsp;
                            ({(this.state.collection.totalBallots / (this.state.collection.voterCount * 1.0) * 100).toFixed(2)} %)</strong>
                        </p>
                        <p className="small-p indented">
                            negaliojančių biuletenių – <strong>{this.state.collection.spoiledBallots}&nbsp;
                            ({(this.state.collection.spoiledBallots / (this.state.collection.totalBallots * 1.0) * 100).toFixed(2)} %)</strong>,
                            galiojančių biuletenių – <strong>{this.state.collection.validBallots}&nbsp;
                            ({(this.state.collection.validBallots / (this.state.collection.totalBallots * 1.0) * 100).toFixed(2)} %)</strong>
                        </p>
                    </div>
                    <div className="col-md-12">
                        <p className="centered-p">
                            Sąraše įrašytų ir rinkimuose dalyvavusių rinkėjų skaičiai skelbiami pagal duomenis
                            perdavusių rinkimų apylinkių protokolus.
                        </p>
                    </div>
                    <div className="col-md-12">
                        <h3>Balsavimo rezultatai apygardoje</h3>
                    </div>
                </div>
                {this.state.chartData 
                    && 
                    <ChartContainer 
                        data={this.state.chartData} 
                        metadata={this.state.chartMetadata}
                        showTooltip={true}
                        showPercent={true}
                    />
                }
                <ReactTable
                    data={this.prepareData()}
                    columns={this.getColumns()}
                    defaultPageSize={6}
                    pageSizeOptions={this.getOptions()}
                    showPageJump={false}
                    previousText='Ankstesnis'
                    nextText='Kitas'
                    loadingText='Kraunama...'
                    noDataText='Duomenų nėra'
                    pageText='Puslapis'
                    ofText='iš '
                    rowsText='eilučių'
                />
                <div className="row narrowed" style={{ margin: '30px 0px 30px 0px' }}>
                    <div className="col-md-12">
                        <h3>Balsavimo rezultatai apylinkėse</h3>
                    </div>
                </div>
                <ReactTable
                    data={this.prepareCountiesData()}
                    columns={this.getCountyColumns()}
                    defaultPageSize={5}
                    pageSizeOptions={this.getCountyOptions()}
                    showPageJump={false}
                    previousText='Ankstesnis'
                    nextText='Kitas'
                    loadingText='Kraunama...'
                    noDataText='Duomenų nėra'
                    pageText='Puslapis'
                    ofText='iš '
                    rowsText='eilučių'
                />
            </div>
        );
    }
});

module.exports = DistrictSMresultView;
