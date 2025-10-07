package it.personalproject.auth.domain;

public class LoginResponse {
	
	private final String accessToken;
	private final String refreshToken;
	private String tokenType = "Bearer";
	
	
	
	public LoginResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public LoginResponse(String accessToken, String refreshToken, String tokenType) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenType = tokenType;
	}



	public String getTokenType() {
		return tokenType;
	}



	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}



	public String getAccessToken() {
		return accessToken;
	}



	public String getRefreshToken() {
		return refreshToken;
	}
	
	
	
	
}
