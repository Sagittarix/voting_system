var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var HomePageComponent = React.createClass({
    render: function () {
        return (
            <div id="homePageButtonsRow">

                <div className="firstDivAfterHeader"></div>

                <div className="homePageButtonsRow">
                    <div className="col-sm-3">
                        <Link to="paieska" className="NoUnderlineLink" href="#" title="title">
                            <button className="frontPageButton" id="buttonElectionResults"><span className="glyphicon glyphicon-search"></span><div className="innerButtonDiv">{this.props.search}</div></button>
                        </Link>
                    </div>
                    <div className="col-sm-3">
                        <Link to="" className="NoUnderlineLink" href="#" title="title">
                            <button className="frontPageButton" id="buttonCountyRepresentatives"><span className="glyphicon glyphicon-knight"></span><div className="innerButtonDiv">{this.props.countiesResults}</div></button>
                        </Link>
                    </div>
                    <div className="col-sm-3">
                        <Link to="" className="NoUnderlineLink" href="#" title="title">
                            <button className="frontPageButton" id="buttonAdministration"><span className="glyphicon glyphicon-bishop"></span><div className="innerButtonDiv">{this.props.districtsResults}</div></button>
                        </Link>
                    </div>
                    <div className="col-sm-3">
                        <Link to="" className="NoUnderlineLink" href="#" title="title">
                            <button className="frontPageButton" id="buttonAdministration"><span className="glyphicon glyphicon-king"></span><div className="innerButtonDiv">{this.props.finalResults}</div></button>
                        </Link>
                    </div>
                </div>
            </div>
        )
    }
});

module.exports = HomePageComponent;
