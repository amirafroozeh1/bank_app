import ITransaction from "../types/transaction.type";
import axios from "axios";

class TransactionService {
 
    findTransactionsByCustomerId(customerId: string) {
      return axios.get<ITransaction>(
        `http://localhost:8082/api/transaction/${customerId}`,
        {
          headers: {
            Accept: 'application/json',
          },
        }
      )
    }
  }

export default new TransactionService();