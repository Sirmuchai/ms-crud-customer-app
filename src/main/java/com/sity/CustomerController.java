package com.sity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(CustomerController.class, args);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> searchCustomer(@PathVariable("customerId") Integer id) {
        Optional<Customer> customerData = customerRepository.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }
    @PostMapping("/customer")
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);

    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id,
                                                   @RequestBody NewCustomerRequest request){
        Optional<Customer> customerData = customerRepository.findById(id);
        Customer customer = customerData.get();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

}
