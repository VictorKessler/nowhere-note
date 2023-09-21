package br.com.victorkessler.nowherenote.infrastructure.repository;

import br.com.victorkessler.nowherenote.domain.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends CrudRepository<Note, UUID> {
    List<Note> findAll();
}
