
import DialogBox from './DialogBox.tsx';
import PlaybackForm from "./PlaybackForm.tsx";

const PlaybackToolbar = () => {

  return (

      <div className={"px-40 p-4 flex justify-end bg-gray-100 shadow-lg"}>
        <div className="text-right">
          <DialogBox title="New Playback" cta="New Playback" content={<PlaybackForm/>}/>
        </div>
      </div>

  )
};

export default PlaybackToolbar
