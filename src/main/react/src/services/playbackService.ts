
import { AxiosResponse } from 'axios';
import {Playback, PlaybackCreateRequest} from './models';
import { apiClient } from './apiClient'

export const getPlaybacks = async (): Promise<Playback[]> => {
  try {
    const response: AxiosResponse<Playback[]> = await apiClient.get(`/api/playback`);
    return response.data;
  } catch (error) {
    console.error("Error: ", error);
    throw error;
  }
};

export const createPlayback = async (data:PlaybackCreateRequest): Promise<Playback> => {
  try {
    const response: AxiosResponse<Playback> = await apiClient.post(`/api/playback`, data);
    return response.data;
  } catch (error) {
    console.error("Error: ", error);
    throw error;
  }
};

export const deletePlayback = async (id: string): Promise<void> => {
  try {
    await apiClient.delete(`/api/playback/${id}`);
  } catch (error) {
    console.error("Error: ", error);
    throw error;
  }
};

export const enablePlayback = async (id: string): Promise<void> => {
  try {
    await apiClient.patch(`/api/playback/${id}/enable`);
  } catch (error) {
    console.error("Error: ", error);
    throw error;
  }
};

export const disablePlayback = async (id: string): Promise<void> => {
  try {
    await apiClient.patch(`/api/playback/${id}/disable`);
  } catch (error) {
    console.error("Error: ", error);
    throw error;
  }
};
