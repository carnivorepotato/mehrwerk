package org.testtask.model.dto;

import java.util.List;

public record LoginResponse(
        String access_token,
        long expires_in,
        String token_type,
        List<String> scope) {
}
