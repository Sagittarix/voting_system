var React = require('react');

function NewCountyInlineForm(props) {
    return (
        <div className="inline-add-county-form">
            <form>
                <div className="form-group">
                    <input type="text" onChange={props.changeName} className="form-control" value={props.name} placeholder="Apylinkės pav."/>
                </div>
                <div className="form-group">
                    <input type="number" onChange={props.changeVoterCount} className="form-control" value={props.count} placeholder="Gyv. skaičius" min={1}/>
                </div>
                <div className="form-group">
                    <button type="submit" className="btn btn-primary btn-sm" onClick={props.submit}>Sukurti</button>
                    <button className="btn btn-warning btn-sm" onClick={props.cancel}>Atšaukti</button>
                </div>
            </form>
        </div>
    )
};

module.exports = NewCountyInlineForm;
