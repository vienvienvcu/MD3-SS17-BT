package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.User;
import ra.model.service.IUserService;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    @Autowired
    private IUserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // Trang chính
    @GetMapping("/")
    public String home() {
        return "index";
    }
    // Trang đăng ký
    @GetMapping("/register")
    public String register() {
        return "register";  // Trang đăng ký
    }

    // Đăng ký người dùng
    @PostMapping("/register")
    public String handleRegister(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password,
                                 @RequestParam(value = "roleId", required = false) Integer roleId) {
        if (roleId == null) {
            roleId = 2; // Mặc định là 2 (user)
        }

        if ("admin123".equals(userName)) {
            roleId = 1; // Admin
        }

        String encodedPassword = passwordEncoder.encode(password);  // Mã hóa mật khẩu
        System.out.println("Encoded password: " + encodedPassword);  // In mật khẩu mã hóa
        userService.registerUser(userName, encodedPassword, roleId);
        return "redirect:/login";  // Chuyển hướng đến trang đăng nhập
    }

    // Trang đăng nhập
    @GetMapping("/login")
    public String login() {
        return "login";  // Trang đăng nhập
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String handleLogin(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              HttpSession session) {
        User user = userService.findByUserName(userName);

        if (user != null) {
            // Giải mã mật khẩu
            boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());

            if (passwordMatches) {
                // Đặt người dùng vào phiên
                session.setAttribute("user", user);

                // Điều hướng dựa trên roleId
                if (user.getRoleId() == 1) {
                    return "admin/index";  // Trang cho ADMIN
                } else if (user.getRoleId() == 2) {
                    return "index";   // Trang cho home chinh
                } else {
                    return "error";  // Trang lỗi hoặc trang mặc định nếu roleId không hợp lệ
                }
            } else {
                // Nếu mật khẩu không khớp
                return "login";  // Trang lỗi đăng nhập hoặc trang chính
            }
        } else {
            // Nếu người dùng không tồn tại
            return "index";  // Trang lỗi đăng nhập hoặc trang chính
        }
    }


    // Trang chính của admin
    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    // Trang chính của người dùng
    @GetMapping("/user")
    public String user() {
        return "user/index";
    }

}
