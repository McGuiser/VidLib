import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './App.css';

let defaultStyle = {
  color:  '#fff'

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

class Video extends Component {

  state = { show: false }

  showModal = () => {
    this.setState({ show: true });
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

    fetch('http://localhost:8080/api/videos').then(response => response.json())
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

          <VideoCounter videos={this.state.serverData}/>

          <Filter onTextChange={text => {
            this.setState({filterString: text})
          }}/>

          {this.state.serverData.filter(videos =>
            videos.name.toLowerCase().includes(this.state.filterString.toLowerCase())
          ).map(videos =>
            <Video video={videos}/>
          )}

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
        <button onClick={handleClose}>
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
