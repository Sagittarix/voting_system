var React = require('react');

function NewCountyAsideForm(props) {
    return (
        <form className="aside-county-form">
            <div className="form-group county-aside">
                <label htmlFor="inputCounty">Apylinkės pavadinimas</label>
                <input type="text" className="form-control" value={props.name} onChange={props.changeName}/>
            </div>
            <div className="form-group county-aside">
                <label>Gyventojų skaičius</label>
                <input type="number" className="form-control" min={1} value={props.count} onChange={props.changeCount}/>
            </div>
            <div className="form-group county-aside">
                <label>Adresas</label>
                <input type="text" className="form-control" value={props.address} onChange={props.changeAddress}/>
            </div>
            <div className="county-form-actions">
                <button type="submit" onClick={props.add} className="btn btn-default btn-xs ">Pridėti</button>
                <button type="submit" onClick={props.cancel} className="btn btn-default btn-xs ">Atšaukti</button>
            </div>
        </form>
    )
}

module.exports = NewCountyAsideForm;
