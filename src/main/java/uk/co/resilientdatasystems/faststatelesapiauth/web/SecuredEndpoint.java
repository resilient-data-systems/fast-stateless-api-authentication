package uk.co.resilientdatasystems.faststatelesapiauth.web;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping(value="/secure")
public class SecuredEndpoint {
    
    
    @RequestMapping("{param}")
    @ResponseBody
    public Response handleRequest(@PathVariable String param) {
        return new Response("Ha! " + param);
    }
}
