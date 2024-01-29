package com.first.redBank.DTO;

public class TransferResponseDTO {
    private String status;
    private Long idOriginAccount;
    private Long idDestinyAccount;
    private String errorMessage;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
