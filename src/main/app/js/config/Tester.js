var React = require('react');
var axios = require('axios');
var fileDownload = require('react-file-download');
var ReactTable = require('react-table').default;
var spring = require('./SpringConfig');

var Tester = React.createClass({
    getInitialState() {
        return ({ collection: [] });
    },
    componentDidMount() {
        axios.get(
                spring.localHost
                .concat('/api/results/county/')
                .concat(this.props.currentUser.county.id + '')
                .concat('/single-mandate')
            )
            .then(function(resp) {
               this.setState({ collection: resp.data });
            }.bind(this))
            .catch(err => {
                console.log(err);
            });
    },
    downloadCSV() {
        axios.get('http://localhost:8080/api/download/testers')
             .then(resp => {
                fileDownload(resp.data, "testData.csv");
                console.log(resp);
             })
             .catch(err => {
                console.log(err);
             });
    },
    render: function() {

        /*const data = [
            {
                name: 'Tanner Linsley',
                age: 26,
                friend: {
                    name: 'Jason Maurer',
                    age: 23,
                }
            },
            {
                name: 'Tanner Linsley',
                age: 26,
                friend: {
                    name: 'Jason Maurer',
                    age: 23,
                }
            },
            {
                name: 'Tanner Linsley',
                age: 26,
                friend: {
                    name: 'Jason Maurer',
                    age: 23,
                }
            }
        ];*/

        /*const columns = [{
            header: 'Name',
            accessor: 'name',
            id: 1
        }, {
            header: 'Age',
            accessor: 'age',
            id: 2
            //render: props => <span className='number'>{props.value}</span> // Custom cell components!
        }, {
            header: 'Friend Name',
            accessor: d => d.friend.name, // Custom value accessors!
            id: 3
        }, {
            header: 'Age', //props => <span>Friend Age</span>, // Custom header components!
            accessor: 'friend.age',
            id: 4
        }];*/

        const data = [this.state.collection];

        //console.log(this.state.collection)
        console.log(data);

        const cols = [{
            header: 'Apylinkė',
            accessor: d => d.county.name,
            id: 1
        }, {
            header: 'Balsuotojų',
            accessor: 'totalBallots',
            id: 2
            //render: props => <span className='number'>{props.value}</span> // Custom cell components!
        }, {
            header: 'Sugadinti biuleteniai',
            accessor: 'spoiledBallots', // Custom value accessors!
            id: 3
        }];

        return (
            <div>
                <ReactTable
                    data={data}
                    columns={cols}
                />
            </div>
        );
    }
});

module.exports = Tester;