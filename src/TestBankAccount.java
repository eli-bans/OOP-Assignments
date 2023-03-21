
/**
 * This class tests the BankAccount class.
 * You will need to add to it incrementally as you implement
 * the methods of the BankAccount class.
 */
public class TestBankAccount {
    public static void main(String[] args) {
        BankAccount myCurrentAccount, mySavingsAccount, yourCurrentAccount, yourSavingsAccount;

        myCurrentAccount = new BankAccount(BankAccount.AccountType.CURRENT,
                "003456CURRENT");
        mySavingsAccount = new BankAccount(BankAccount.AccountType.SAVINGS,
                "003456SAVINGS");
        yourCurrentAccount = new BankAccount(BankAccount.AccountType.CURRENT,
                "003457CURRENT");
        yourSavingsAccount = new BankAccount(BankAccount.AccountType.SAVINGS, "003457SAVINGS", 500);

        // Add various method calls to test you bank account class as you
        // implement it.
        System.out.println("This program doesn't really do anything yet.  You have to add to it.");

        //testing the getBalance method
        System.out.println("My current account balance is " + myCurrentAccount.getBalance());

        //testing the getAccountType method
        System.out.println("My current account type is " + mySavingsAccount.getAcctType());

        //testing the getAccountID method
        System.out.println("My current account ID is " +mySavingsAccount.getAcctID() );

        //testing the getMinBalance method
        System.out.println("The minimum balance for a savings account is "+ mySavingsAccount.getMinBalance());

        //withdraw money from red account
        myCurrentAccount.withdraw(50);

        //attempt to withdraw negative amount
        yourSavingsAccount.withdraw(-2);

        //withdraw more money than in account
        yourSavingsAccount.withdraw(1000);

        //withdraw amount within balance in account
        yourSavingsAccount.withdraw(200);

        //deposit amount
        myCurrentAccount.deposit(240);

        //attempt to deposit a negative number
        myCurrentAccount.deposit(-2);

       //perform monthly maintenace
        yourSavingsAccount.performMonthlyMaintenance();

        //transfer money from current account to savings account
        yourSavingsAccount.transfer(true, mySavingsAccount,100);

        //transfer money from an empty account to savings account
        myCurrentAccount.transfer(true,mySavingsAccount, 50);

        //receive money from my savings account that is in the red
        myCurrentAccount.transfer(false, mySavingsAccount,10.5);

        //receive money from your savings account
        myCurrentAccount.transfer(false,yourSavingsAccount,10.5);

    }
}
