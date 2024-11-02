import {useEffect, useState} from "react";
import '../index.css'

import { Playback } from "../services/models";
import {getPlaybacks, disablePlayback, enablePlayback, deletePlayback} from "../services/playbackService.ts";
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
            console.error('Error disabling playback:', error);
        }
    };

    const handleEnablePlayback = async (id: string) => {
        try {
            await enablePlayback(id);
            setDirty(true);
        } catch (error) {
            console.error('Error enabling playback:', error);
        }
    };

    const handleDeletePlayback = async (id: string) => {
        try {
            await deletePlayback(id);
            setDirty(true);
        } catch (error) {
            console.error('Error deleting playback:', error);
        }
    };

    return (
        <div className="py-10 px-40">
            {
                playbacks && playbacks.map((item) => (
                    <PlaybackEntry
                        data={item}
                        onDisable={handleDisablePlayback}
                        onEnable={handleEnablePlayback}
                        onDelete={handleDeletePlayback}
                    />
                ))
            }
        </div>
    );
}

export default PlaybackList
