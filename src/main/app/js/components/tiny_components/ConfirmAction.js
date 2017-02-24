var React = require('react')
var Confirm = require('react-confirm-bootstrap');

function ConfirmAction(props) {
    return (
        <Confirm
            onConfirm={props.onConfirm}
            title={props.title}
            body={props.body}
            confirmText={props.confirmText || "Patvirtinti"}
            cancelText={props.cancelText || "Atšaukti"}
        >
            {props.children}
        </Confirm>
    )
}

ConfirmAction.propTypes = {
    onConfirm: React.PropTypes.func.isRequired,
    title: React.PropTypes.string.isRequired,
    body: React.PropTypes.string.isRequired,
    confirmText: React.PropTypes.string,
    cancelText: React.PropTypes.string
}

module.exports = ConfirmAction;
