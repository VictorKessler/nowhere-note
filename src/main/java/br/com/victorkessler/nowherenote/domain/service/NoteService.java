package br.com.victorkessler.nowherenote.domain.service;

import br.com.victorkessler.nowherenote.domain.model.Note;
import br.com.victorkessler.nowherenote.infrastructure.repository.NoteRepository;
import br.com.victorkessler.nowherenote.infrastructure.requests.NoteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    private NoteRepository repository;
    private Logger LOGGER = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public void createNote(NoteRequest newNoteRequest) {

        try {

            if (newNoteRequest.name().isEmpty() || newNoteRequest.content().isEmpty()) {
                LOGGER.error("The note must have name or content");
                return;
            }

            final Note newNote = new Note(
                    newNoteRequest.name(),
                    newNoteRequest.content(),
                    Instant.now()
            );

            repository.save(newNote);
        } catch (Exception e) {
            LOGGER.error("Invalid note", e.getMessage());
        }
    }

    public List<Note> getNotes() {
        return repository.findAll();
    }

    public void updateNote(UUID noteId, NoteRequest request) {

        Optional<Note> oldNote = repository.findById(noteId);

        if (oldNote.isEmpty()) {
            LOGGER.error("No such note available");
            return;
        }

        try {
            oldNote.ifPresent(updatedNote -> {
                updatedNote.setName(request.name());
                updatedNote.setContent(request.content());
                repository.save(updatedNote);
            });
        } catch (Exception e) {
            LOGGER.error("Invalid action", e.getMessage());
        }
    }

    public void deleteNote(UUID noteId) {
        Optional<Note> oldNote = repository.findById(noteId);

        if (oldNote.isEmpty()) {
            LOGGER.error("No such note available");
            return;
        }

        try {
            repository.deleteById(noteId);
        } catch (Exception e) {
            LOGGER.error("Invalid action", e.getMessage());
        }
    }

}
