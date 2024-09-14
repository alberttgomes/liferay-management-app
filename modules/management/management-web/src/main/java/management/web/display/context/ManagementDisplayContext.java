package management.web.display.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Albert Cabral
 */
public class ManagementDisplayContext {

    public ManagementDisplayContext(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        this._httpServletRequest = httpServletRequest;
        this._httpServletResponse = httpServletResponse;
    }

    private final HttpServletRequest _httpServletRequest;
    private final HttpServletResponse _httpServletResponse;

}
