import axios from "axios";
import Cookies from 'universal-cookie';

const HEADERS = {
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
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


class LoginResponse {
  token: string;
  type: string;
  id: number;
  email: string;
  roles: string[];
  status: number;

  constructor(data: LoginResponse, status: number){
    this.token = data.token;
    this.type = data.type;
    this.id = data.id;
    this.email = data.email;
    this.roles = data.roles;
    this.status = status;
  }
}

const cookies = new Cookies(null, { path: '/', secure: true });

export const apiLoginRequest = async (params: any = {}): Promise<LoginResponse> => {
  try {
    
      const {data, status} = await apiClient.post(`${apiAuthUrl}/login`, params, HEADERS)

      if (status === 200) {
        const token = data.token;
        cookies.set("Authorization", token);
      }

      return new LoginResponse(data, status);

  } catch (e: any) {
    if (axios.isAxiosError(e)) {
      return e.response?.data;
    }
    return e;
  }
};
