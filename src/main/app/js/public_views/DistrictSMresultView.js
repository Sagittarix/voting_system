var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;
var axios = require('axios');
var ReactTable = require('react-table').default;
var spring = require('../config/SpringConfig');
var Helper = require('../utils/Helper');

var hide = {
    display: 'none'
};

var DistrictSMresultView = React.createClass({
    getInitialState() {
        return ({ collection: {} });
    },
    componentWillMount() {
        axios.get(
                spring.localHost
                      .concat('/api/results/district/')
                      .concat(1 + '')                       // blogai imamas id
                      .concat('/single-mandate')
            )
            .then(function(resp) {
                this.setState({ collection: resp.data });
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

        let sortedRows = Helper.sortSMresultDesc(rows);

        sortedRows.push(
            {
                candidate: '',
                partyName: <strong style={{ float: 'right', marginRight: 10 }}>Iš viso:</strong>,
                voteCount: <strong>{this.state.collection.validBallots}</strong>,
                votesFromValid: <strong>{100.00}</strong>,
                votesFromTotal: <strong>{totalPercentageOfTotalBallots.toFixed(2)}</strong>
            }
        );

        return rows;
    },
    prepareCountiesData() {
        //TODO code needed
    },
    getColumns() {
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
                    id: 2
                },
                {
                    header: 'Balsai',
                    accessor: 'voteCount',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    id: 3
                },
                {
                    header: '% nuo galiojančių biuletenių',
                    accessor: 'votesFromValid',
                    headerStyle: { fontWeight: 'bold' },
                    style: { textAlign: 'center' },
                    id: 4
                },
                {
                    header: '% nuo dalyvavusių rinkėjų',
                    accessor: 'votesFromTotal',
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
        const array = [5, 10];
        let max;
        if (Object.keys(this.state.collection).length > 0) {
            max = this.state.collection.votes.length + 1;
        } else {
            return array;
        }
        array.push(max);

        return Array.from(new Set(array)).sort((a, b) => {
            // TODO perdaryti ĄČĘ rūšiavimą
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
            return 0;
        });
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
                <ReactTable
                    data={this.prepareData()}
                    columns={this.getColumns()}
                    defaultPageSize={5}
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
                    columns={this.getColumns()}
                    defaultPageSize={5}
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
            </div>
        );
    }
});

module.exports = DistrictSMresultView;