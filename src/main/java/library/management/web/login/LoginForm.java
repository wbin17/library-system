package library.management.web.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class LoginForm {
    private String memberId;
    private String memberPass;
}
