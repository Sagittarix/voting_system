var React = require('react');

var RepresentativeProfileComponent = React.createClass({
    propTypes: {
        
    }, 
    render: function() {
        return (
            <div className="row">
                <div className="col-sm-3">
                    <ul className="list-group">
                        <li className="list-group-item text-muted" contentEditable="false">Profile</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Joined</strong></span> 2.13.2014</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Last seen</strong></span> Yesterday</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Real name</strong></span> Joseph Doe</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Role: </strong></span> Pet Sitter</li>
                    </ul>
                    <div className="panel panel-default">
                        <div className="panel-heading">Insured / Bonded?</div>
                        <div className="panel-body"><i style={{color: 'green'}} className="fa fa-check-square"></i> Yes, I am insured and bonded.</div>
                    </div>
                    <div className="panel panel-default">
                        <div className="panel-heading">Website <i className="fa fa-link fa-1x"></i></div>
                        <div className="panel-body"><a href="http://bootply.com" className="">bootply.com</a></div>
                    </div>
                    <ul className="list-group">
                        <li className="list-group-item text-muted">Activity <i className="fa fa-dashboard fa-1x"></i></li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Shares</strong></span> 125</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Likes</strong></span> 13</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Posts</strong></span> 37</li>
                        <li className="list-group-item text-right"><span className="pull-left"><strong className="">Followers</strong></span> 78</li>
                    </ul>
                    <div className="panel panel-default">
                        <div className="panel-heading">Social Media</div>
                        <div className="panel-body">	<i className="fa fa-facebook fa-2x"></i>  <i className="fa fa-github fa-2x"></i> 
                            <i className="fa fa-twitter fa-2x"></i> <i className="fa fa-pinterest fa-2x"></i>  <i className="fa fa-google-plus fa-2x"></i>
                        </div>
                    </div>
                </div>


                <div className="col-sm-9" style={{}} contentEditable="false">
                    <div className="panel panel-default">
                        <div className="panel-heading">Starfox221's Bio</div>
                        <div className="panel-body"> A long description about me.</div>
                    </div>
                    <div className="panel panel-default target">
                        <div className="panel-heading" contentEditable="false">Pets I Own</div>
                            <div className="panel-body">
                                <div className="row row-autoheight">
                                    <div className="col-md-4">
                                        <div className="thumbnail">
                                            <img alt="300x200" src="http://lorempixel.com/600/200/people" />
                                            <div className="caption">
                                                <h3>Rover</h3>
                                                <p>Cocker Spaniel who loves treats.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <div className="thumbnail">
                                            <img alt="300x200" src="http://lorempixel.com/600/200/city" />
                                            <div className="caption">
                                                <h3>Marmaduke</h3>
                                                <p>Is just another friendly dog.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <div className="thumbnail">
                                            <img alt="300x200" src="http://lorempixel.com/600/200/sports" />
                                            <div className="caption">
                                                <h3>Rocky</h3>
                                                <p>Loves catnip and naps. Not fond of children.</p>
                                            </div>
                                        </div>
                                    </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

module.exports = RepresentativeProfileComponent;