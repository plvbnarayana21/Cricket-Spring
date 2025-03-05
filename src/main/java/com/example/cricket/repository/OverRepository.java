package com.example.cricket.repository;

import com.example.cricket.Beans.Over;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OverRepository extends MongoRepository<Over,String> {
    Over save(Over over);
}
