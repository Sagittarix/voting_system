var React = require('react');

var Helpers = {
    createdOn: function(millis) {
        var timeStamp = new Date(millis);
        var month = timeStamp.getMonth() + 1;
        var date = timeStamp.getDate();
        var hours = timeStamp.getHours();
        var mins = timeStamp.getMinutes();
        var secs = timeStamp.getSeconds();

        if (month < 10) month = "0" + month;
        if (date < 10) date = "0" + date;
        if (hours < 10) hours = "0" + hours;
        if (mins < 10) mins = "0" + mins;
        if (secs < 10) secs = "0" + secs;

        return (
            <div>
                <div className="list-group-item active" style={{'marginTop': 10}}>
                    Rezultatų suvedimas:
                </div>
                <div className="list-group-item">
                    <span>{timeStamp.getFullYear()}</span>
                    <span>/{month}</span>
                    <span>/{date} </span> &nbsp;
                    <span>{hours}</span>
                    <span>:{mins}</span>
                    <span>:{secs}</span>
                </div>
            </div>
        );
    },
    currentTime: function() {
        var timeStamp = new Date();
        var hours = timeStamp.getHours();
        var mins = timeStamp.getMinutes();
        var secs = timeStamp.getSeconds();

        if (hours < 10) hours = "0" + hours;
        if (mins < 10) mins = "0" + mins;
        if (secs < 10) secs = "0" + secs;

        return (
            <span id="current-time">
                <span>{hours}</span>
                <span>:{mins}</span>
                <span>:{secs}</span>
            </span>
        );
    },
};

module.exports = Helpers;
