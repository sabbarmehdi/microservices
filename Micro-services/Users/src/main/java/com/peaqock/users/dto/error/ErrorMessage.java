package com.peaqock.users.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente le bean qui permet de sérializer un message d'erreur.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private RestError error;

}
