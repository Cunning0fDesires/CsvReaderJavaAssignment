package org.example.util;

import lombok.AllArgsConstructor;
import org.example.dto.CustomerDto;
import org.example.exceptioins.MissingInputDataException;
import org.example.service.CustomerClientService;
import org.example.service.FileReaderService;


import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ConsoleInterface {
    private final FileReaderService<CustomerDto> fileReader;
    private final CustomerClientService customerClientService;
    public static final String EXIT_WORD = "exit";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to the console program to parse and save files!"
                + System.lineSeparator()
                + "Please enter the location of the file you want to process" + System.lineSeparator());
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(EXIT_WORD)) {
                System.out.println("Thank you for using the program, see you next time!");
                break;
            } else {
                try {
                    List<CustomerDto> customerDtos = fileReader.readFile(userInput);
                    customerClientService.uploadCustomer(customerDtos);
                    System.out.println("Done! The file has been saved as JSON. If you want to leave, please enter 'exit'" + System.lineSeparator());
                } catch (MissingInputDataException e) {
                    System.out.println("Oops! It seems your file has no data. Please check your file and try again." + System.lineSeparator());
                }
                catch (Exception e) {
                    System.out.println("Something went wrong, please try again or change the input file.");
                }
            }
        }
        scanner.close();
    }
}
