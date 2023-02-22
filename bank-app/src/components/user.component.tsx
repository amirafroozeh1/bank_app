import { Component, ChangeEvent } from "react";
import UserService from "../services/user.service";
import IUser from '../types/user.type';

type Props = {};

type State = {
  user: IUser | null,
  searchCustomerId: string
};

export default class UserComponent extends Component<Props, State>{
  constructor(props: Props) {
    super(props);
    this.onChangeSearch = this.onChangeSearch.bind(this);
    this.searchUser = this.searchUser.bind(this);

    this.state = {
      user: null,
      searchCustomerId: ""
    };
  }

  onChangeSearch(e: ChangeEvent<HTMLInputElement>) {
    this.setState({
      searchCustomerId: e.target.value
    });
  }

  searchUser() {
    UserService.findUserById(this.state.searchCustomerId)
      .then((response: any) => {
        this.setState({
          user: response.data
        });
      })
      .catch((e: Error) => {
        console.log(e);
      });
  }

  render() {
    const { searchCustomerId, user } = this.state;

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
                onClick={this.searchUser}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-xl-12">
          <h4>User Info</h4>

          <ul className="list-group">
                <li>
                    First Name: {user?.firstName}
                </li>
                <li>
                    Last Name: {user?.lastName}
                </li>
                <li>
                    Address: {user?.address}
                </li>
                <li>
                    Phone Number: {user?.phoneNumber}
                </li> 
          </ul>
          <ul className="padding-left: 3px;">
              {user?.accounts.map(data => (
              <li key={data.accountId}>
                <div>Account Id:  {data.accountId}</div>
                <div>Balance:  {data.balance}</div>
              </li>
              ))}
          </ul>
        </div>
      </div>
    );
  }
}
