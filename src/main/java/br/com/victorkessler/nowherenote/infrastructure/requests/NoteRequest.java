package br.com.victorkessler.nowherenote.infrastructure.requests;

public record NoteRequest(
        String name,
        String content
){}
