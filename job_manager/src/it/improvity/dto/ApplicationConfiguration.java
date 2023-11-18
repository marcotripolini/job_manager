package it.improvity.dto;

public class ApplicationConfiguration {
	
	private String clientId;
	private String secret;
	private String environment;
	private String expressCheckoutUrl;
	private String bnCode;
	private String cancelUrl;
	private String returnUrl;

	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getExpressCheckoutUrl() {
		return expressCheckoutUrl;
	}
	public void setExpressCheckoutUrl(String expressCheckoutUrl) {
		this.expressCheckoutUrl = expressCheckoutUrl;
	}
	public String getBnCode() {
		return bnCode;
	}
	public void setBnCode(String bnCode) {
		this.bnCode = bnCode;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
}
