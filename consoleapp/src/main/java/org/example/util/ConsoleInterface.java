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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleConstants.INITIAL_WELCOMING
                + System.lineSeparator()
                + ConsoleConstants.ENTER_FILE_LOCATION_PROMPT + System.lineSeparator());
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(ConsoleConstants.EXIT_WORD)) {
                System.out.println(ConsoleConstants.GOODBYE);
                break;
            } else {
                try {
                    List<CustomerDto> customerDtos = fileReader.readFile(userInput);
                    System.out.println(customerDtos);
                    customerClientService.uploadCustomer(customerDtos);
                    System.out.println(ConsoleConstants.JSON_SAVING_SUCCESS + System.lineSeparator());
                } catch (MissingInputDataException e) {
                    System.out.println(ConsoleConstants.NO_DATA_ERROR + System.lineSeparator());
                } catch (Exception e) {
                    System.out.println(ConsoleConstants.UNKNOWN_ERROR);
                }
            }
        }
        scanner.close();
    }
}
