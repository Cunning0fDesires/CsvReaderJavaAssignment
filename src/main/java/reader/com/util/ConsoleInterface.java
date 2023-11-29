package reader.com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reader.com.dto.Customer;
import reader.com.repository.CustomerRepository;
import reader.com.resource.CustomerController;
import reader.com.service.CsvReaderService;
import reader.com.service.CustomerClientService;
import reader.com.service.FileReaderService;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleInterface {
    private FileReaderService<Customer> fileReader;
    private CustomerClientService customerClientService;

    @Autowired
    public void setSomeDependency(CustomerClientService customerClientService, FileReaderService<Customer> fileReader) {
        this.customerClientService = customerClientService;
        this.fileReader = fileReader;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String exitWord = "exit";

        while (true) {
            System.out.print("Welcome to the console program to parse and save files!"
            + System.lineSeparator()
            + "Please enter the location of the file you want to process");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(exitWord)) {
                System.out.println("Thank you for using the program, see you next time!");
                break;
            } else {
                List<Customer> customers = fileReader.readFile(userInput);
                customerClientService.uploadCustomer(customers);
                System.out.println("Done! The file has been saved as JSON. If you want to leave, please enter 'exit'");
            }
        }
        scanner.close();
    }
}
