package com.demo.aroha.day9.ShopingCart;

public class ItemTransaction {
	private int transactionId;
	private int itemId;
	private int quantityOfpurchase;
	private double billAmout;
	public ItemTransaction(int transactionId, int itemId, int quantityOfpurchase, double billAmout) {
		super();
		this.transactionId = transactionId;
		this.itemId = itemId;
		this.quantityOfpurchase = quantityOfpurchase;
		this.billAmout = billAmout;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantityOfpurchase() {
		return quantityOfpurchase;
	}
	public void setQuantityOfpurchase(int quantityOfpurchase) {
		this.quantityOfpurchase = quantityOfpurchase;
	}
	public double getBillAmout() {
		return billAmout;
	}
	public void setBillAmout(double billAmout) {
		this.billAmout = billAmout;
	}
	@Override
	public String toString() {
		return "ItemTransaction [transactionId=" + transactionId + ", itemId=" + itemId + ", quantityOfpurchase="
				+ quantityOfpurchase + ", billAmout=" + billAmout + "]";
	}
	

}
