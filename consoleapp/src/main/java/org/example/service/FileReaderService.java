package org.example.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface FileReaderService<T> {

    List<T> readFile(String filePath);
}
