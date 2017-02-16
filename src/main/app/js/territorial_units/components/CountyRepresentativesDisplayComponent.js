/**
 * Created by osvaldas on 17.2.7.
 */
var React = require('react');
var NewRepresentativeSideFormContainer = require('../containers/tiny_containers/NewRepresentativeSideFormContainer');
var CountyRepresentativeListLineComponent = require('./tiny_components/CountyRepresentativeListLineComponent');

var CountyRepresentativesDisplayComponent = React.createClass ({
    render: function () {

      console.log(this.props)
      console.log("TYPE")
      console.log(typeof this.props.repData)
        var CountyRepresentativesArray = [];

        that=this;

        this.props.repData.forEach(function (rep, index) {
            CountyRepresentativesArray.push(
                <CountyRepresentativeListLineComponent
                    repData={rep}
                    id={index}
                    key={index}
                    onDeleteRepresentative={that.props.onDeleteRepresentative}
                />
            )
        });

        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-8 units-list-area">

                        <div className="list-group-item active">
                            <div style={{textAlign:"center"}}><b>RINKIMŲ APYLINKIŲ ATSTOVAI</b></div>
                        </div>
                        <div className="list-group-item active">
                            <div>
                                <div style={{height: "20px"}}>
                                    <div className="col-md-4">Atstovas</div>

                                    <div className="col-md-4">Apylinkė</div>
                                    <div className="col-md-3">ID (būsimas e-mail)</div>
                                    <div className="col-md-1">action</div>
                                </div>
                            </div>
                        </div>
                        {CountyRepresentativesArray}
                    </div>
                    <div className="col-md-4 units-create-area">
                        <div className="col-md-11">
                            <NewRepresentativeSideFormContainer
                                newRep={this.props.newRep}
                                districtsData={this.props.districtsData}
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

module.exports = CountyRepresentativesDisplayComponent;
