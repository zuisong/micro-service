package demo;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;

@Data
@Component
@ConfigurationProperties("my-config")
public class MyConfig {

  private String myName;
}
