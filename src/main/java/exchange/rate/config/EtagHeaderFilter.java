package exchange.rate.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagHeaderFilter {

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> registrationBean =
                new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CurrencyAPIFilter");
        return registrationBean;
    }
}
