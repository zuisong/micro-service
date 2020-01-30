package demo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String name;
  private String password;
}
