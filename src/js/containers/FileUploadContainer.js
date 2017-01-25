var React = require('react');
var ReactRouter = require('react-router');
var axios = require('axios');
var Link = ReactRouter.Link;


var FileUploadContainer = React.createClass({
    propTypes: {
        
    }, 
    handleUploadFile: function(e) {
        e.preventDefault();
        var file = this.refs.file.files[0];
        var config = { headers: { 'Content-Type': 'multipart/form-data' } };
        var fd = new FormData();

        fd.append('file',file)
        console.log("PROPS")
        console.log(this.props)
        this.props.onUpload(axios.post(this.props.uploadToUrl, fd, config))
    },
    render: function() {
        return (
            <div>
                <form ref="uploadForm" className="uploadForm" encType="multipart/form-data">
                    <div className="form-group">
                        <label>Pridėti kandidatų sąrašą iš CSV:</label>
                        <input ref="file" type="file" name="file" className="form-control-file"/>
                        <button type="button" className="btn " onClick={this.handleUploadFile}>
                            Siųsti 
                        </button>
                    </div>
                </form> 
            </div>
        );
    }
});


                
module.exports = FileUploadContainer;