package ra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Configuration

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};// Cấu hình ứng dụng chính
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Nếu bạn có cấu hình riêng cho DispatcherServlet, hãy thêm vào đây
        return new Class[]{AppConfig.class}; // Nếu cần cấu hình cho DispatcherServlet
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
