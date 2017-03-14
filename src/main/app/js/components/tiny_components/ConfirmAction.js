var React = require('react');
var Confirm = require('react-confirm-bootstrap');

function ConfirmAction(props) {
    var confirmText;
    var cancelText;
    if(props.confirmText != null){
        confirmText = props.confirmText;
    } else {
        confirmText = "Atšaukti";
    }
    if(props.cancelText != null){
        cancelText = props.cancelText
    } else {
        cancelText = "Atšaukti"
    }
    return (
        <Confirm
            onConfirm={props.onConfirm}
            title={props.title}
            body={props.body}
            confirmText={confirmText}
            cancelText={cancelText}
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
