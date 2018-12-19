import React, { Component } from 'react';
import './App.css';

let defaultStyle = {
  color:  '#fff'

}

let fakeServerData = {
  library:{
    videos: [
      {
      name: 'Bitch Lasagna',
      creator: 'Pewdiepie',
      uploadDate: "12/19/1018"
      },
      {
      name: 'You Slav You Lose',
      creator: 'Pewdiepie',
      uploadDate: "12/19/1018"
      },
      {
      name: 'Meme Review',
      creator: 'Pewdiepie',
      uploadDate: "12/19/1018"
      },
      {
      name: 'LWAIY',
      creator: 'Pewdiepie',
      uploadDate: "12/19/1018"
      }
    ]
  }
};

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
        <input type = 'text'/>
        Filter
      </div>
    );
  }
}

class Video extends Component {
  render() {
    return (

      <div style={{...defaultStyle, width: '25%', display: 'inline-block'}}>
        <img/>
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
    this.state = {serverData: {}};
  }

  componentDidMount() {
    setTimeout(() => {
      this.setState({serverData: fakeServerData});
    }, 500);
  }

  render() {
    return (
      <div className="App">
        {this.state.serverData.library ?
        <div>
          <h1 style={{...defaultStyle, fontSize: '56px'}}>VidLib</h1>

          <VideoCounter videos={this.state.serverData.library.videos}/>

          <Filter/>

          {this.state.serverData.library.videos.map(videos =>
            <Video video={videos}/>
          )}

        </div> : <h1 style={defaultStyle}>Loading...</h1>
        }
      </div>
    );
  }
}

export default App;
