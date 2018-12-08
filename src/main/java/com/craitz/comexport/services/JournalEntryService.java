package com.craitz.comexport.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.repositories.JournalEntryRepository;
import com.craitz.comexport.services.exceptions.InvalidDateException;
import com.craitz.comexport.services.exceptions.JournalEntryNotFoundException;

@Service
public class JournalEntryService {
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private GregorianDateMatcherService matcherService;
	
	@Async
	public CompletableFuture<List<JournalEntry>> getAllJournalEntries(){
		// retorna todos os lançamentos contábeis de forma assíncrona
		return CompletableFuture.completedFuture(journalEntryRepository.findAll());
		
	}
	
	@Async
	public CompletableFuture<Long> insertEntry(JournalEntry journalEntry) {
		// valida a data antes de salvar
		if (!matcherService.matches(journalEntry.getDate().toString().trim())) {
			throw new InvalidDateException();
		}
		
		// insere o novo lançamento contábil de forma assíncrona
		return CompletableFuture.completedFuture(journalEntryRepository.save(journalEntry).getId());
	}
		
	@Async
	public CompletableFuture<JournalEntry> findEntry(Long id) throws InterruptedException, ExecutionException {
		// busca o lançamento contábil
		CompletableFuture<Optional<JournalEntry>> future = CompletableFuture.completedFuture(journalEntryRepository.findById(id));
		
		// verifica se foi encontrado
		if (future.get().isPresent()) {
			//modifica o conteúdo de CompletableFuture, transformando-o de Optional<JournalEntry> para JournalEntry apenas
			return future.thenApply(f -> f.get());
		} else {
			throw new JournalEntryNotFoundException();
		}
	}
}
