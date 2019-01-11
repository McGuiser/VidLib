import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './App.css';
import { access } from 'fs';

let defaultStyle = {
  color:  '#fff'

}

class Uploader extends Component {
  

  state = { 
    show: false
  }

  video = {
    name: null,
    creator: 'Corey',
    date: '12/28/2018',
    videoSource: null,
    thumbnailSource: null
  }

  showModal = () => {
    this.setState({ show: true });
  }
  
  hideModal = () => {
    this.setState({ show: false });
  }

  hasNull(target) {
    for (let member in target) {
        if (target[member] == null)
            return true;
    }
    return false;
  }

  createVideo(){

    let headers = new Headers();

    //headers.set('Authorization', 'Basic ' + window.btoa("mary:fun1234"));
    headers.set("Content-Type", "application/json")
    headers.append('Authorization', 'Bearer ' + localStorage.getItem('accessToken'))

    console.log(localStorage.getItem('accessToken'))

    console.log('In not null: ' + JSON.stringify(this.video));
    fetch("http://localhost:8080/api/videos", {
      method: "POST",
      body: JSON.stringify(this.video),
      headers: headers
    }).then(response => response)
    .then(data => {
      console.log('Created new video: ' + JSON.stringify(data));
    }).catch(error => {
      console.log('ERROR: ' + error);
    })

  }


  uploadFile(signedUrlContentType, file, extension) {

    let signedURL = '';
    let request = new XMLHttpRequest();
    const secureURL = new URL('http://localhost:8080/api/secure');
    secureURL.search = new URLSearchParams({fileExtension: extension});
    this.video.name = document.getElementById('videoTitle').value;

    fetch(secureURL).then(response => response.json())
    .then(uploadData => {
      console.log(uploadData[0]);
      console.log(uploadData[1]);
      console.log(typeof uploadData);
      signedURL = uploadData[0];
      console.log("here " + signedURL);
      
      request.open('PUT', signedURL, true);
      request.setRequestHeader('Content-Type', signedUrlContentType);

      request.upload.onprogress = (event) => {
        if (event.lengthComputable) {
          var percent = Math.round((event.loaded / event.total) * 100)
          console.log(percent);
        }
      };
  
      request.onload = () => {
        if (request.status === 200) {

          if(extension === '.mp4') {
            this.video.videoSource = 'https://s3-us-west-1.amazonaws.com/vidlib.io/' + uploadData[1];
          } else {
            this.video.thumbnailSource = 'https://s3-us-west-1.amazonaws.com/vidlib.io/' + uploadData[1];
          }
          
          console.log('Success');
          if(!this.hasNull(this.video)){
            this.createVideo();
          }
        }
      };
      request.onerror = () => {
        console.log('Error');
      };
      request.send(file);
      return uploadData;
    })
    
  }

  render(){

    return (
      <div id ="uploadInterface">
          <button onClick={() => this.showModal()}>Upload a video</button>
          <Modal show={this.state.show} handleClose={this.hideModal} >
            <div id = "uploadForm" style={{position: 'absolute', bottom: '50%', left: '40%', marginBottom: '10px'}}>

              <label>Video title: </label>
              <input id='videoTitle' type='text'/>
              <br></br>

              <label>Video file: </label>
              <input id='videoFile' type='file'/>
              <br></br>

              <label>Thumbnail file: </label>
              <input id='thumbnailFile' type='file'/>
              <br></br>

              <label>Description: </label>
              <textarea id="videoDescription"></textarea>
              <br></br>

              <button onClick={() => {
                this.uploadFile(
                  'video/mp4', 
                  document.getElementById('videoFile').files[0],
                  '.mp4');
                
                this.uploadFile(
                  'image/jpeg', 
                  document.getElementById('thumbnailFile').files[0],
                  '.jpg');
              }}>
                Upload
              </button>

            </div>
          </Modal>

      </div>
    );

  }
}


class VideoCounter extends Component {
  render() {

    return (
      <div style={{...defaultStyle, width: '40%', display: 'inline-block'}}>

        {this.props.videos && <h2>{this.props.videos.length} videos to choose from!</h2>}

      </div>
    );
  }
}

class Filter extends Component {
  render() {
    return(
      <div style={defaultStyle}>
        <input type = 'text' onKeyUp={event => 
          this.props.onTextChange(event.target.value)}/>
      </div>
    );
  }
}

class CommentSection extends Component {
  componentDidMount(){
    console.log(this.props);
  }
  

  render() {
    return(
      <div>
      {this.props.comments && 
      <div style={{...defaultStyle, width: '30%', display: 'inline-block', float: 'bottom'}}>
        {this.props.comments.map(comments =>
          <Comment comment={comments}/>
        )}
      </div>}
      </div>
    );
  }
}

