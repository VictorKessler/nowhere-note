package br.com.victorkessler.nowherenote.infrastructure.sync;

import br.com.victorkessler.nowherenote.domain.model.Note;
import br.com.victorkessler.nowherenote.domain.service.NoteService;
import br.com.victorkessler.nowherenote.infrastructure.requests.NoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@RestController("/home")
public class NoteController {

    private NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @PostMapping("/new-note")
    public ResponseEntity<HttpResponse> newNote(@RequestBody NoteRequest request) {
        service.createNote(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return service.getNotes();
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateNote(@RequestParam(name = "noteId") UUID id, @RequestBody NoteRequest body) {
        service.updateNote(id, body);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteNote(@RequestParam(name = "noteId") UUID id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
