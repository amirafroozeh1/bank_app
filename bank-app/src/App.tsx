import { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import UserComponent from "./components/user.component";
import TransactionComponent from "./components/transaction.component";
import AddUserComponent from "./components/addUser.component";
import OpenAccountComponent from "./components/openAccount.component";


class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/user"} className="navbar-brand">
            Bank 
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/user"} className="nav-link">
                User Info
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/transaction"} className="nav-link">
                Transaction Info
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add-user"} className="nav-link">
                Add User
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/open-account"} className="nav-link">
                Open Account
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/user"]} component={UserComponent} />
            <Route exact path="/transaction" component={TransactionComponent} />
            <Route exact path="/add-user" component={AddUserComponent} />
            <Route exact path="/open-account" component={OpenAccountComponent} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
