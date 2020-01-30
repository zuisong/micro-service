package demo;

import lombok.*;

/**
 * @author Chen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
  private String name;
  private String password;
}
