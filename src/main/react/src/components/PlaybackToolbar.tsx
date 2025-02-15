
import PlaybackCreatePopup from './PlaybackCreatePopup.tsx';

const PlaybackToolbar = () => {

  return (

      <div className={"px-40 p-4 flex justify-end bg-gray-100 shadow-lg"}>
        <div className="text-right">
          <PlaybackCreatePopup/>
        </div>
      </div>

  )
};

export default PlaybackToolbar
