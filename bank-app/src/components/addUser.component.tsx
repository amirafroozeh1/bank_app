import React, { useState } from 'react';
import UserService from "../services/user.service";

const AddNewUser = () => {
  const [inputValues, setInputValues] = useState<{ [x: string]: string }>()
  const [user, setUser] = useState([])

  const handleFormSubmit = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault()
    const data = {
      firstName: inputValues?.firstName,
      lastName: inputValues?.lastName,
      address: inputValues?.address,
      phoneNumber: inputValues?.phoneNumber
    }

    UserService.addUser(data)
      .then((response: any) => {
        setUser(response.data);
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
        <h1>Add new user</h1>
        <form className="form">
          <input 
            className="form-input"
            name="firstName" 
            value={inputValues?.firstName} 
            onChange={handleInputChange} 
            placeholder="First name"
          />
          <input 
            className="form-input"
            name="lastName" 
            value={inputValues?.lastName} 
            onChange={handleInputChange} 
            placeholder="Last name" 
          />
          <input 
            className="form-input" 
            name="phoneNumber"
            value={inputValues?.phoneNumber} 
            onChange={handleInputChange} 
            placeholder="Phone number" 
          />
          <input 
            className="form-input"
            name="address"
            value={inputValues?.address} 
            onChange={handleInputChange} 
            placeholder="Address" 
          />
          <button 
            className='form-submit'
            onClick={handleFormSubmit}
          >
            Submit
          </button>
        </form>
        
        <p>CustomerId: {user}</p>
        <p>After adding a new user, you will receive a customer id, you can use that to open a new account!</p>
      </div>
    </div>
  );
}

export default AddNewUser;
