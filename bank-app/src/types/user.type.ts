export default interface IUser {
  customerId: string,
  firstName: string,
  lastName: string,
  address: string,
  phoneNumber: string,
  accounts :  IAccount[]
}

interface IAccount {
  accountId: string,
  balance: number
}