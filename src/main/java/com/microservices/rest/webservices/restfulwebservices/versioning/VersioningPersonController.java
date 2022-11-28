package com.microservices.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Charlie Hebdo");
    }
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Charlie", "Sheen"));
    }
    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getFirstVersionRequestParameter(){
        return new PersonV1("Charlie Chaplin");
    }
    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondVersionRequestParameter(){
        return new PersonV2(new Name("Charlie", "Sheen"));
    }

}
