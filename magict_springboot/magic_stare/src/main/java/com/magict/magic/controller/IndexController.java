package com.magict.magic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZP
 * @date 2018/5/213:53
 * @description:
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
