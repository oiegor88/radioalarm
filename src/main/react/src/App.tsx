
import PlaybackList from './components/PlaybackList.tsx';
import {PlaybackProvider} from './context/PlaybackContext.tsx';
import PlaybackToolbar from './components/PlaybackToolbar.tsx';

function App() {

  return (
      <PlaybackProvider>
        <PlaybackToolbar/>
        <PlaybackList/>
      </PlaybackProvider>
  )
}

export default App
