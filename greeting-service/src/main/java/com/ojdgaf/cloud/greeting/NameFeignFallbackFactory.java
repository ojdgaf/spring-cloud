package com.ojdgaf.cloud.greeting;

import lombok.extern.java.Log;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Log
@Component
public class NameFeignFallbackFactory implements FallbackFactory<NameFeignClient> {

    @Override
    public NameFeignClient create(final Throwable cause) {
        log.warning("Fallback executed");
        return () -> "Fallback (caused by " + cause.getMessage() + ")";
    }
}
