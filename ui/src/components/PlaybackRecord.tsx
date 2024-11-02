import React from "react";
import '../index.css'
import classNames from 'classnames';

import PlaybackStatusIcon from "./PlaybackStatusIcon.tsx";
import Button from "./Button.tsx";
import { Playback } from "../services/models";
import { enablePlayback, disablePlayback } from "../services/playbackService.ts";

const PlaybackRecord: React.FC<Playback> = ({ name, cron, enabled }) => (

    <div className={classNames("flex flex-row items-center border-b border-b-gray-100", {
        'bg-gray-100 text-gray-500': !enabled,
        'bg-white': enabled == true
    })}>
        <div className="p-4 w-1/12">
            <PlaybackStatusIcon enabled={ enabled }/>
        </div>
        <div className="p-4 w-5/12">
            <h1>{ name }</h1>
        </div>
        <div className="p-4 w-4/12">
            <p>Schedule: { cron }</p>
        </div>
        <div className="p-4 w-2/12 ">
            <div>
                { enabled && <Button label="Disable" color="bg-grey" /> }
                { !enabled && <Button label="Enable" color="bg-blue" /> }
                <Button label="Delete" color="bg-red"/>
            </div>
        </div>
    </div>
);

export default PlaybackRecord
