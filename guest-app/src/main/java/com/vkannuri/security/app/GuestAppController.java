package com.vkannuri.security.app;

import com.vkannuri.security.app.domain.Guest;
import com.vkannuri.security.app.domain.GuestDto;
import com.vkannuri.security.app.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** @author Venu Kannuri . */
@Controller
@RequestMapping("/")
public class GuestAppController {

    private final GuestService guestService;

    public GuestAppController(GuestService guestService) {
        super();
        this.guestService = guestService;
    }

    @GetMapping(value = {"/", "/index"})
    public String getHomePage(Model model) {

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/guests")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuests(Model model) {
        List<Guest> guests = this.guestService.getAllGuest();
        model.addAttribute("guests", guests);
        return "guests-view";
    }

    @GetMapping(value = "/guests/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGuestForm(Model model) {
        return "guest-view";
    }

    @PostMapping(value = "/guests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGuest(
            HttpServletRequest request, Model model, @ModelAttribute GuestDto guestDto) {
        Guest guest = this.guestService.addGuest(guestDto);
        model.addAttribute("guest", guest);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/guests/" + guest.getId());
    }

    @GetMapping(value = "/guests/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuest(Model model, @PathVariable long id) {
        Guest guest = this.guestService.getGuest(id);
        model.addAttribute("guest", guest);
        return "guest-view";
    }

    @PostMapping(value = "/guests/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateGuest(
            Model model, @PathVariable long id, @ModelAttribute GuestDto guestDto) {
        Guest guest = this.guestService.updateGuest(id, guestDto);
        model.addAttribute("guest", guest);
        model.addAttribute("guestModel", new GuestDto());
        return "guest-view";
    }
}
