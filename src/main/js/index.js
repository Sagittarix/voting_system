
var React = require('react');
var ReactDOM = require('react-dom');
var ReactRouter = require('react-router');
var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

var DistrictContainer = require('./containers/DistrictContainer');
var DistrictListContainer = require('./containers/DistrictListContainer');
var NewDistrictContainer = require('./containers/NewDistrictContainer');


// if (typeof window !== 'undefined') {
//     window.React = React;
// }

var App = function(props) {
    return (
        <div>
            <div>{props.children}</div>
        </div>
    )
};

var NoMatch = function(props) {
    return <div>Route did not match</div>
};

var Tester = function(props) {
    return <DistrictListContainer />
};


var routes = (
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={DistrictListContainer}/>
            <Route path="districts" component={DistrictListContainer}/>
            <Route path="districts/new" component={NewDistrictContainer}/>
            <Route path="districts/:id" component={DistrictContainer}/>
        </Route>
    </Router>
);


ReactDOM.render(
    routes, 
    document.getElementById('app')
);