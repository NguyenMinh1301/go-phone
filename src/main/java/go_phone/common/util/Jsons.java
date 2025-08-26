package go_phone.common.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Jsons {

    private final ObjectMapper om;

    public String toJson(Object any) {
        try {
            return om.writeValueAsString(any);
        } catch (Exception e) {
            return null;
        }
    }
}
