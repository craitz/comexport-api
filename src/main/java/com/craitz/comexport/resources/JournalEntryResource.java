package com.craitz.comexport.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.domains.ResourceCreated;
import com.craitz.comexport.services.JournalEntryService;

@RestController
@RequestMapping("/lancamentos-contabeis")
public class JournalEntryResource {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@GetMapping
	public ResponseEntity<?> getAllJournalEntries() throws InterruptedException, ExecutionException {
		// chama a camada de serviço de forma assíncrona
		CompletableFuture<List<JournalEntry>> future = journalEntryService.getAllJournalEntries();

		// retorna os lançamentos contábeis para o cliente
		return ResponseEntity.ok(future.get());
	}

	@PostMapping
	public ResponseEntity<?> insertEntry(@Valid @RequestBody JournalEntry journalEntry) throws InterruptedException, ExecutionException {
		// chama a camada de serviço de forma assíncrona
		CompletableFuture<Long> future = journalEntryService.insertEntry(journalEntry);

		// retorna o id do lançamento contábil recém criado para o cliente
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceCreated(future.get()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findEntry(@PathVariable(value = "id", required = true) Long id) throws InterruptedException, ExecutionException {
		// chama a camada de serviço de forma assíncrona
		CompletableFuture<JournalEntry> future = journalEntryService.findEntry(id);

		// retorna o lançamento contábil para o cliente
		return ResponseEntity.ok(future.get());
	}
	
}
	