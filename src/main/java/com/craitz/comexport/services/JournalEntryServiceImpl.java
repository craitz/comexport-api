package com.craitz.comexport.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.domains.Stats;
import com.craitz.comexport.exceptions.InvalidDateException;
import com.craitz.comexport.exceptions.JournalEntryNotFoundException;
import com.craitz.comexport.repositories.JournalEntryRepository;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {
	@Autowired
	private JournalEntryRepository journalEntryRepository;
		
	@Async
	@Override
	public CompletableFuture<List<JournalEntry>> getAllJournalEntries(){
		// retorna todos os lançamentos contábeis, de forma assíncrona
		return CompletableFuture.completedFuture(journalEntryRepository.findAll());
	}
	
	@Async
	@Override
	public CompletableFuture<Long> insertEntry(JournalEntry journalEntry) {
		// valida a data antes de salvar
		if (!validateDate(journalEntry.getDate().toString().trim())) {
			throw new InvalidDateException();
		}
		
		// insere o novo lançamento contábil, de forma assíncrona
		return CompletableFuture.completedFuture(journalEntryRepository.save(journalEntry).getId());
	}
		
	@Async
	@Override
	public CompletableFuture<JournalEntry> findEntry(Long id) throws InterruptedException, ExecutionException {
		// busca o lançamento contábil de forma assíncrona, pelo id
		CompletableFuture<Optional<JournalEntry>> future = CompletableFuture.completedFuture(journalEntryRepository.findById(id));
		
		// verifica se foi encontrado
		if (future.get().isPresent()) {
			//modifica o conteúdo de CompletableFuture, transformando-o de Optional<JournalEntry> para JournalEntry apenas
			return future.thenApply(f -> f.get());
		} else {
			throw new JournalEntryNotFoundException();
		}
	}
	
	@Async
	@Override
	public CompletableFuture<List<JournalEntry>> findEntryByJournalAccount(Long journalAccount) throws InterruptedException, ExecutionException {
		// busca os lançamentos contábeis de forma assíncrona, pela conta contábil
		return CompletableFuture.completedFuture(journalEntryRepository.findByJournalAccount(journalAccount));
	}
	
	@Async
	@Override
	public CompletableFuture<Stats> getStats(Long journalAccount) throws InterruptedException, ExecutionException {
		CompletableFuture<List<JournalEntry>> future = null;
		
		if (journalAccount == null) {
			// retorna todos os lançamentos contábeis, de forma assíncrona
			future = CompletableFuture.completedFuture(journalEntryRepository.findAll());
		} else {
			// retorna os lançamentos contábeis de forma assíncrona, pela conta contábil
			future = CompletableFuture.completedFuture(journalEntryRepository.findByJournalAccount(journalAccount));
		}
		
		List<JournalEntry> entries = future.get();
		
		return 	CompletableFuture.completedFuture(calculateStats(entries));
	}
	
	private Stats calculateStats(List<JournalEntry> entries) {
		Stats stats = new Stats();

		if (entries.isEmpty()) {
			return stats;
	    }
		
		//inicializa min/max
		stats.setMin(entries.get(0).getValue());
		stats.setMax(entries.get(0).getValue());
		
	    // quantidde
		stats.setQuantity(Long.valueOf(entries.size()));

		for (JournalEntry entry : entries) {
			// soma
			stats.setSum(stats.getSum() + entry.getValue());
			
			// min
			if (entry.getValue() < stats.getMin()) {
				stats.setMin(entry.getValue());
			}

			// max
			if (entry.getValue() > stats.getMax()) {
				stats.setMax(entry.getValue());
			}
		}
		
		// media
		stats.setAverage(stats.getSum()/entries.size());
				
		return stats;
	}
	
	public Boolean validateDate(String date) {
		// seta o formato para a data
		Pattern datePattern = Pattern.compile(
				"^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))02-29)$"
				+ "|^(((19|2[0-9])[0-9]{2})02(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})(0[13578]|10|12)(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})(0[469]|11)(0[1-9]|[12][0-9]|30))$"
		);
		
		// retorna o resultado da verificação
		return datePattern.matcher(date).matches();
	}
}
