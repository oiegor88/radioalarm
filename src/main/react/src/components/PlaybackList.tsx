
import {useContext, useEffect} from "react";
import '../index.css'

import { disablePlayback, enablePlayback, deletePlayback } from "../services/playbackService.ts";
import PlaybackEntry from "./PlaybackEntry.tsx";
import {PlaybackContext} from "../context/PlaybackContext.tsx";
import {Typography} from "@mui/material";

const PlaybackList = () => {

    const { playbacks, reloadPlaybacks } = useContext(PlaybackContext);

    useEffect(() => {
        reloadPlaybacks();
    }, []);

    const handleDisablePlayback = async (id: string) => {
        try {
            disablePlayback(id).then(() => reloadPlaybacks());
        } catch (error) {
            console.error('Error disabling playback:', error);
        }
    };

    const handleEnablePlayback = async (id: string) => {
        try {
            enablePlayback(id).then(() => reloadPlaybacks());
        } catch (error) {
            console.error('Error enabling playback:', error);
        }
    };

    const handleDeletePlayback = async (id: string) => {
        try {
            deletePlayback(id).then(() => reloadPlaybacks());
        } catch (error) {
            console.error('Error deleting playback:', error);
        }
    };

    return (
        <div className="py-10 px-40">
            {
                playbacks?.length > 0 ?
                    playbacks.map((item) => (
                        <PlaybackEntry
                            key={item.id}
                            data={item}
                            onDisable={handleDisablePlayback}
                            onEnable={handleEnablePlayback}
                            onDelete={handleDeletePlayback}
                        />
                    )) :
                    <Typography textAlign='center' color='grey'>
                        No playbacks yet
                    </Typography>
            }

        </div>
    );
}

export default PlaybackList
