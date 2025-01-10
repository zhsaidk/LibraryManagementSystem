package com.zhsaidk.Service;

import com.zhsaidk.database.Entity.Author;
import com.zhsaidk.database.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author findAuthorByID(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AuthorNotFound!"));
    }

}
