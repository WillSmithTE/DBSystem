package ses1grp6.dbsystemandroid.network;

public enum MethodType {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private final String name;

    MethodType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
