import { apiAuth} from "../api/apiActions2";

export const authUser = (userEmail: string, userPassword: string) => {
  const userData = {
    email: userEmail,
    password: userPassword,
  };
  return apiAuth(userData);
};
