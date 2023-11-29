package reader.com.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileReaderService<T> {

    List<T> readFile(String filePath);
}
