import React from "react";
import '../index.css'
import classNames from 'classnames';

import PlaybackStatusIcon from "./PlaybackStatusIcon.tsx";
import PlaybackStatus from "../utils/status.tsx";
import Button from "./Button.tsx";


interface PlaybackRecordProps {
    name: string;
    cron: string;
    status: PlaybackStatus;
}

const PlaybackRecord: React.FC<PlaybackRecordProps> = ({ name, cron, status }) => (

    <div className={classNames("flex flex-row items-center border-b border-b-gray-100", {
        'bg-gray-100 text-gray-500': status === PlaybackStatus.INACTIVE,
        'bg-white': status === PlaybackStatus.ACTIVE
    })}>
        <div className="p-4 w-1/12">
            <PlaybackStatusIcon status={status}/>
        </div>
        <div className="p-4 w-5/12">
            <h1>{name}</h1>
        </div>
        <div className="p-4 w-4/12">
            <p>Schedule: {cron}</p>
        </div>
        <div className="p-4 w-2/12 ">
            <div>
                { status === PlaybackStatus.ACTIVE
                    && <Button label="Disable" color="bg-grey"/>
                }
                { status === PlaybackStatus.INACTIVE
                    && <Button label="Enable" color="bg-blue"/>
                }
                <Button label="Delete" color="bg-red"/>
            </div>
        </div>
    </div>
);

export default PlaybackRecord
