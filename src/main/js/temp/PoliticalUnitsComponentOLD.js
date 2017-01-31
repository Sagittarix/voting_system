var React = require('react');
var ReactRouter = require('react-router');
var Link = ReactRouter.Link;

var PoliticalUnitsComponent = React.createClass({
    render: function() {
		return (
			<div className="container">
                <div className="row">
                    <div className="col-md-8 territorial-list-area">
                        <div className="list-group">
                            <div className="list-group-item active">
                                <div className="party-link">
                                    <Link to="#">Partijų sąrašas</Link>
                                </div>
                            </div>
                            <div className="list-group-item" style={{ height: 'auto' }}>
                                <div>
                                    <table id="party_table">
                                        <tr>
                                            <td>Partija 1</td>
                                            <td>Partija 2</td>
                                            <td>Partija 3</td>
                                        </tr>
                                        <tr>
                                            <td>Partija 4</td>
                                            <td>Partija 5</td>
                                            <td>Partija 6</td>
                                        </tr>
                                        <tr>
                                            <td>Partija 7</td>
                                            <td>Partija 8</td>
                                            <td>Partija 9</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div className="list-group-item">
                                <div>
                                    PARTY MEMBERS LIST
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 territorial-create-area">
                        <NewPartyAsideForm create={this.props.create} />
                    </div>

                </div>
			</div>
        );
    }
});

window.PoliticalUnitsComponent = PoliticalUnitsComponent;
