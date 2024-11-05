package model.Purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginPageModel {

    public String email_user;
    public String password_user;
    public int int_quantity;

    public static LoginPageModel LoginVehicularformModel(Map<String, String> entry) {
        LoginPageModel inPutData = new LoginPageModel();
        inPutData.setEmail_user(entry.get("Email"));
        inPutData.setPassword_user(entry.get("Password"));
        return inPutData;
    }
    
}
