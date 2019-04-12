package com.example.ceshidemo.cc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lxy
 * @Date: 2019/3/11 15:42
 */

@RestController
public class CeController {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "qwe";
    }

    @PreAuthorize("hasAuthority('sss')")
    @GetMapping(value = "/test1")
    public String test1() {
        return "sss";
    }
}
