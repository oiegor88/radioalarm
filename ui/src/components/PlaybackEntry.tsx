import React from "react";
import '../index.css'
import classNames from 'classnames';

import PlaybackStatusIcon from "./PlaybackStatusIcon.tsx";
import Button from "./Button.tsx";

interface PlaybackEntryProps {
    id: string,
    name: string,
    source: string,
    cron: string,
    duration: string,
    enabled: boolean,
    onDisable?: (id: string) => Promise<void>,
    onEnable?: (id: string) => Promise<void>,
}

const PlaybackEntry: React.FC<PlaybackEntryProps> = ({id, name, cron, enabled, onDisable, onEnable}) => {

  return (
      <div className={classNames("flex flex-row items-center border-b border-b-gray-100", {
        'bg-gray-100 text-gray-500': !enabled,
        'bg-white': enabled == true
      })}>
        <div className="p-4 w-1/12">
          <PlaybackStatusIcon enabled={enabled}/>
        </div>
        <div className="p-4 w-5/12">
          <h1>{name}</h1>
        </div>
        <div className="p-4 w-4/12">
          <p>Schedule: {cron}</p>
        </div>
        <div className="p-4 w-2/12 ">
          <div>
            {enabled && <Button label="Disable" color="bg-grey" onClick={() => onDisable(id)}/>}
            {!enabled && <Button label="Enable" color="bg-blue" onClick={() => onEnable(id)}/>}
            <Button label="Delete" color="bg-red"/>
          </div>
        </div>
      </div>
  )
};

export default PlaybackEntry
