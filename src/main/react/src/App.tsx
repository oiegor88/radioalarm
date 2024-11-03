
import PlaybackList from './components/PlaybackList.tsx';
import PlaybackForm from './components/PlaybackForm.tsx';
import {PlaybackProvider} from './context/PlaybackContext.tsx';

function App() {

  return (
      <PlaybackProvider>
        <PlaybackForm/>
        <PlaybackList/>
      </PlaybackProvider>
  )
}

export default App
