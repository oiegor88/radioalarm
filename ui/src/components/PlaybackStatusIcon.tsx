import PlaybackStatus from "../utils/status.tsx";
import React from "react";
import sound_on from '@images/sound-on.svg';
import sound_off from '@images/sound-off.svg';

interface PlaybackStatusIconProps {
    status: PlaybackStatus;
}

const PlaybackStatusIcon: React.FC<PlaybackStatusIconProps> = ({ status }) => (
    <div>
        {status === PlaybackStatus.ACTIVE ?
            <img className = "h-5" src = { sound_on } alt = "Active" /> :
            <img className = "h-5" src = { sound_off} alt = "Disabled"/>
        }
    </div>
);

export default PlaybackStatusIcon
