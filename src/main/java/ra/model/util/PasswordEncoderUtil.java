package ra.model.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Mã hóa mật khẩu khi đăng ký
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$P8lvP1RB1nnyClqgcl4EduiQoWyMSL6gLcaEIZoKEYTClUMcipigm"; // Mật khẩu đã mã hóa từ cơ sở dữ liệu
        System.out.println("Encoded password from DB: " + encodedPassword);

        // So sánh mật khẩu khi đăng nhập
        String rawPasswordInput = "123456";
        boolean matches = passwordEncoder.matches(rawPasswordInput, encodedPassword);
        System.out.println("Password matches: " + matches);
    }
}
