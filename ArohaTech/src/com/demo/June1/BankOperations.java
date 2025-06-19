package com.demo.June1;

public class BankOperations {

	//@AuditLog
	private String name = "Shankar";
	public int age = 30;
    @AuditLog
    public void transferMoney(String fromAcc, String toAcc, double amount) {
        System.out.println("Transferring Rs. " + amount + " from " + fromAcc + " to " + toAcc);
    }

    //@AuditLog
    public void checkBalance(String accountNo) {
        System.out.println("Checking balance for account: " + accountNo);
    }

    public static void main(String[] args) throws Exception {
        BankOperations bank = new BankOperations();

        for (var method : BankOperations.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AuditLog.class)) {
                System.out.println("NOTE::: method call happend Operation `" + method.getName() + "` needs to be logged.");
            }
        }

        bank.transferMoney("12345", "54321", 5000);
        bank.checkBalance("12345");
    }
}
