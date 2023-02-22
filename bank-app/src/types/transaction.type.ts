export default interface ITransaction {
  transactionId: string,
  customerId: string,
  creditAccountId: string,
  debitAccountId: string,
  amount: number,
  description : string
}
