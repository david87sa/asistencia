package com.cenfotec.asistencia.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping(value = {
            "/login",
            "/estudiantes/seccion",
            "/clases/docente/{id}",
            "/clases/docente",
            "/clases/{id}/estudiantes"
    })
    public String forward(HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println("➡️ Reescribiendo hacia index.html: " + path);
        return "forward:/index.html";
    }
}
