package org.example;

import org.example.client.CsvCustomerClient;
import org.example.client.CustomerClient;
import org.example.dto.CustomerDto;
import org.example.service.CsvReaderService;
import org.example.service.CustomerClientService;
import org.example.service.FileReaderService;
import org.example.util.ConsoleInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        FileReaderService<CustomerDto> fileReaderService = new CsvReaderService();
        CustomerClient customerClient = new CsvCustomerClient();
        CustomerClientService customerClientService = new CustomerClientService(customerClient);

        ConsoleInterface consoleInterface = new ConsoleInterface(fileReaderService, customerClientService);
        consoleInterface.start();
    }
}
