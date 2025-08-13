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
        public static final String ADD = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";

        private User() {
        }
    }

    public static final class Product {
        public static final String BASE = VERSION_V1 + "/product";
        public static final String GET_ALL = "/get";
        public static final String GET_ALL_PAGEABLE = "/page";
        public static final String SEARCH_PAGEABLE = "/search";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String ADD = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String SOFT_DELETE = "/delete/{id}";

        private Product() {
        }
    }

    public static final class Category {
        public static final String BASE = VERSION_V1 + "/category";
        public static final String BRAND = "/brand";
        public static final String COLOR = "/color";
        public static final String MADE_FROM = "/made-from";
        public static final String GET_ALL = "/get";
        public static final String GET_ALL_PAGEABLE = "/page";
        public static final String SEARCH_PAGEABLE = "/search";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String ADD = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String SOFT_DELETE = "/delete/{id}";

        private Category() {
        }
    }

    public static final class Health {
        public static final String HEALTH = "/health";
        public static final String HEALTH_DB = HEALTH + "/db";

        private Health() {
        }
    }

}
