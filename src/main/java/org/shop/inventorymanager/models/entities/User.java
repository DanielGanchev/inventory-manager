package org.shop.inventorymanager.models.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
@Table(name = "users")
public class User extends BaseEntity {

  @NotBlank(message = "First name is required")
  @Length(min = 2, max = 15, message = "First name must be between 2 and 15 characters")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Length(min = 2, max = 15, message = "Last name must be between 2 and 15 characters")
  private String lastName;

  @NotNull(message = "Password is required")
  @Length(min = 6, message = "Password size must be minimum 6 characters")
  private String password;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @Builder.Default
  private boolean enabled = true;

  @Builder.Default
  private boolean locked = false;

  @Builder.Default
  private boolean accountExpired = false;

  @Builder.Default
  private boolean credentialsExpired = false;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Inventory inventory = new Inventory();


}
