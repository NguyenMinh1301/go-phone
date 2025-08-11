package go_phone.common.constants;

public class ApiConstants {
    private ApiConstants() {
    }

    public static final String BASE_API = "/api";
    public static final String VERSION_V1 = BASE_API + "/v1";

    public static final class User {
        public static final String BASE = VERSION_V1 + "/user";
        public static final String GET_ALL = "/get";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String ADD = "/add";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";

        private User() {
        }
    }

    public static final class Product {
        public static final String BASE = VERSION_V1 + "/product";
        public static final String GET_ALL = "/get";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String ADD = "/add";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";

        private Product() {
        }
    }

    public static final class Health {
        public static final String HEALTH = "/health";
        public static final String HEALTH_DB = HEALTH + "/db";

        private Health() {
        }
    }

}
