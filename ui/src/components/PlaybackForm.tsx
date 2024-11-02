import {useContext, useState} from "react";
import '../index.css'

import Button from "./Button.tsx";
import { useForm } from 'react-hook-form';
import { PlaybackContext } from "./PlaybackContext.tsx";

export enum FormState {
  COLLAPSED,
  EXPANDED
}

interface PlaybackFormData {
    name: string,
    source: string,
    cron: string,
    duration: string,
    enabled: boolean
}

const PlaybackForm = () => {

  const { register, handleSubmit, reset } = useForm<PlaybackFormData>();
  const { addPlayback } = useContext(PlaybackContext);
  const [state = FormState.COLLAPSED, setState] = useState<FormState>();

  const handleFormSubmit = (data: PlaybackFormData) => {
    addPlayback({
      name: data.name,
      source: data.source,
      cron: data.cron,
      enabled: data.enabled,
      duration: data.duration
    });
    reset();
  };

  const handleFormDiscard = async () => {
    setState(FormState.COLLAPSED);
    reset();
  };

  const handleFormExpand = async () => {
    setState(FormState.EXPANDED);
    reset();
  };

  return (
      state === FormState.EXPANDED ? (
          <form onSubmit={handleSubmit(handleFormSubmit)} >
            <div className={"flex items-center border-b border-b-gray-100"}>
              <div className="p-4">
                <label className={"block"}>
                  Name:
                  <input className={"bg-gray-50 border border-gray-300"}
                         type="text"
                         {...register("name")}/>
                </label>
              </div>
              <div className="p-4">
                <label className={"block"}>
                  Source:
                  <input className={"bg-gray-50 border border-gray-300"}
                         type="url"
                         {...register("source")}/>
                </label>
              </div>
              <div className="p-4">
                <label className={"block"}>
                  Cron:
                  <input className={"bg-gray-50 border border-gray-300"}
                         type="text"
                         {...register("cron")}/>
                </label>
              </div>
              <div className="p-4">
                <label className={"block"}>
                  Duration:
                  <input className={"bg-gray-50 border border-gray-300"}
                         type="text"
                         {...register("duration")}/>
                </label>
              </div>
              <div className="p-4">
                <label className={"block"}>
                  Enabled:
                  <input className={"bg-gray-50 border border-gray-300"}
                         type="checkbox"
                         {...register("enabled")}/>
                </label>
              </div>
              <div className="p-4">
                <div>
                  <Button type="submit" color="bg-green" label="Submit"/>
                  <Button type="button" color="bg-red" label="Discard" onClick={handleFormDiscard}/>
                </div>
              </div>
            </div>
          </form>
      ) : (
          <div className={"flex items-right border-b border-b-gray-100"}>
            <div className="p-4 w-2/12 ">
              <div>
                <Button type="button" label="New" color="bg-blue" onClick={handleFormExpand}/>
              </div>
            </div>
          </div>
      )
  )
};

export default PlaybackForm
