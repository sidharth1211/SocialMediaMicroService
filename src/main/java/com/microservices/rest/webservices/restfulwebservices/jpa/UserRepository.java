package com.microservices.rest.webservices.restfulwebservices.jpa;


import com.microservices.rest.webservices.restfulwebservices.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface UserRepository extends JpaRepository<User, Integer> {

}
