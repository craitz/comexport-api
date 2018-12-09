package com.craitz.comexport.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.domains.ResourceCreated;
import com.craitz.comexport.domains.Stats;
import com.craitz.comexport.services.JournalEntryService;

@RestController
@RequestMapping("/lancamentos-contabeis")
@CrossOrigin(origins = "*")
public class JournalEntryResource {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@GetMapping
	public ResponseEntity<?> getAllJournalEntries(@RequestParam(value = "contaContabil", required = false) Long journalAccount) throws InterruptedException, ExecutionException {
		
		CompletableFuture<List<JournalEntry>> future = null;
		
		// implementação de uma cache simples (20 segundos) para este endpoint
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

		if (journalAccount != null && !journalAccount.toString().isEmpty()) {
			// chama a camada de serviço buscando por conta contábil
			future = journalEntryService.findEntryByJournalAccount(journalAccount);
		} else {
			// chama a camada de serviço
			future = journalEntryService.getAllJournalEntries();
		}

		// retorna os lançamentos contábeis para o cliente
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(future.get());	
	}

	@PostMapping
	public ResponseEntity<?> insertEntry(@Valid @RequestBody JournalEntry journalEntry) throws InterruptedException, ExecutionException {
		// chama a camada de serviço
		CompletableFuture<Long> future = journalEntryService.insertEntry(journalEntry);

		// retorna o id do lançamento contábil recém criado para o cliente
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceCreated(future.get()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findEntry(@PathVariable(value = "id", required = true) Long id) throws InterruptedException, ExecutionException {
		// chama a camada de serviço
		CompletableFuture<JournalEntry> future = journalEntryService.findEntry(id);

		// retorna o lançamento contábil para o cliente
		return ResponseEntity.ok(future.get());
	}
		
	@GetMapping("/_stats")
	public ResponseEntity<?> getStats(@RequestParam(value = "contaContabil", required = false) Long journalAccount) throws InterruptedException, ExecutionException {
		
		CompletableFuture<Stats> future = null;
		
		if (journalAccount != null && !journalAccount.toString().isEmpty()) {
			// chama a camada de serviço buscando por conta contábil
			future = journalEntryService.getStats(journalAccount);
		} else {
			// chama a camada de serviço
			future = journalEntryService.getStats(null);
		}

		// retorna os lançamentos contábeis para o cliente
		return ResponseEntity.ok(future.get());
	}	
}
	