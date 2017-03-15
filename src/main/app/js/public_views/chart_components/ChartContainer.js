var React = require('react');
var ReactDOM = require('react-dom');
var d3 = require('d3');
var SortableBarChart = require('./SortableBarChart');

var ChartContainer = React.createClass({
    propTypes: {
        data: React.PropTypes.arrayOf(React.PropTypes.object).isRequired,
        metadata: React.PropTypes.object.isRequired,
        showPercent: React.PropTypes.bool,
        showTooltip: React.PropTypes.bool
    },
    getDefaultProps() {
        return {
            showPercent: false,
            showTooltip: false,
            metadata: { total: 0 }
        }
    },
    getInitialState: function () {
        return {
            w: 0,
            data: []
        }
    },
    componentWillMount() {
        this.setState({ 
            data: this.sortData(this.props.data, 'abc')
        });
    },
    componentDidMount() {
        window.addEventListener('resize', this.fitToParentSize)
        this.fitToParentSize();
    },
    componentWillUnmount() {
        window.removeEventListener('resize', this.fitToParentSize)
    },
    componentWillReceiveProps() {
        this.fitToParentSize();
    },
    fitToParentSize() {
        let elem = ReactDOM.findDOMNode(this);
        let w = elem.parentNode.offsetWidth;
        let currentW = this.state.w;

        if (w !== currentW) {
            this.setState({ w: w });
        }
    },
    sortData(data, orderBy) {
        if (orderBy === 'abc') {
            return data.sort(function(x, y) {
                return d3.ascending(x.key, y.key);
            })
        } else {
            return data.sort(function(x, y) {
                return d3.descending(x.value, y.value);
            })
        }
    },
    handleSortingOptionChange(e) {
        if (this.state.orderBy !== e.target.value) {
            this.setState({
                orderBy: e.target.value,
                data: this.sortData(this.state.data, e.target.value)
            });
        }
    },
    render() {

        let data = this.state.data
        let margin = {left: 170, right: 60, top: 30, bottom: 130}
        let width = this.state.w
        let height = data.length * 40 + margin.top + margin.bottom        
        
        let { metadata, showPercent, showTooltip } = this.props

        let params = {
            orderBy: 'abc',
            data: data,
            metadata: metadata,
            showPercent: showPercent,
            showTooltip: showTooltip,
            width: width,
            height: height,
            margin: margin,
            padding: 0.1
        }

        return (
                <div className='row narrowed'>
                    <div className='col-sm-12'>
                        <SortableBarChart {...params}/>
                    </div>
                </div>
        );
    }

});

module.exports = ChartContainer;