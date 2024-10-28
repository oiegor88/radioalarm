
import PlaybackRecord from './components/PlaybackRecord.tsx'
import PlaybackStatus from './utils/status.tsx'

function App() {

  return (
    <>
        <PlaybackRecord name={'Radio Polska 24'} cron={'* 0 * * * *'} status={PlaybackStatus.ACTIVE}/>
        <PlaybackRecord name={'Radio caprice'} cron={'* 0 * * * *'} status={PlaybackStatus.INACTIVE}/>
    </>
  )
}

export default App
