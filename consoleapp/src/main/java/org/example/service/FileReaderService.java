package org.example.service;

import java.util.List;

public interface FileReaderService<T> {

    List<T> readFile(String filePath);
}
