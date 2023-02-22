import { Component, ChangeEvent } from "react";
import TransactionService from "../services/transaction.service";
import ITransaction from "../types/transaction.type";

type Props = {};

type State = {
  transactions: ITransaction[] ,
  searchCustomerId: string
};

export default class TransactionComponent extends Component<Props, State>{
  constructor(props: Props) {
    super(props);
    this.onChangeSearch = this.onChangeSearch.bind(this);
    this.searchTransaction = this.searchTransaction.bind(this);

    this.state = {
      transactions: [],
      searchCustomerId: ""
    };
  }

  onChangeSearch(e: ChangeEvent<HTMLInputElement>) {    
    this.setState({
      searchCustomerId: e.target.value
    });
  }

  searchTransaction() {
    TransactionService.findTransactionsByCustomerId(this.state.searchCustomerId)
      .then((response: any) => {
        this.setState({
          transactions: response.data
        });
      })
      .catch((e: Error) => {
        console.log(e);
      });
  }

  render() {
    const { searchCustomerId, transactions } = this.state;

    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by customer id"
              value={searchCustomerId}
              onChange={this.onChangeSearch}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchTransaction}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-xl-12">
          <h4>Transactions Info</h4>
          <ul className="padding-left: 3px;">
              {transactions.map(transaction => (
                <li key={transaction.transactionId}>
                    <div>Transaction Id: {transaction.transactionId}</div>
                    <div>Customer Id: {transaction.customerId}</div>
                    <div>Credit Account Id: {transaction.creditAccountId}</div>
                    <div>Debit Account Id: {transaction.debitAccountId}</div>
                    <div>Amount: {transaction.amount}</div>
                    <div>Description: {transaction.description}</div>
                </li>
              ))}
          </ul>
        </div>
      </div>
    );
  }
}
