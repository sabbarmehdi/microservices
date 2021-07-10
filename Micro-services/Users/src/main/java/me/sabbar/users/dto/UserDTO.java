package me.sabbar.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.sabbar.users.Model.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private ProfileRole roles;

    private String token;

}
