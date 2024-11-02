import {createContext, useState} from "react";
import {Playback, PlaybackCreateRequest} from "../services/models.ts";
import { getPlaybacks, createPlayback } from "../services/playbackService.ts";

export const PlaybackContext = createContext<{
  playbacks: Playback[];
  addPlayback: (playback: PlaybackCreateRequest) => void;
  reloadPlaybacks: () => void;
}>({
  playbacks: [],
  addPlayback: () => {},
  reloadPlaybacks: () => {}
});

// @ts-ignore
export const PlaybackProvider = ({ children }) => {
  const [playbacks, setPlaybacks] = useState<Playback[]>([]);

  const addPlayback = (data: PlaybackCreateRequest) => {
    createPlayback({
      name: data.name,
      source: data.source,
      cron: data.cron,
      enabled: data.enabled,
      duration: data.duration
    }).then(() => reloadPlaybacks())
  };

  const reloadPlaybacks = async () => {
    try {
      getPlaybacks().then(playbacks=> setPlaybacks(playbacks));
    } catch (error) {
      console.error('Error fetching playbacks:', error);
    }
  };

  return (
      <PlaybackContext.Provider value={{ playbacks, addPlayback, reloadPlaybacks }}>
          { children }
      </PlaybackContext.Provider>
  );
};