package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 22/5/19.
 */
public class LoginResponse {
    private byte[] token;
    private String userType;

    public LoginResponse(byte[] token, String userType) {
        this.token = token;
        this.userType = userType;
    }

    public byte[] getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }
}
