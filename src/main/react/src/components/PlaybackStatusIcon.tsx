
import React from "react";
import sound_on from '@images/sound-on.svg';
import sound_off from '@images/sound-off.svg';

interface PlaybackStatusIconProperties {
    enabled: boolean
}

const PlaybackStatusIcon: React.FC<PlaybackStatusIconProperties> = ({ enabled }) => (
    <div>
        { enabled ?
            <img className = "h-5" src = { sound_on } alt = "Active" /> :
            <img className = "h-5" src = { sound_off } alt = "Disabled"/>
        }
    </div>
);

export default PlaybackStatusIcon
