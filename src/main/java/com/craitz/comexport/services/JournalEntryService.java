package com.craitz.comexport.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.domains.Stats;

public interface JournalEntryService {
	public CompletableFuture<List<JournalEntry>> getAllJournalEntries();
	public CompletableFuture<Long> insertEntry(JournalEntry journalEntry);
	public CompletableFuture<JournalEntry> findEntry(Long id) throws InterruptedException, ExecutionException;
	public CompletableFuture<List<JournalEntry>> findEntryByJournalAccount(Long journalAccount) throws InterruptedException, ExecutionException;
	public CompletableFuture<Stats> getStats(Long journalAccount) throws InterruptedException, ExecutionException;
}
