
/**
 * This class represents a bank account.  The account may be a current account or
 * a savings account.  Current accounts must maintain a minimum balance of GHC 6.00,
 * while savings accounts must maintain a minimum balance of 30.00.
 * Current accounts have a monthly maintenance fee of GHC 2.00, whereas savings
 * accounts have no monthly maintenance fee.  Savings accounts have an interest rate
 * of 6%, but current accounts have no interest.  Lastly, withdrawals from savings
 * accounts may only be made 2 times a month, whereas there is no limit
 * on withdrawals from current accounts.
 */
public class BankAccount {
    // Defined enumeration specifying the valid types of accounts
    public enum AccountType {CURRENT, SAVINGS}

    private AccountType acctType;   // The type of account (CURRENT or SAVINGS)
    private String acctID;          // The account's identifier

    private double balance;         // The current balance on the account
    private int numWithdrawals;     // The number of withdrawals that have been made
    // in the current month.
    private boolean inTheRed;       // Whether or not this account is "in the red"
    // (i.e. whether its balance is less than the
    //  minimum required balance.)

    private double minBalance;      // This account's minimum balance requirement
    private double interestRate;    // This account's annual interest rate
    private double maintenanceFee;  // This account's maintenance fee
    private int withdrawalLimit;    // The maximum number of withdrawals per month.

    // Defined constants
    public static final int MONTHS_PER_YEAR = 12;
    public static final double SAVINGS_ACCT_MIN_BALANCE = 30.0;
    public static final double CURRENT_ACCT_MIN_BALANCE = 6.00;
    public static final double SAVINGS_ACCT_INTEREST_RATE = 0.06;
    public static final double CURRENT_ACCT_MAINTENANCE_FEE = 2.00;
    public static final int SAVINGS_WITHDRAWAL_LIMIT = 2;

    // A constructor that takes the account type and id
    public BankAccount(AccountType type, String id) {
        acctType = type;
        acctID = id;
        balance = 0;
        numWithdrawals = 0;

        if (acctType == AccountType.CURRENT) {
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }

        inTheRed = (balance < minBalance);
    }

    // TO DO: Define a constructor that takes the account type, id,
    // and opening balance
    public BankAccount(AccountType acctType, String ID, double balance) {
        this(acctType,ID);
        this.balance = balance;

        if (acctType == AccountType.CURRENT) {
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }

        inTheRed = (balance < minBalance);
    }
    // TO DO: Define the getBalance() method

    public double getBalance() {
        return balance;
    }

    // TO DO: Define the getAccountType() method
    public AccountType getAcctType() {
        return acctType;
    }


    public String getAcctID() {
        return acctID;
    }

    public double getMinBalance() {
        return minBalance;
    }

    /**

     This method withdraws a given amount from the account balance if the conditions are met
     @param amount the amount to withdraw
     @return true if the withdrawal is successful, false otherwise
     */
    public boolean withdraw(double amount){
        // Checks if the amount to withdraw is less than 0
        if(amount < 0){
            System.out.println("Please enter a number greater than 0");
            return false;
        }
        // Checks if the account balance is already in the red
        else if (inTheRed){
            System.out.println("The account balance is in the red");
            return false;
        }
        // Checks if the account has reached the maximum withdrawals allowed for the month
        else if(numWithdrawals==withdrawalLimit){
            System.out.println("This account has reached it's maximum withdrawals for the month");
            return false;
        }
        // Checks if the amount to withdraw is greater than the account balance
        else if(amount > balance){
            System.out.println("The account balance is insufficient");
            return false;
        }
        // Checks if withdrawing the amount would put the account in the red zone
        else{
            if ((balance-amount)<minBalance){
                System.out.println("Transaction unsuccessful. Withdraw less to stay out of the red zone");
                return false;
            }
        // Withdraws the amount and updates the number of withdrawals if the withdrawal is successful
            else{
                balance-=amount;
                if (acctType == AccountType.SAVINGS) numWithdrawals++;
                System.out.println("Transaction successful\n GHS" + amount +" withdrawn");
                return true;
            }
        }
    }

    /**
     * This method deposits a given amount from the Bank account if the conditions are met
     * @param amount to deposit
     */
    public void deposit(double amount){
        // check if amount is greater than 0 and updates the balance
        if (amount < 0){
            System.out.println("You can only deposit an amount greater than 0");
            return;
        }
        balance+=amount;
        System.out.println("Congratulations! Successfully deposited GHS "+ amount);
    }

    /**
     *This method performs the monthly maintenance process on the account
     */
    public void performMonthlyMaintenance(){
        balance+=(interestRate/12)*balance;  //updates the balance by the interest rate
        balance-=maintenanceFee; //updates the balance by the maintenance fee
        //display earned interests, maintenance fee, updated balance to the user
        //and whether the account is in the red or not
        System.out.println("Earned interest: GHS " +(interestRate/12)*balance);
        System.out.println("Maintenance fee: GHS " +maintenanceFee);
        System.out.println("Updated balance: GHS " +balance);
        if (inTheRed){
            System.out.println("WARNING: THIS ACCOUNT IS IN THE RED");
        }
    }

    /**
     * This method transfers money from one bank account to the other if all the conditions are met
     * @param transferTo determines whether there is a deposit or withdrawal
     * @param otherAccount the other bank account that the transfer is going wo occur between
     * @param amount the amount that is to be transferred
     * @return true if the transaction was successful
     */
    public boolean transfer(boolean transferTo,BankAccount otherAccount,double amount){
        //checks if the amount entered is not a negative number
        if (amount < 0){
            System.out.println("Amount should be greater than 0");
            return false;
        //transfers money to the other account and displays either an error message or
        //success message depending on whether the conditions are met
        }else if(transferTo==true){
            if(inTheRed){
                System.out.println("Your account is in the red. Transfer unsuccessful");
                return false;
            }else{
                withdraw(amount);
                otherAccount.deposit(amount);
                return true;
            }
        //transfers money from the other account and displays either an error message or
        // success message depending on whether the conditions are met
        }else{
            if(otherAccount.inTheRed){
                System.out.println("The other account is in the red. Transfer unsuccessful");
                return false;
            }else{
                otherAccount.withdraw(amount);
                deposit(amount);
                return true;
            }
        }

    }


}