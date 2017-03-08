var React = require('react');
var RepresentativePanelComponent = require('../components/RepresentativePanelComponent');
var axios = require('axios');
var spring = require('../config/SpringConfig');

var RepresentativeHomeContainer = React.createClass({
    getInitialState() {
        return {
<<<<<<< HEAD
            representative: undefined
||||||| merged common ancestors
            representative: undefined,
            countyId: undefined,
            districtId: undefined
=======
            representative: false
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
        };
    },
    contextTypes: {
        router: React.PropTypes.object.isRequired
    },
    /*componentWillReceiveProps(newProps) {
        if (newProps.currentUser != this.state.currentUser) this.setState({ currentUser: newProps.currentUser });
    },*/
    componentDidMount() {
<<<<<<< HEAD
        const url = spring.localHost.concat("/api/county-rep/") + this.props.params.id;

        axios.get(url)
            .then(function(response) {
                this.setState({ 
                    representative: response.data,
                    county: response.data.county,
                    district: response.data.district
                 });
            }.bind(this))
            .catch(function(err) {
||||||| merged common ancestors
        const url = spring.localHost.concat("/api/county-rep/") + this.props.params.id;

        axios.get(url)
            .then(function(response) {
                this.setState({ 
                    representative: response.data,
                    countyId: response.data.countyId,
                    districtId: response.data.districtId
                 });
            }.bind(this))
            .catch(function(err) {
=======
        const _this = this;
        let fd = new FormData();
        fd.append("role", "ROLE_REPRESENTATIVE");
        axios.post(spring.localHost.concat('/api/auth/role'), fd)
            .then(resp => {
                if (resp.data == false) {
                    _this.context.router.push('/prisijungti')
                } else {
                    _this.setState({ representative: true });
                }
            })
            .catch(err => {
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
                console.log(err);
            });
    },
    render: function() {
<<<<<<< HEAD

        const {representative, county, district } = this.state
        
        let childrenWithProps = React.Children.map(this.props.children, (child) => 
            React.cloneElement(child, {
                representative: representative,
                county: county,
                district: district
            })
        );
        
        if (!representative) {
            return <div></div>
        }
||||||| merged common ancestors
        let childrenWithProps = React.Children.map(this.props.children, (child) => 
            React.cloneElement(child, {
                representative: this.state.representative,
                countyId: this.state.countyId,
                districtId: this.state.districtId
            })
        );
=======
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe

<<<<<<< HEAD
        return (
            <div>
                <RepresentativePanelComponent repId={representative.id}/>
                <div className="main-layout">
                    {childrenWithProps}
||||||| merged common ancestors
        return (
            <div>
                <RepresentativePanelComponent />
                <div className="main-layout">
                    {childrenWithProps}
=======
        /*let childrenWithProps = React.Children.map(this.props.children, (child) =>
            React.cloneElement(child, {currentUser: this.state.currentUser})
        );*/

        let displayer;
        if (this.state.representative) {
            displayer = (
                <div>
                    <RepresentativePanelComponent location={this.props.location} />
                    <div className="main-layout">
                        {this.props.children}
                    </div>
>>>>>>> 3d63d0cf9afd35327b1916ce5c41949a50e219fe
                </div>
            );
        } else {
            displayer = <div></div>
        }

        return displayer;
    }
});

module.exports = RepresentativeHomeContainer;