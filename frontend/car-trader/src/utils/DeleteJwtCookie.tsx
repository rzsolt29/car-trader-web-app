import Cookies from 'universal-cookie';
import { useNavigate } from "react-router-dom";

const cookies = new Cookies();

export const DeleteJwtCookie = () => {
    cookies.remove("Authorization");
    return null;
}