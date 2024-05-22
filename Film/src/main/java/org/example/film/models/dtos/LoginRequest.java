package org.example.film.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.film.commons.cqrs.IRequest;
import org.example.film.models.entities.Account;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest implements IRequest<Account> {
    @NotBlank(message = "The Email is not null.")
    @Email(message = "Email is not valid.")
    private String email;

    @NotBlank(message = "The Password is not null.")
    @Size(min = 8, message = "Minimum length is 8.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")  //It nhat 1 chu cai in thuong(hoa), 1 so, 1 ki tu dac biet, do dai it nhat 8 ki tu
    private String password;
}
