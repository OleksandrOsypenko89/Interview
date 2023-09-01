package com.osypenko.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping("/adminpage")
    public String getAdminPage() {
        return "admin/adminpage";
    }
}
