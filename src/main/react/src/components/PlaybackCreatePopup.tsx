import { useContext, useState } from 'react';
import {Dialog, DialogTitle, DialogContent, DialogActions, Button, Box, TextField, InputAdornment } from '@mui/material';
import { AlarmAdd, EventRepeat, Link, Timelapse } from '@mui/icons-material';
import { useForm } from 'react-hook-form';
import { PlaybackContext } from '../context/PlaybackContext.tsx';

interface PlaybackFormData {
  name: string,
  source: string,
  cron: string,
  duration: string
}

const PlaybackCreatePopup = () => {
  const [open, setOpen] = useState(false);
  const { register, handleSubmit, reset } = useForm<PlaybackFormData>();
  const { addPlayback } = useContext(PlaybackContext);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    reset();
  };

  const handleFormSubmit = (data: PlaybackFormData) => {
    addPlayback({
      name: data.name,
      source: data.source,
      cron: data.cron,
      duration: data.duration
    });
    handleClose();
  };

  return (
      <div>
        <Button variant="contained" color="primary" onClick={handleOpen} startIcon={<AlarmAdd />}>
          Add Playback
        </Button>
        <Dialog open={open} onClose={handleClose} maxWidth="md">
          <DialogTitle> Add Playback </DialogTitle>
          <DialogContent className="pt-px-10">

            <Box
                component="form"
                onSubmit={ handleSubmit(handleFormSubmit)}
                mx={{
                  display: 'flex',
                  flexDirection: 'column',
                  gap: 20,
                  width: '50rem',
                  margin: '0 auto'
                }}>

              <TextField
                  label="Name"
                  placeholder="Arbitrary playback name"
                  variant="outlined"
                  slotProps={{
                    input: {
                      startAdornment: (
                          <InputAdornment position="start">
                            <AlarmAdd />
                          </InputAdornment>
                      ),
                    },
                  }}
                  required
                  {...register("name")}
              />

              <TextField
                  label="Source"
                  placeholder="Playback source URL"
                  variant="outlined"
                  slotProps={{
                    input: {
                      startAdornment: (
                          <InputAdornment position="start">
                            <Link />
                          </InputAdornment>
                      ),
                    },
                  }}
                  required
                  {...register("source")}
              />

              <TextField
                  label="Playback scheludle"
                  placeholder="Example: 0 30 06 * * *"
                  variant="outlined"
                  slotProps={{
                    input: {
                      startAdornment: (
                          <InputAdornment position="start">
                            <EventRepeat />
                          </InputAdornment>
                      ),
                    },
                  }}
                  required
                  {...register("cron")}
              />

              <TextField
                  label="Playback duration"
                  placeholder="Example: PT5m"
                  variant="outlined"
                  slotProps={{
                    input: {
                      startAdornment: (
                          <InputAdornment position="start">
                            <Timelapse />
                          </InputAdornment>
                      ),
                    },
                  }}
                  required
                  {...register("duration")}
              />

              <Button variant="contained" color="primary" type="submit">
                Submit
              </Button>
            </Box>

          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      </div>
  );
};

export default PlaybackCreatePopup;