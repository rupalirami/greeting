package com.example.greeting;

import com.example.greeting.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.parseInt;

//please reconfigure the other 3 endpoints to use ResponseEntity and choose the appropriate status codes to return
//        /findAll (GET)
//        /random (GET)
//        /greetings (POST)

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GreetingController {

//    private ArrayList<Greeting> greetings = new ArrayList<>();

    // dependency injection
        // avoids us needing to make a new instance
    @Autowired
    GreetingRepository repository;

    @GetMapping("/greeting/{id}")
//    public Greeting getGreetingById(@PathVariable int id) {
    public ResponseEntity<Optional<Greeting>> getGreetingById(@PathVariable String id) {
        // what made up my response
            // status code
            // body - our actual greeting()
            // headers - additional information regarding the request and response

        // ResponseEntity
            // we can configure our entire response using this
                // .status() configure the status code we receive
//        return repository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));


//        return greetings.stream()
//                .filter(greeting -> greeting.getId() == parseInt(id))
//                .findFirst()
//                .orElse(null);
    }


    @GetMapping("/greetings")
    public ResponseEntity<List<Greeting>> getAllGreetings() {
//        return repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
//    public ResponseEntity<Optional<Greeting>> getGreetingById(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));

    @GetMapping("/random")
    public ResponseEntity<Greeting> getRandomGreeting() {
    // example
        // Random r = new Random();
        // Greeting randomGreeting = greetings.get(r.nextInt(greetings.size()));
        // return randomGreeting;
        // return repository.findByid(r.nextInt((int) repository.count()));
        Random r = new Random();
        // .count to find number of entries in the repository
            // int index = 1 + r.nextInt((int) (repository.count()));
            //  return repository.findByid(index);
        // .findAll (to get all existing greetings)
        List<Greeting> greetings = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(greetings.get(r.nextInt(greetings.size())));
//        return greetings.get(index);
//        return null;
        // .findById with repository.count() as argument
    }

    @PostMapping("/greetings")
    public ResponseEntity<String> createGreeting(@RequestBody Greeting greeting) {
        // set the greetings id based on the greetings list
        // set the created by
//        greeting.setId(greetings.size() + 1);
//        greeting.setCreatedBy("Ollie");
//        greeting.setDateCreated(new Timestamp(System.currentTimeMillis()));
        repository.save(greeting);
        return ResponseEntity.status(HttpStatus.OK).body(greeting + " greeting added");
    }

    // UPDATE route
    @PutMapping("/greetings/{id}")
    public String updateFullGreeting(@PathVariable int id, @RequestBody Greeting newGreeting){
//        Greeting updatedGreeting = greetings.get(id);
        Greeting updatedGreeting = repository.findByid(id);
        System.out.println(updatedGreeting);
        if (newGreeting.getGreeting() != null) {
            updatedGreeting.setGreeting(newGreeting.getGreeting());
        }
        if (newGreeting.getCreatedBy()  != null) {
            updatedGreeting.setCreatedBy(newGreeting.getCreatedBy());
        }
        if (newGreeting.getOriginCountry()  != null) {
            updatedGreeting.setOriginCountry(newGreeting.getOriginCountry());
        }
        repository.save(updatedGreeting);
        return "Greeting with id: " + id + "changed to" + updatedGreeting;
    }

    // DELETE route
//    @Transactional
    @DeleteMapping("/greetings/{id}")
    public String deleteGreeting(@PathVariable int id) {
        repository.delete(repository.findByid(id));
//        greetings.remove(greetings.get(id));
        return "Greeting with id: " + id + " deleted.";
    }
}
