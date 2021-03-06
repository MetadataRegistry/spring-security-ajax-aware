package org.modelcatalogue.core.security.ajax;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private int statusForAjaxCalls = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.startsWith("application/json")) {
            response.sendError(statusForAjaxCalls, "Access Denied");
        } else {
            super.commence(request, response, authException);
        }
    }

    public int getStatusForAjaxCalls() {
        return statusForAjaxCalls;
    }

    public void setStatusForAjaxCalls(int statusForAjaxCalls) {
        this.statusForAjaxCalls = statusForAjaxCalls;
    }
}