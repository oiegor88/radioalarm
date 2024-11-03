import {ReactElement, useState} from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';

interface DialogBoxProps {
  title: string,
  cta: string,
  content: ReactElement
}

const DialogBox = (props:DialogBoxProps) => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
      <div>
        <Button variant="contained" color="primary" onClick={handleOpen}>
          { props.cta }
        </Button>
        <Dialog open={open} onClose={handleClose} maxWidth="md">
          <DialogTitle> {props.title} </DialogTitle>
          <DialogContent>
            { props.content }
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      </div>
  );
}

export default DialogBox;