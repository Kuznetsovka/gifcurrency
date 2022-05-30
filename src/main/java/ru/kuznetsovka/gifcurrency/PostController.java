package ru.kuznetsovka.gifcurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private MyService myService;

    @GetMapping(value = "/{currency}")
    public RedirectView getAllPosts(@PathVariable String currency) {
        String link = myService.difBetweenYesterdayAndTodayRate(currency);
//        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
//        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("redirectedUrl");
    }
}