package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 22/5/19.
 */
public class LoginResponse {
    private byte[] token;
    private String userType;
    private int userId;

    public LoginResponse(byte[] token, String userType, int id) {
        this.token = token;
        this.userType = userType;
        this.userId = id;
    }

    public byte[] getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }
}
