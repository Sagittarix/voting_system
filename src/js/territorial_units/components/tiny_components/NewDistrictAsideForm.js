var React = require('react');
var NewDistrictInlineForm = require('./NewDistrictInlineForm');

function NewDistrictAsideForm(props) {
    return (
        <form>
            <div className="form-group">
                <label htmlFor="inputDistrict">Apygardos pavadinimas</label>
                <input type="text" className="form-control" id="inputDistrictName" value={props.name} onChange={props.changeName}/>
            </div>
            {props.counties}
            <NewDistrictInlineForm addCounty={props.addCounty}/>
            <button type="submit" className="btn btn-primary btn-md" style={{ marginTop: 10 }} onClick={props.create}>Sukurti</button>
        </form>
    )
}

module.exports = NewDistrictAsideForm;
