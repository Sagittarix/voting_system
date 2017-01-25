var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var CountyRow = require('../components/CountyRow');


var CountyListContainer = React.createClass({
    propTypes: {
        
    }, 
    render: function() {
        var countyList = [];
        this.props.counties.forEach(function(county, idx) {
            countyList.push(<CountyRow county={county} key={idx}/>)
        })
        return (
            <div>
                {countyList.length > 0
                    &&
                    <div>
                        <table className="table table-condensed table-hover" style={{width: "30%"}}>
                            <thead>
                                <tr className="">
                                    <td>Apskritis</td>
                                    <td>Balsuotojų kiekis</td>
                                </tr>
                            </thead>
                            <tbody>
                                {countyList}
                            </tbody>
                        </table>
                    </div>
                }
                {countyList.length == 0
                    && 
                    <div>
                        <p style={{"color": "red"}}>Apylinkių sąrašas tuščias</p>
                    </div>
                }
            </div>
        );
    }
});

module.exports = CountyListContainer;