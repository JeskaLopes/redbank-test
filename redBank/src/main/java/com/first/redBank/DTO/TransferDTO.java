package com.first.redBank.DTO;

public class TransferDTO {
	private Long idOriginAccount;
    private Long idDestinyAccount;
    private double value;
	public Long getIdOriginAccount() {
		return idOriginAccount;
	}
	public void setIdOriginAccount(Long idOriginAccount) {
		this.idOriginAccount = idOriginAccount;
	}
	public Long getIdDestinyAccount() {
		return idDestinyAccount;
	}
	public void setIdDestinyAccount(Long idDestinyAccount) {
		this.idDestinyAccount = idDestinyAccount;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
