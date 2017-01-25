var React = require('react');
var Formsy = require('formsy-react');
var Form = Formsy.Form;
var MyInput = require('./MyInput')


const CountyInputs = props => {
    function onRemove(pos) {
        return event => {
            event.preventDefault();
            props.onRemove(pos);
        };
    }

    return (
        <div className="county">
            {props.data.map((countyInput, i) => (
                <div className="countyInput" key={countyInput.id}>
                    <div className="col-sm-12">
                        <div className="col-sm-3 col-sm-offset-1">
                            <MyInput
                                value=""
                                type="text"

                                name={'counties[' + i + '].name'}
                                placeholder="Pavadinimas"
                            />
                        </div>
                        <div className="col-sm-3">
                            <MyInput
                                value=""
                                type="number"
                                name={'counties[' + i + '].voterCount'}
                                placeholder="Balsuotojų skaičius"
                            />
                        </div>
                        <div className="col-sm-1">
                        <div>
                            <button type="button" className="remove-county btn btn-danger" onClick={onRemove(i)}>
                                X
                            </button>
                            </div>
                        </div>
                        <div className="col-sm-4">
                        </div>
                    </div>
                </div>
            ))
            }
        </div>
    );
};

module.exports = CountyInputs;