var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var AdminPanelComponent = React.createClass({
	getInitialState: function () {
		return {
			teritoriniaiVienetai: {backgroundColor: '#CDEBF7', color:"#006B96",},
			apygarduKandidatai: {backgroundColor: '#CDEBF7', color:"#006B96"},
			apylinkiuAtstovai: {backgroundColor: '#CDEBF7', color:"#006B96"},
			politiniaiVienetai: {backgroundColor: '#CDEBF7', color:"#006B96"},
			apylinkiuRezultatai: {backgroundColor: '#CDEBF7', color:"#006B96"}
		}
    },

	resetButtonBackgrounds: function () {
        this.setState({teritoriniaiVienetai: {backgroundColor: '#CDEBF7', color:"#006B96"}});
        this.setState({apygarduKandidatai: {backgroundColor: '#CDEBF7', color:"#006B96"}});
        this.setState({apylinkiuAtstovai: {backgroundColor: '#CDEBF7', color:"#006B96"}});
        this.setState({politiniaiVienetai: {backgroundColor: '#CDEBF7', color:"#006B96"}});
        this.setState({apylinkiuRezultatai: {backgroundColor: '#CDEBF7', color:"#006B96"}});
    },

    componentWillReceiveProps: function (nextProps) {

        if(nextProps.location.pathname === '/administravimas'){
			this.resetButtonBackgrounds();
        }
		if(nextProps.location.pathname === '/administravimas/teritorinis-suskirstymas'){
            this.resetButtonBackgrounds();
        	this.setState({teritoriniaiVienetai: {backgroundColor: '#006B96', color:"white"}});
        }
        if(nextProps.location.pathname === '/administravimas/apygardu-kandidatai'){
            this.resetButtonBackgrounds();
            this.setState({apygarduKandidatai: {backgroundColor: '#006B96', color:"white"}});
		}
		if(nextProps.location.pathname === '/administravimas/apylinkiu-atstovai') {
            this.resetButtonBackgrounds();
            this.setState({apylinkiuAtstovai: {backgroundColor: '#006B96', color:"white"}});
		}
        if(nextProps.location.pathname === '/administravimas/politinis-suskirstymas') {
            this.resetButtonBackgrounds();
            this.setState({politiniaiVienetai: {backgroundColor: '#006B96', color:"white"}});
        }
        if(nextProps.location.pathname === '/administravimas/apylinkiu-rezultatai') {
            this.resetButtonBackgrounds();
            this.setState({apylinkiuRezultatai: {backgroundColor: '#CDEBF7', color:"white"}});
        }
    },

    render: function() {
        return (
			<div>
				<div className="menu">
					<ul className="nav nav-tabs" id="bootstrap-overrides-nav-tabs">
						<li className="tab"><Link to="administravimas/teritorinis-suskirstymas" className="adminPanelButton" id="teritoriniaiVienetai" style={this.state.teritoriniaiVienetai} >Teritoriniai vienetai</Link></li>
						<li className="tab"><Link to="administravimas/apygardu-kandidatai" className="adminPanelButton" id="apygarduKandidatai" style={this.state.apygarduKandidatai} >Apygardų kandidatai</Link></li>
						<li className="tab"><Link to="administravimas/apylinkiu-atstovai" className="adminPanelButton" id="apylinkiuAtstovai" style={this.state.apylinkiuAtstovai} >Apylinkių atstovai</Link></li>
						<li className="tab"><Link to="administravimas/politinis-suskirstymas" className="adminPanelButton" id="politiniaiVienetai" style={this.state.politiniaiVienetai} >Politiniai vienetai</Link></li>
						<li className="tab"><Link to="administravimas/apylinkiu-rezultatai" className="adminPanelButton" id="ApylinkiuRezultatai" style={this.state.apylinkiuRezultatai} >Apylinkių rezultatai</Link></li>
					</ul>
				</div>
				<div className="main-layout">
                    {this.props.children}
				</div>
			</div>
        );
    }
});

module.exports = AdminPanelComponent;
