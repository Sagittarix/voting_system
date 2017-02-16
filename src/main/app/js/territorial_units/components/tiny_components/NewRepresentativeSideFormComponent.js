/**
 * Created by osvaldas on 17.2.7.
 */
var React = require('react');

var onlyRequiredCounties = [];
var countyErrors = '';

var NewRepresentativeSideFormComponent = React.createClass({

    getInitialState: function () {
        return {
            countiesOfDistrict: [],
            countyErrors: '',
            name: '',
            surname: '',
            email: '',
            district: 'Pasirinkite apygardą',
            county: 'Pasirinkite apylinkę',

            nameCharacters: '',
            maxNameLength: 15,
            nameLength: '',
            surnameCharacters: '',
            maxSurnameLength: 20,
            surnameLength: '',
            emailCharacters: '',
        }
    },

    componentWillMount: function () {
        onlyRequiredCounties = [];
    },
    handleNameChange: function (event) {
        this.setState({name: event.target.value});

        var input = event.target.value.trim();
        var checkMaxLength = input.length <= this.state.maxNameLength;
        if (!checkMaxLength){
            this.setState({nameLength: "Vardas per ilgas: įveskite ne daugiau " + this.state.maxNameLength + " simbolių. Dabar yra: " + event.target.value.length});
        } else {
            this.setState({nameLength: ''});
        }

        var isFirstCharacterGood = /^[AaĄąBbCcČčDdEeĘęĖėFfGgHhIiĮįYyJjKkLlMmNnOoPpRrSsŠšTtUuŲųŪūVvZzŽž]*$/.test(input.charAt(0));
        var areOtherCharactersGood = /^([AaĄąBbCcČčDdEeĘęĖėFfGgHhIiĮįYyJjKkLlMmNnOoPpRrSsŠšTtUuŲųŪūVvZzŽž\s\-]*)$/.test(input);

        if(!isFirstCharacterGood || !areOtherCharactersGood){
            this.setState({nameCharacters: "Vardas turi netinkamus simbolius: įveskite tik lietuviškas raides"});
        } else {
            this.setState({nameCharacters: ''});
        }
    },
    handleSurnameChange: function (event) {
        this.setState({surname: event.target.value});

        var input = event.target.value.trim();
        var checkMaxLength = input.length <= this.state.maxSurnameLength;
        if (!checkMaxLength){
            this.setState({surnameLength: "Pavardė per ilga: įveskite ne daugiau " + this.state.maxSurnameLength + " simbolių. Dabar yra: " + event.target.value.length});
        } else {
            this.setState({surnameLength: ''});
        }

        var isFirstCharacterGood = /^[AaĄąBbCcČčDdEeĘęĖėFfGgHhIiĮįYyJjKkLlMmNnOoPpRrSsŠšTtUuŲųŪūVvZzŽž]*$/.test(input.charAt(0));
        var areOtherCharactersGood = /^([AaĄąBbCcČčDdEeĘęĖėFfGgHhIiĮįYyJjKkLlMmNnOoPpRrSsŠšTtUuŲųŪūVvZzŽž\s\-]*)$/.test(input);

        if(!isFirstCharacterGood || !areOtherCharactersGood){
            this.setState({surnameCharacters: "Pavardė turi netinkamus simbolius: įveskite tik lietuviškas raides"});
        } else {
            this.setState({surnameCharacters: ''});
        }
    },
    hendleEmailChange: function (event) {
        this.setState({email: event.target.value})

        var checkIfOnlyCharacters = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(event.target.value);
        if(!checkIfOnlyCharacters){
            this.setState({emailCharacters: "El. pašto adreso pavyzdys: vartotojas@e-mail.lt"});
        } else {
            this.setState({emailCharacters: ''});
        }
    },
    handleDistrictChange: function (event) {
        this.setState({district: event.target.value})
    },
    handleCountyChange: function (event) {
        this.setState({county: event.target.value});
    },
    callHelperFunction: function (event) {
        this.changePossibleCounties(event);
        this.handleDistrictChange(event);
    },
    changeState: function () {
        this.setState({countiesOfDistrict: this.props.onlyFirstRequiredCounties});
    },
    changePossibleCounties: function (event) {
        onlyRequiredCounties = [];
        console.log("click");
        var self = this;
        var matchFound = false;
        var districtToLookFor = event.target.value;
        console.log(districtToLookFor);
        this.props.OnlyDistricts.map(function(district, index){

            if(district.name == districtToLookFor){
                console.log("match");
                matchFound = true;
                onlyRequiredCounties = [];
                district.counties.map(function (county, index) {
                    onlyRequiredCounties.push(county);
                });
                self.setState({countiesOfDistrict: onlyRequiredCounties})
            } else {
                console.log("no match");
            }
            if(matchFound == false){
                onlyRequiredCounties = [];
                self.setState({countiesOfDistrict: onlyRequiredCounties})
            }
        });
    },

    onSubmit: function () {
        this.props.newRep(this.state.name, this.state.surname, this.state.email, this.state.district, this.state.county);
        this.setState({name: ''});
        this.setState({surname: ''});
        this.setState({email: ''});
        this.setState({district: 'Pasirinkite apygardą'});
        this.setState({county: 'Pasirinkite apylinkę'});
        this.changePossibleCounties();
    },

    render: function () {

        {this.changePossibleCounties};

        var DistrictNames = [];
        DistrictNames = this.props.OnlyDistricts;

        console.log(this.props.OnlyDistricts);

        MakeDistrictItem = function(X) {
            return <option>{X.name}</option>;
        };
        MakeCountyItem = function(X) {
            return <option>{X.name}</option>;
        };

        return (
            <form>
                <div className="form-group">
                    <label htmlFor="inputCounty">Atstovo vardas</label>
                    <input type="text" className="form-control" value={this.state.name} onChange={this.handleNameChange}/>
                    <div style={{color: 'red'}}>{this.state.nameCharacters}</div>
                    <div style={{color: 'red'}}>{this.state.nameLength}</div>
                    <label htmlFor="inputCounty">Atstovo pavardė</label>
                    <input type="text" className="form-control" value={this.state.surname} onChange={this.handleSurnameChange}/>
                    <div style={{color: 'red'}}>{this.state.surnameCharacters}</div>
                    <div style={{color: 'red'}}>{this.state.surnameLength}</div>
                    <label htmlFor="inputCounty">Atstovo el. paštas</label>
                    <input type="text" className="form-control"  value={this.state.email} onChange={this.hendleEmailChange}/>
                    <div style={{fontStyle: 'italic'}}>{this.state.emailCharacters}</div>
                    <label htmlFor="inputCounty" >Atstovo apygarda</label>
                    <select className="form-control" value={this.state.district} onChange={this.callHelperFunction}>
                        <option>Pasirinkite apygardą</option>;
                        {DistrictNames.map(MakeDistrictItem)}
                    </select>
                    <label htmlFor="inputCounty">Atstovo apylinkė</label>
                    <select className="form-control" value={this.state.county} onChange={this.handleCountyChange}>
                        <option>Pasirinkite apylinkę</option>;
                        {onlyRequiredCounties.map(MakeCountyItem)}
                    </select>
                </div>
                <div>
                    <button type="submit" disabled={
                        this.state.nameCharacters ||
                        this.state.surnameCharacters ||
                        this.state.nameLength ||
                        this.state.surnameLength ||
                        this.state.emailCharacters ||
                        this.state.district == 'Pasirinkite apygardą' ||
                        this.state.county == 'Pasirinkite apylinkę'
                    } className="btn btn-primary btn-md" onClick={this.onSubmit} style={{ marginTop: 10 }} >Sukurti</button>
                </div>

            </form>
        )
    }

});

module.exports = NewRepresentativeSideFormComponent;
