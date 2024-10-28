
import PlaybackRecord from './components/PlaybackRecord.tsx'
import PlaybackStatus from './utils/status.tsx'

function App() {

  return (
      <>
          <div className="py-10 px-40">
              <PlaybackRecord name={'Radio Polska 24'} cron={'* 0 * * * *'} status={PlaybackStatus.ACTIVE}/>
              <PlaybackRecord name={'Radio Polska 24'} cron={'* 0 * * * *'} status={PlaybackStatus.ACTIVE}/>
              <PlaybackRecord name={'Radio Polska 24'} cron={'* 0 * * * *'} status={PlaybackStatus.ACTIVE}/>
              <PlaybackRecord name={'Radio caprice'} cron={'* */2 * * * *'} status={PlaybackStatus.INACTIVE}/>
          </div>
      </>
  )
}

export default App
