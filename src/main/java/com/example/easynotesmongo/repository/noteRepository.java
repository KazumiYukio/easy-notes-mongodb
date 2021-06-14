package com.example.easynotesmongo.repository;

import com.example.easynotesmongo.model.Note;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface noteRepository extends MongoRepository<Note, Long>{
    
}
