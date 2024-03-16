package org.shop.inventorymanager.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDto {

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotNull(message = "Password is required")
  @Size(min = 6, message = "Password size must be minimum 6 characters")
  private String password;

  @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters")
  private String lastName;











}
