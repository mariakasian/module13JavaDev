package com.maria.module13.controllers;

import com.maria.module13.entity.Note;
import com.maria.module13.service.NoteService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired private NoteService noteService;

    @GetMapping(value = "/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("allNotes");
        result.addObject("notes",  noteService.listAll());
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView createNote(
            @RequestParam(value="title") @Size(min = 3, max = 50) String title,
            @RequestParam(value="content") @Size(min = 3, max = 300) String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return noteList();
    }

    @GetMapping(value = "/edit")
    public ModelAndView editNote(@NotEmpty @RequestParam(value="id") String id) {
        ModelAndView result = new ModelAndView("updateNote");
        result.addObject("note", noteService.getById(UUID.fromString(id)));
        return result;
    }

    @PostMapping(value = "/edit")
    public ModelAndView updateNote(
            @NotEmpty @RequestParam(value="id") String id,
            @Size(min = 3, max = 50) @RequestParam(value="title") String title,
            @Size(min = 3, max = 300) @RequestParam(value="content") String content) {

        noteService.update(UUID.fromString(id), title, content);
        return noteList();
    }

    @GetMapping(value = "/delete")
    @PostMapping(value = "/delete")
    public ModelAndView deleteNoteByPost(@NotEmpty @RequestParam(value="id") String id) {
        noteService.deleteById(UUID.fromString(id));
        return noteList();
    }
}

