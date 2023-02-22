import React, { useState } from 'react';
import AccountService from "../services/account.service";

const OpenNewAccount = () => {
  const [inputValues, setInputValues] = useState<{ [x: string]: string }>()

  const handleFormSubmit = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault()
    const data = {
      customerId: inputValues?.customerId,
      initialCredit: inputValues?.initialCredit
    }

    AccountService.openNewAccount(data)
    .then((response: any) => {
      console.log(response.data);
    })
    .catch((error: Error) => {
      console.log(error);
    });
  }

  const handleInputChange = (e: React.FormEvent<HTMLInputElement>) => {
    const { name, value } = e.currentTarget
    setInputValues(prevState => ({ ...prevState, [name]: value }))
  }

  return (
    <div className="Form">
      <div className="form-wrapper">
        <h1>Open New Account</h1>
        <form className="form">
          <input 
            className="form-input"
            name="customerId" 
            value={inputValues?.customerId} 
            onChange={handleInputChange} 
            placeholder="Customer Id"
          />
          <input 
            className="form-input"
            name="initialCredit" 
            value={inputValues?.initialCredit} 
            onChange={handleInputChange} 
            placeholder="Initial Credit" 
          />
          <button 
            className='form-submit'
            data-testid="form-submit" 
            onClick={handleFormSubmit}
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default OpenNewAccount;
