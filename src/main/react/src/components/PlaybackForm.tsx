import {useContext} from 'react';
import '../index.css'

import { useForm } from 'react-hook-form';
import { PlaybackContext } from '../context/PlaybackContext.tsx';
import {Box, Button, TextField} from "@mui/material";

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

  const handleFormSubmit = (data: PlaybackFormData) => {
    addPlayback({
      name: data.name,
      source: data.source,
      cron: data.cron,
      enabled: data.enabled,
      duration: data.duration
    });
  };

  return (
      <Box
          component="form"
          onSubmit={() => { handleSubmit(handleFormSubmit); reset() }}
          mx={{
            display: 'flex',
            flexDirection: 'column',
            gap: 10,
            width: '50rem',
            margin: '0 auto',
            mt: 5
          }}>

        <TextField
            label="Name"
            helperText="Arbitrary playback name"
            variant="outlined"
            required
            {...register("name")}
        />

        <TextField
            label="Source"
            helperText="Playback source URL"
            variant="outlined"
            required
            {...register("source")}
        />

        <TextField
            label="Playback scheludle"
            helperText="Example: 0 30 06 * * *"
            variant="outlined"
            required
            {...register("cron")}
        />

        <TextField
            label="Playback duration"
            helperText="Example: PT5m"
            variant="outlined"
            required
            {...register("duration")}
        />

        <TextField
            label="Enabled"
            helperText="Disabled playbacks won't play"
            variant="outlined"
            required
            {...register("enabled")}
        />

        <Button variant="contained" color="primary" type="submit">
          Submit
        </Button>
      </Box>


  )
};

export default PlaybackForm
