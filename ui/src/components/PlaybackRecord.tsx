import React from "react";
import '../index.css'
import classNames from 'classnames';

import PlaybackStatusIcon from "./PlaybackStatusIcon.tsx";
import PlaybackStatus from "../utils/status.tsx";


interface PlaybackRecordProps {
    name: string;
    cron: string;
    status: PlaybackStatus;
}

const PlaybackRecord: React.FC<PlaybackRecordProps> = ({ name, cron, status }) => (
    <div className={classNames("flex", {
        'bg-gray-400': status === PlaybackStatus.INACTIVE,
        'bg-white': status === PlaybackStatus.ACTIVE
    })}>
        <div className="p-4">
            <PlaybackStatusIcon status={status}/>
        </div>
        <div className="p-4 bg-blue-500">
            <h1>Hello, {name}!</h1>
        </div>
        <div className="p-4 bg-blue-500">
            <p>Age: {cron}</p>
        </div>
        <div className="p-4 bg-blue-500">
            <div role="button"> Add</div>
        </div>
    </div>
);

export default PlaybackRecord
