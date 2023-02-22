
import axios from "axios";

class AccountService {
 
  openNewAccount(openAccount: any) {
    return axios.post<string>(
      `http://localhost:8081/api/account`, openAccount,
      {
        headers: {
          Accept: 'application/json',
        },
      }
    )
  }
}

export default new AccountService();