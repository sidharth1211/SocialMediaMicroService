package com.microservices.rest.webservices.restfulwebservices.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    public static Integer userCount=0;
    static {
        users.add(new User(userCount++,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(userCount++,"Marcus", LocalDate.now().minusYears(40)));
        users.add(new User(userCount++,"Ricky", LocalDate.now().minusYears(50)));
    }

    public List<User>findAll(){
        return users;
    }
    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findAny().orElse(null);
    }
    public User save(User user){
        user.setId(userCount);
        users.add(user);
        return user;
    }
    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
