package info.joninousiainen.postcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
    @RequestMapping("/")
    public String redirectRootToVaadinUI() {
        return "redirect:/vaadin/";
    }
}
