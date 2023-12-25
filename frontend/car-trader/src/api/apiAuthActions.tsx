import axios from "axios";

const HEADERS = {
    headers: {
      "Content-Type": "application/json",
    },
  };

const apiAuthUrl = "http://localhost:8080/api/auth";

const apiClient = axios.create();

export const apiRegistrationRequest = async (params: any = {}) => {
  try {
    return await apiClient.post(`${apiAuthUrl}/registration`, params, HEADERS);
  } catch (e: any) {
    if (axios.isAxiosError(e)) {
      return e.response?.data;
    }
    return e;
  }
};
