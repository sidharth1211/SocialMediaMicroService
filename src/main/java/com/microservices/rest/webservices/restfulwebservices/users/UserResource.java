package com.microservices.rest.webservices.restfulwebservices.users;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }



    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // http://localhost:8080/users
    //Entity Model
    //WebMvcLinkBuilder

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){

        User user = service.findOne(id); // convert to predicate
        if(user==null)
            throw new UserNotFoundException("id: "+id );
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id){

        service.deleteById(id); // convert to predicate
    }
}
