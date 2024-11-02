import PlaybackRecord from './components/PlaybackRecord.tsx'

import {getPlaybacks} from "./services/playbackService.ts";
import {useEffect, useState} from "react";
import {Playback} from "./services/models.ts";

function App() {

  const [playbacks, setPlaybacks] = useState<Playback[]>();

  useEffect(() => {
    fetchPlaybacks();
  }, []);

  const fetchPlaybacks = async () => {
    try {
      setPlaybacks(await getPlaybacks());
    } catch (error) {
      console.error('Error fetching playbacks:', error);
    }
  };

  return (
    <div className="py-10 px-40">
      {
          playbacks && playbacks.map((item) => (
              <PlaybackRecord
                  id={item.id}
                  name={item.name}
                  cron={item.cron}
                  enabled={item.enabled}
                  source={item.source}
                  duration={item.duration}
              />
          ))
      }
    </div>
  )
}

export default App
