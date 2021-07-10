package com.peaqock.users.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente le bean qui va sérializer une erreur avec son code et son message.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestError {

    private String code;
    private String message;
}
