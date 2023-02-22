import IUser from "../types/user.type"
import INewUser from "../types/newUser.type"

import axios from "axios";

class UserService {
 
  findUserById(customerId: string) {
    return axios.get<IUser>(
      `http://localhost:8081/api/user/${customerId}`,
      {
        headers: {
          Accept: 'application/json',
        },
      }
    )
  }

  addUser(newUser: INewUser) {
    return axios.post<string>(
      `http://localhost:8081/api/user`, newUser,
      {
        headers: {
          Accept: 'application/json',
        },
      }
    )
  }
}


export default new UserService();