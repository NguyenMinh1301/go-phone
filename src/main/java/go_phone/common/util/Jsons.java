package go_phone.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Jsons {

    private final ObjectMapper om;

    public String toJson(Object any) {
        try { return om.writeValueAsString(any); }
        catch (Exception e) { return null; }
    }

}
