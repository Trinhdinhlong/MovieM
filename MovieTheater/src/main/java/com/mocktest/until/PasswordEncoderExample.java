package com.mocktest.until;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderExample {
    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkpw(String rawPassword, String hashedPasswordFromDatabase) {
         return BCrypt.checkpw(rawPassword, hashedPasswordFromDatabase);
    }
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
