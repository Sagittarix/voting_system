var React = require('react');

var SortingFilter = function(props) {
    return(
        <div className="sorting-filter">
            <form>
                <label> Rikiuoti pagal:</label>
                <label className="radio-inline">
                    <input checked={props.orderBy==='abc'} onChange={props.onChange} value="abc" type="radio" name="optradio"/>vardą
                </label>
                <label className="radio-inline">
                    <input checked={props.orderBy==='count'} onChange={props.onChange} value="count" type="radio" name="optradio"/>balsus
                </label>
            </form>
        </div>
    );
}

SortingFilter.propTypes = {
    orderBy: React.PropTypes.string,
    onChange: React.PropTypes.func.isRequired
}

module.exports = SortingFilter;