package com.example.greeting;

import com.example.greeting.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//          Repository
//              |
//        CrudRepository
//              |
//         JpaRepository
// we have access to all of the CRUD operations inherited from CrudRepository

public interface GreetingRepository extends JpaRepository <Greeting, String> {
    // we have access to built in methods like save() for POST, findAll for GET
    // we can also write our own custom methods (later)
        // jpa will parse the names of these custom methods to look for "find" "by" "distinct"
        // and it will generate a jpa query language and will create a custom method

    Greeting findByid(int id);
            // find - searching for something
            // By - the condition fo what's being searched for
            // id - the variable
//     Greeting findById(String id);
}
