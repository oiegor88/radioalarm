
export interface Playback {
  id: string,
  name: string,
  source: string,
  cron: string,
  duration: string,
  enabled: boolean
};

export interface PlaybackCreateRequest {
  name: string,
  source: string,
  cron: string,
  duration: string,
  enabled: boolean
}