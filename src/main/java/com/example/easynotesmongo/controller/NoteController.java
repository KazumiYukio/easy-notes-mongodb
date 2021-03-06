package com.example.easynotesmongo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.easynotesmongo.exception.ResourceNotFoundException;
import com.example.easynotesmongo.model.Note;
import com.example.easynotesmongo.repository.noteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NoteController {
    
    @Autowired
    noteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAlNotes(){
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note creaNote(@Valid @RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note findNoteById(@PathVariable(value = "id") Long noteId){
        return noteRepository.findById(noteId)
            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updaNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetail){
        
        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetail.getTitle());
        note.setContent(noteDetail.getContent());
        note.setPersisted(true);

        Note updateNote = noteRepository.save(note);
        return updateNote;

    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId){

        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        
        noteRepository.delete(note);

        return ResponseEntity.ok().build();

    }

}
