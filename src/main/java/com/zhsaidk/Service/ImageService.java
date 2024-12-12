package com.zhsaidk.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("C:\\Users\\admin\\Desktop\\LibraryManagementSystem\\image")
    private final String bucket;


    @SneakyThrows
    public void update(String filename, InputStream content){
        Path fullPath = Path.of(bucket, filename);

        try(content){
            Files.write(fullPath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.createDirectories(fullPath.getParent());
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String filename){
        Path fullPath = Path.of(bucket, filename);
        return Files.exists(fullPath)
                ? Optional.of(Files.readAllBytes(fullPath))
                : Optional.empty();
    }
}
