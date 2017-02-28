var React = require('react');
var axios = require('axios');
var AdminResultsViewComponent = require('../components/AdminResultsViewComponent');
var CountyDisplayContainer = require('./CountyDisplayContainer');

var AdminResultsViewContainer = React.createClass({
	getInitialState: function() {
		return ({ districts: [],
				  counties: [],
				  parties: [],
				  activeDistrictId: undefined,
				  activeCountyId: undefined });
	},
	componentDidMount: function() {
		var _this = this;

		axios
			.all([
				axios.get('http://localhost:8080/api/district'),
				axios.get('http://localhost:8080/api/party')
			])
			.then(axios.spread(function(districts, parties) {
				var counties = [];
				districts.data.forEach(d => {
					d.counties.forEach(c => counties.push(c));
				});
				_this.setState({ districts: districts.data,
								 counties: counties,
								 parties: parties.data });
			}))
			.catch(function(err) {
				console.log(err);
			})

	},
	setActiveDistrict: function(districtId) {
		this.setState({ activeDistrictId: districtId });
	},
	clearActiveDistrict: function() {
		this.setState({ activeDistrictId: undefined });
	},
	setActiveCounty: function(countyId) {
		this.setState({ activeCountyId: countyId });
	},
	clearActiveCounty: function() {
		this.setState({ activeCountyId: undefined });
	},
	prepareCounties() {
		var counties = this.state.counties;
		var preparedCounties = [];

		if (this.state.activeDistrictId != undefined) {
			if (this.state.activeCountyId != undefined) {
				counties.forEach((c, idx) => {
					if (this.state.activeCountyId == c.id) {
						console.log("X")
						preparedCounties.push(
							<CountyDisplayContainer
								key={idx}
								index={idx}
								county={c}
								parties={this.state.parties}
							/>
						);
					}
				});
			} else {
				counties.forEach((c, idx) => {
					console.log("Y")
					if (this.state.activeDistrictId == c.districtId) {
						preparedCounties.push(
							<CountyDisplayContainer
								key={idx}
								index={idx}
								county={c}
								parties={this.state.parties}
							/>
						);
					}
				});
			}
		} else {
			counties.forEach((c, idx) => {
				console.log("Z")
				preparedCounties.push(
					<CountyDisplayContainer
						key={idx}
						index={idx}
						county={c}
						parties={this.state.parties}
					/>
				);
			});
		}
		return preparedCounties;
	},
	districtsSelect: function() {
		var districts = this.state.districts;
		var preparedDistricts = [];

		districts.forEach((d, idx) => {
			preparedDistricts.push(
				<option
					value={d.id}
					key={idx}
					onClick={this.setActiveDistrict.bind(this, d.id)}>
					{d.username}
				</option>
			);
		});

		return (
			<select>
				<option
					value={undefined}
					key={districts.length}
					onClick={this.clearActiveDistrict}>
					Visos apygardos
				</option>
				{preparedDistricts}
			</select>
		);
	},
	countiesSelect: function() {
		var counties = this.state.counties;
		var filteredCounties = [];



		if (this.state.activeDistrictId == undefined) return undefined;
		else {
			counties.forEach((c, idx) => {
				if (c.districtId == this.state.activeDistrictId) {
					filteredCounties.push(
						<option
							value={c.id}
							key={idx}
							onClick={this.setActiveCounty.bind(this, c.id)}>
							{c.username}
						</option>
					);
				}
			});
		}

		return (
			<select>
				<option
					value={undefined}
					key={counties.length}
					onClick={this.clearActiveCounty}>
					Visos apylinkės
				</option>
				{filteredCounties}
			</select>
		);
	},
	render: function() {
		return <AdminResultsViewComponent
				  counties={this.prepareCounties()}
				  districtsSelect ={this.districtsSelect()}
				  countiesSelect={this.countiesSelect()}
			   />
	}
});

module.exports = AdminResultsViewContainer;
