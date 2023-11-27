package com.osypenko.controller.template.admin;

import com.osypenko.services.admin.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.DATA_LOG_FILES;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LogController {
    private final HttpSession session;
    private final AdminService adminService;

    @GetMapping(LOG)
    public String filesLog(@RequestParam(name = "nameFile") String file) {
        String content = adminService.getDataLogsFile(file);
        session.setAttribute(DATA_LOG_FILES, content);
        return DIRECTORY_ADMIN + LOG;
    }
}
