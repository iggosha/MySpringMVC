package com.sportline.exc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalAnyExceptionHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String requestError(Model model, HttpServletRequest request) {
        model.addAttribute("esc", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        model.addAttribute("ee", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        model.addAttribute("em", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        model.addAttribute("eru", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        model.addAttribute("eet", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
        model.addAttribute("esn", request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME));

        return "sportline/basic/exc";
    }
}