class Comment extends Component {
  render() {
    return(
      <div style={{...defaultStyle, width: '100%', display: 'inline-block', color: '#000'}}>
        {this.props.comment.user.userName}: {this.props.comment.comment}
      </div>
    );
  }
}

class Video extends Component {

  state = { show: false }

  showModal = () => {
    fetch('http://localhost:8080/api/' + this.props.video.id + '/comments')
      .then(response => response.json())
      .then(comments => {
        console.log(comments);
        this.setState({ show: true, 
                        comments: comments});
        return comments;
      })
  }
  
  hideModal = () => {
    this.setState({ show: false });
  }

  render() {
    return (

      <div style={{...defaultStyle, width: '25%', display: 'inline-block'}}>
        <img src={this.props.video.thumbnailSource} alt='Thumbnail' style={{width: '210px'}} onClick={() => this.showModal()}></img>
        <Modal show={this.state.show} handleClose={this.hideModal} >
          <video width="700" controls>
            <source src={this.props.video.videoSource} type="video/mp4"/>
            Your browser does not support HTML5 video.
          </video>
          <br></br>
          <CommentSection comments={this.state.comments}/>
        </Modal>
        <h3>{this.props.video.name}</h3>
        <h4>{this.props.video.creator}</h4>
        <h4>{this.props.video.uploadDate}</h4>
      </div>

    );
  }
}

class App extends Component {

  constructor(props) {
    super(props);
    this.state = 
    {
      serverData: {},
      filterString: ''
    };
  }

  componentDidMount() {
    /*setTimeout(() => {
      this.setState({serverData: fakeServerData});
    }, 500);*/

    let data ={"usernameOrEmail": "mary", "password": "fun123"};

    let headers = new Headers();

    //headers.set('Authorization', 'Basic ' + window.btoa("mary:fun1234"));
    headers.set("Content-Type", "application/json")

    fetch("http://localhost:8080/api/auth/signin", {
      method: "POST",
      body: JSON.stringify(data),
      headers: headers
    })
    .then(response => {
      console.log("Authenticated Succesfully: " + JSON.stringify(response));

      if (response.ok) {
        response.json().then(json => {
          console.log(json);
          localStorage.setItem("accessToken", json.accessToken);
          console.log(localStorage.getItem("accessToken"));
        });
        headers.append('Authorization', 'Bearer ' + localStorage.getItem("accessToken"))
        fetch("http://localhost:8080/api/user/me", {
          method: "GET",
          headers: headers
        })
        .then(response => {

          if (response.ok) {
            console.log("here");
            response.json().then(json => console.log(json))
          }
        })
    }}).catch(error => {
      console.log("Authentication Error: " + error);
    })

    /*
    let newUser ={"firstName": "Michael", "lastName": "Scott", "username": "MScarn", "email": "mscott@dmifflin.com", "password": "password"};

    fetch("http://localhost:8080/api/auth/signup", {
      method: "POST",
      body: JSON.stringify(newUser),
      headers: {"Content-Type": "application/json"}
    })
    .then(response => {
      console.log("Signup Succesfully: " + JSON.stringify(response));

        response.json().then(json => {
          console.log(json);
          localStorage.setItem("accessToken", json.accessToken);
          console.log(localStorage.getItem("accessToken"));
        });
    }).catch(error => {
      console.log("Signup Error: " + error);
    })*/

    fetch('http://localhost:8080/api/videos')
      .then(response => response.json())
      .then(playlistData => {
        console.log(playlistData);
        this.setState({serverData: playlistData});
        return playlistData;
      })

    setTimeout(() => {
      this.setState({filterString: ''});
    }, 500);
  }

  render() {
    return (
      <div className="App">
        {this.state.serverData[0] ?
        <div>
          <h1 style={{...defaultStyle, fontSize: '56px'}}>VidLib</h1>
          
          <Uploader upload={this.state.signedURL}/>

          <VideoCounter videos={this.state.serverData}/>

          <Filter onTextChange={text => {
            this.setState({filterString: text})
          }}/>

          <div style={{...defaultStyle, width: '80%', display: 'inline-block', float: 'right'}}>
            {this.state.serverData.filter(videos =>
              videos.name.toLowerCase().includes(this.state.filterString.toLowerCase())
            ).map(videos =>
              <Video video={videos}/>
            )}
          </div>

        </div> : <h1 style={defaultStyle}>Loading...</h1>
        }
      </div>
    );
  }
}

const Modal = ({ handleClose, show, children }) => {
  const showHideClassName = show ? 'modal display-block' : 'modal display-none';

  return (
    <div className={showHideClassName}>
      <section className='modal-main'>
        {children}
        <div>
        <button style={{position: 'absolute', bottom: '0', marginBottom: '10px'}} onClick={handleClose}>
          Close
        </button>
        </div>
      </section>
    </div>
  );
};

const container = document.createElement('div');
document.body.appendChild(container);
ReactDOM.render(<App />, container);

export default App;
