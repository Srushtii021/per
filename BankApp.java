abstract class Account{
    String coustomerName;
    int accountNumber;
    String accountType;

    public Account( String coustomerName,int accountNumber,String accountType){
        this.coustomerName= coustomerName;
        this.accountNumber= accountNumber;
        this.accountType= accountType;
    }
    abstract void creditAmount(double amount);
    abstract void debitAmount(double amount);
    abstract void printBalance();
    abstract void computeInterest();
}
class SavAccount extends Account{
    double balance;

    public SavAccount(String coustomerName, int accountNumber,double balance){
        super(coustomerName,accountNumber,"Savings");
        this.balance=balance;
    }
@Override
void creditAmount(double amount){
    balance+=amount;
    System.out.println("Deposited to saving:"+amount);
}
@Override
void debitAmount(double amount){
    if(balance>=amount){
        balance-=amount;
        System.out.println("Withdrawn from savings:"+amount);
    }
    else{
        System.out.println("Insufficient funds in saving account.");
    }
}
@Override
void computeInterest(){
    double interest=balance*0.04;
    balance+=interest;
    System.out.println("Interest added to saving:"+interest);
}
@Override
void printBalance(){
    System.out.println("Savings Balance:"+balance);
}
}
class CurrAccount extends Account{
    double balance;
    double minBalance;
    double serviceCharge;

    public CurrAccount(String coustomerName,int accountNumber,double balance,double minBalance,double serviceCharge){
        super(coustomerName, accountNumber,"Current");
        this.balance=balance;
        this.minBalance=minBalance;
        this.serviceCharge=serviceCharge;
    }
    @Override
    void creditAmount(double amount){
        balance+=amount;
        System.out.println("Deposited to current:"+amount);
    }
    @Override
    void debitAmount(double amount){
        if(balance>=amount){
            balance-=amount;
            System.out.println("Withdrawn from current:"+amount);
        }
        else{
            System.out.println("Insufficient funds in current account.");
        }
        if(balance < minBalance){
            balance -= serviceCharge;
            System.out.println("Balance below minimum.Service charge imposed:"+serviceCharge);
        }
    }
@Override
void computeInterest(){
    System.out.println("No interest for Current account:");
}
@Override
void printBalance(){
    System.out.println("Current balance:"+balance);
}
}
public class BankApp{
    public static void main(String[] args) {
        Account[]accounts=new Account[2];
        accounts[0]=new SavAccount("Alice",1001,5000);
        accounts[1]=new CurrAccount("Bob",1002,3000,2000,1000);

        for(Account acc: accounts){
            System.out.println("\nAccount Type:"+acc.accountType);
            acc.printBalance();
            acc.creditAmount(1000);
            acc.debitAmount(2000);
            acc.computeInterest();
            acc.printBalance();

        }
    }
}
