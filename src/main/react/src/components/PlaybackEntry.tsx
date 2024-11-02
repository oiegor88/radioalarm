import React from "react";
import '../index.css'
import classNames from 'classnames';

import PlaybackStatusIcon from "./PlaybackStatusIcon.tsx";
import Button from "./Button.tsx";
import {Playback} from "../services/models.ts";

interface PlaybackEntryProps {
  data: Playback,
  onDisable: (id: string) => Promise<void>,
  onEnable: (id: string) => Promise<void>,
  onDelete: (id: string) => Promise<void>,
}

const PlaybackEntry: React.FC<PlaybackEntryProps> = ({data, onDisable, onEnable, onDelete}) => {

  return (
      <div className={classNames("flex flex-row items-center border-b border-b-gray-100", {
        'bg-gray-100 text-gray-500': !data.enabled,
        'bg-white': data.enabled
      })}>
        <div className="p-4 w-1/12">
          <PlaybackStatusIcon enabled={data.enabled}/>
        </div>
        <div className="p-4 w-5/12">
          <h1>{data.name}</h1>
        </div>
        <div className="p-4 w-4/12">
          <p>Schedule: {data.cron}</p>
        </div>
        <div className="p-4 w-2/12 ">
          <div>
            {data.enabled && <Button type="button" label="Disable" color="bg-grey" onClick={() => onDisable(data.id)}/>}
            {!data.enabled && <Button type="button" label="Enable" color="bg-blue" onClick={() => onEnable(data.id)}/>}
            <Button type="button" label="Delete" color="bg-red" onClick={() => onDelete(data.id)}/>
          </div>
        </div>
      </div>
  )
};

export default PlaybackEntry
