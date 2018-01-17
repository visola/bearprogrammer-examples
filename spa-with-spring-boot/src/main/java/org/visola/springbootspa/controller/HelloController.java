package org.visola.springbootspa.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${api.base.path}/messages")
@RestController
public class HelloController {

    @ResponseBody
    @RequestMapping("/{name}")
    public HelloVO getMessage(@PathVariable String name) {
        return new HelloVO(name);
    }

}
