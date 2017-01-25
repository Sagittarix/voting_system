var React = require('react');

var FilterBarContainer = React.createClass({
    propTypes: {
        
    }, 
    render: function() {
        return (
            <div>
                <form>
                    <input type="text" value={this.props.filterText} placeholder="Filter..." onChange={this.props.onChangeFilter}/>
                    <p>
                        <input type="checkbox" checked={this.props.showInStockOnly} onChange={this.props.onChangeCheckbox}/>
                        Only show products in stock
                    </p>
                </form>
            </div>
        );
    }
});

module.exports = FilterBarContainer;