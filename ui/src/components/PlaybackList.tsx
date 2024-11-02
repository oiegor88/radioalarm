import {useEffect, useState} from "react";
import '../index.css'

import { Playback } from "../services/models";
import { getPlaybacks, disablePlayback, enablePlayback } from "../services/playbackService.ts";
import PlaybackEntry from "./PlaybackEntry.tsx";

const PlaybackList = () => {

    const [playbacks, setPlaybacks] = useState<Playback[]>();
    const [dirty = true, setDirty] = useState<boolean>();

    useEffect(() => {
        dirty && fetchPlaybacks();
    }, [dirty]);

    const fetchPlaybacks = async () => {
        try {
            setPlaybacks(await getPlaybacks());
            setDirty(false);
        } catch (error) {
            console.error('Error fetching playbacks:', error);
        }
    };

    const handleDisablePlayback = async (id: string) => {
        try {
            await disablePlayback(id);
            setDirty(true);
        } catch (error) {
            console.error('Error fetching playbacks:', error);
        }
    };

    const handleEnablePlayback = async (id: string) => {
        try {
            await enablePlayback(id);
            setDirty(true);
        } catch (error) {
            console.error('Error fetching playbacks:', error);
        }
    };

    return (
        <div className="py-10 px-40">
            {
                playbacks && playbacks.map((item) => (
                    <PlaybackEntry
                        id={item.id}
                        name={item.name}
                        cron={item.cron}
                        enabled={item.enabled}
                        source={item.source}
                        duration={item.duration}
                        onDisable={handleDisablePlayback}
                        onEnable={handleEnablePlayback}
                    />
                ))
            }
        </div>
    );
}

export default PlaybackList
