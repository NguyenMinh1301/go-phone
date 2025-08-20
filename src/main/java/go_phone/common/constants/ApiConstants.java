package go_phone.common.constants;

public class ApiConstants {
    private ApiConstants() {
    }

    public static final String BASE_API = "/api";
    public static final String VERSION_V1 = BASE_API + "/v1";

    public static final class Auth {
        public static final String BASE = VERSION_V1 + "/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String INTROSPECT = "/introspect";
        public static final String FORGOT_REQUEST = "/forgot/request";
        public static final String FORGOT_RESET = "/forgot/reset";

        private Auth() {
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

    public static final class Cart {
        public static final String BASE = VERSION_V1 + "/cart";
        public static final String ITEMS = "/items";
        public static final String GET = "/get";
        public static final String CLEAR = "/clear";
        private Cart() {}
    }

    public static final class Order {
        public static final String BASE = VERSION_V1 + "/orders";
        public static final String CREATE = "/create";
        public static final String GET_BY_CODE = "/get/{orderCode}";
        private Order() {}
    }

    public static final class Payment {
        public static final String BASE = VERSION_V1 + "/payments/payos";
        public static final String CREATE = "/create";
        public static final String WEBHOOK = "/webhooks/payos"; // or use app.payment.webhook-path
        private Payment() {}
    }

}
