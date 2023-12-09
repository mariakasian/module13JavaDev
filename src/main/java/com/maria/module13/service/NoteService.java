package com.maria.module13.service;

import com.maria.module13.entity.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {
    @Autowired
    private Note note;

    private List<Note> notes = new ArrayList<>();

    public Note add(Note note) {
        note.setId(UUID.randomUUID());
        this.notes.add(note);
        return note;
    }

    public List<Note> listAll() {
        return this.notes;
    }

    public Note getById(UUID id){
        int index = -1;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return notes.get(index);
        } else {
            throw new NullPointerException("The Note with id " + id + " is not found!");
        }
    }

    public void update(UUID id, String newTitle, String newContent){

        Note updatingNote = getById(id);
        if (updatingNote != null){
            updatingNote.setTitle(newTitle);
            updatingNote.setContent(newContent);
        }
    }

    public void deleteById(UUID id){
        int index = -1;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            notes.remove(index);
        } else {
            throw new NullPointerException("The Note with id " + id + " is not found!");
        }
    }

    @PostConstruct
    public void init() {
        Note n1 = new Note();
        n1.setTitle("Note #1");
        n1.setContent("Content #1");
        add(n1);

        Note n2 = new Note();
        n2.setTitle("Note #2");
        n2.setContent("Content #2");
        add(n2);

        System.out.println("=============================");
        System.out.println("Getting by Id:");
        System.out.println("getById(n1.getId()) = " + getById(n1.getId()));

        System.out.println("=============================");
        System.out.println("List All Notes:");
        System.out.println("listAll() = " + listAll());

        System.out.println("=============================");
        System.out.println("Updating the Note #1");
        update(n1.getId(), "Updated Note #1", "Updated content #1");
        System.out.println("getById(n1.getId()) = " + getById(n1.getId()));

        System.out.println("=============================");
        System.out.println("Deleting by Id the Note #2");
        deleteById(n2.getId());
        System.out.println("listAll() = " + listAll());
        System.out.println("=============================");
    }
}
