var InputComponent = React.createClass({
    render: function() {
        return (
            <div>
                <form method="POST" encType="multipart/form-data" action="/upload-csv">
                    <div className="form-group">
                        <label htmlFor="inputFile">Upload single mandate candidates</label>
                        <input type="file" name="file" id="inputFile"/>
                    </div>
                    <button type="submit" value="upload" className="btn btn-primary btn-lg">Upload</button>
                </form>
            </div>
        );
    }
});

window.InputComponent = InputComponent;