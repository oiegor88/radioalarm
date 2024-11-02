import axios from "axios";

// TODO: add app config
const baseUrl = 'http://localhost:7070';
const headers = {
  'Content-Type': 'application/json',
};

// TODO: add high-level abstraction for request/response typing, headers customization, etc.
export const apiClient = axios.create({
  baseURL: baseUrl,
  headers: headers
});
