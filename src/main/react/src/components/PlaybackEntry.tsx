import React from "react";
import '../index.css'
import { Playback } from '../services/apiModels';
import { Box, Button, Typography } from '@mui/material';
import { AlarmOff, AlarmOn } from '@mui/icons-material';

interface PlaybackEntryProps {
  data: Playback,
  onDisable: (id: string) => Promise<void>,
  onEnable: (id: string) => Promise<void>,
  onDelete: (id: string) => Promise<void>,
}

const PlaybackEntry: React.FC<PlaybackEntryProps> = ({data, onDisable, onEnable, onDelete}) => {

  return (
      <Box className={data.enabled ? "bg-gray-50" : "bg-gray-200 opacity-75"}
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'start',
            padding: 2,
            gap: 2,
            borderBottom: '1px solid #ddd',
            width: '100%',
            maxWidth: '50rem',
            margin: '0 auto',
          }}
      >

        {data.enabled ? <AlarmOn/> : <AlarmOff/>}

        <Typography
            variant="body1"
            color="text.primary"
            sx={{ width: '30rem' }}>
          {data.name}
        </Typography>

        <Typography
            variant="body2"
            color="text.secondary"
            sx={{ width: '10rem' }}>
          {data.cron}
        </Typography>

        <Box sx={{ display: 'flex',
          alignItems: 'center',
          justifyContent: 'end',
          gap: 1,
          width: '100%',
           }}>
          {data.enabled ?
              <Button
                  variant="contained"
                  color="warning"
                  size="small"
                  onClick={() => onDisable(data.id)}>
                Disable
              </Button> :
              <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  onClick={() => onEnable(data.id)}>
                Enable
              </Button>
          }

          <Button
              variant="contained"
              color="error"
              size="small"
              onClick={() => onDelete(data.id)}> Delete </Button>
        </Box>
      </Box>
  )
};

export default PlaybackEntry
