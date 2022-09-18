package infoco.immo.security;

import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Authentication;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.database.SQL.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.util.Objects;


@Slf4j
@Component
public class BearerAuthentication implements Filter {
    static final String FORBIDDEN = "forbidden, add a bearer token";

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AuthenticationRepository authenticationRepository;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String headers = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authenticationRepository == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            authenticationRepository = webApplicationContext.getBean(AuthenticationRepository.class);

        }


        if (headers == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), FORBIDDEN);
            return;
        }

        Authentication userTokenData;
        String token;
        try {
            token = headers.split(" ")[1];
            userTokenData = authenticationRepository.getByToken(token);
            token = userTokenData.getToken();
        } catch (Exception arrayException) {
            log.info(arrayException.getMessage());
            response.sendError(HttpStatus.FORBIDDEN.value(), FORBIDDEN);
            return;
        }

        if (token.isEmpty()) {
            response.sendError(HttpStatus.FORBIDDEN.value(), FORBIDDEN);
            return;
        }

        if(!GenerateAuth.decode(token, userTokenData.getHash())){
            log.info("problem with auth token");
            response.sendError(HttpStatus.FORBIDDEN.value(), "forbidden, token is malformed, login again");
            return;
        }
        if (!Objects.equals(token, userTokenData.getToken())) {
            log.info("token not here");
            response.sendError(HttpStatus.FORBIDDEN.value(), "token is not in here");
            return;
        }
        response.setHeader(HttpHeaders.AUTHORIZATION, headers);

        filterChain.doFilter(request, response);

    }

}
