var React = require('react');
const InfiniteScroll = require('react-infinite-scroller');

function PartyDisplayComponent(props) {

    var scrollUp = function() {
        window.scrollTo(0, 0);
    };

    return (
        <div className="unit">
            <div className="list-group-item list-group-item-success">
                <div onClick={props.toggleShow} style={{ cursor: 'pointer' }}>
                    {props.name}
                </div>
            </div>
            <div style={ props.display }>
                <div className="list-group-item">
                    {props.actions}
                    <b style={props.displayLoadingIcon}>Prašome palaukti&nbsp;</b>
                    <img style={props.displayLoadingIcon} src="app/imgs/axios-loader.gif" alt="working-hard"/>
                    {props.confirmDeleteParty}
                </div>
                <InfiniteScroll
                    pageStart={0}
                    hasMore={true || false}
                    initialLoad={false}
                    loader={<div className="loader">Kraunama ...</div>}
                >
                    {props.candidates}
                    <buttton className="btn btn-default btn-sm" onClick={scrollUp}>UP</buttton>
                </InfiniteScroll>
            </div>

        </div>
    );
}

module.exports = PartyDisplayComponent;
