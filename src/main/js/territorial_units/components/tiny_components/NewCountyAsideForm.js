var React = require('react');

function NewCountyAsideForm(props) {
    return (
        <form>
            <div className="form-group">
                <label htmlFor="inputCounty">Apylinkės pavadinimas</label>
                <input type="text" className="form-control" value={props.name} onChange={props.changeName}/>
            </div>
            <div className="form-group">
                <label htmlFor="inputCountyPopulation">Gyventojų skaičius</label>
                <input type="number" className="form-control" min={1} value={props.count} onChange={props.changeCount}/>
            </div>
            <div className="county-form-actions">
                <button type="submit" onClick={props.cancel} className="btn btn-warning btn-xs county-cancel-button">Atšaukti</button>
                <button type="submit" onClick={props.add} className="btn btn-primary btn-xs county-add-button">Pridėti</button>
            </div>
        </form>
    )
}

module.exports = NewCountyAsideForm;
