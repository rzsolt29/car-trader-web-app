import axios from "axios";
import Cookies from 'universal-cookie';

const cookies = new Cookies();
//console.log(cookies.get("Authorization"));

const HEADERS = {
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": "Bearer " + cookies.get("Authorization")
    },
  };

const apiCarUrl = "http://localhost:8080/api/cars";

const apiClient = axios.create();

export const apiCreateCarRequest = async (params: any = {}) => {
  try {
    return await apiClient.post(`${apiCarUrl}/add-car`, params, HEADERS);
  } catch (e: any) {
    if (axios.isAxiosError(e)) {
      return e.response?.data;
    }
    return e;
  }
};



/*class GetUsersCarsResponse {
  cars: {
    id: number,
    publishedOn: any,
    make: string,
    model: string,
    price: number
}[]

  constructor(data: LoginResponse, status: number){
    this.token = data.token;
    this.type = data.type;
    this.id = data.id;
    this.email = data.email;
    this.roles = data.roles;
    this.status = status;
  }
}*/
interface Car {
  id: number;
  publishedOn: any;
  make: string;
  model: string;
  price: number;
}
interface CarListProps {
  items: Car[];
};

export const apiGetUsersCarsRequest = async (params: any = {}) => {
  try {
    /*const {data, status} =*/ return await apiClient.get(`${apiCarUrl}/my-cars`, HEADERS);
    //return data;
  } catch (e: any) {
    if (axios.isAxiosError(e)) {
      return e.response?.data;
    }

    return e;
  }
};
