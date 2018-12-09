package com.craitz.comexport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.domains.Stats;
import com.craitz.comexport.repositories.JournalEntryRepository;
import com.craitz.comexport.services.JournalEntryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ComexportApiApplicationTests {
	@Mock
	private JournalEntryRepository journalEntryRepositoryMock;
	
	@InjectMocks
	private JournalEntryServiceImpl journalEntryServiceMock;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(journalEntryRepositoryMock);
		assertNotNull(journalEntryServiceMock);
	}
	
	@Test
	public void testGetAllJournalEntries() throws InterruptedException, ExecutionException {
		List<JournalEntry> entryList = new ArrayList<JournalEntry>();		
		entryList.add(new JournalEntry(1L, 211L, 21011022L, 123.44));
		entryList.add(new JournalEntry(2L, 211L, 21011022L, 33.04));
		entryList.add(new JournalEntry(3L, 211L, 21011022L, 45.41));
		entryList.add(new JournalEntry(4L, 311L, 21011022L, 79.14));
		entryList.add(new JournalEntry(5L, 311L, 21011022L, 10.99));
		when(journalEntryRepositoryMock.findAll()).thenReturn(entryList);
		
		CompletableFuture<List<JournalEntry>> result = journalEntryServiceMock.getAllJournalEntries();
		List<JournalEntry> resultEntryList = result.get();
		assertEquals(5, resultEntryList.size());
	}
	
	@Test
	public void testGetAllJournalEntriesByJournalAccount() throws InterruptedException, ExecutionException {
		List<JournalEntry> entryList = new ArrayList<JournalEntry>();		
		entryList.add(new JournalEntry(1L, 211L, 21011022L, 123.44));
		entryList.add(new JournalEntry(2L, 211L, 21011022L, 33.04));
		entryList.add(new JournalEntry(3L, 211L, 21011022L, 45.41));
		when(journalEntryRepositoryMock.findByJournalAccount(211L)).thenReturn(entryList);
		
		CompletableFuture<List<JournalEntry>> result = journalEntryServiceMock.findEntryByJournalAccount(211L);
		List<JournalEntry> resultEntryList = result.get();
		assertEquals(3, resultEntryList.size());
	}

	@Test
	public void testFindJournalEntryById() throws InterruptedException, ExecutionException {
		JournalEntry entry = new JournalEntry(1L, 211L, 21011022L, 123.44);
		Optional<JournalEntry> optionalEntry = Optional.ofNullable(entry);
		when(journalEntryRepositoryMock.findById(1L)).thenReturn(optionalEntry);
		
		CompletableFuture<JournalEntry> result = journalEntryServiceMock.findEntry(1L);
		JournalEntry resultEntry = result.get();
		assertEquals(Long.valueOf(1), resultEntry.getId());
		assertEquals(Long.valueOf(211), resultEntry.getJournalAccount());
		assertEquals(Long.valueOf(21011022), resultEntry.getDate());
		assertEquals(Double.valueOf(123.44), resultEntry.getValue());
	}	
	
	@Test
	public void insertJournalEntry() throws InterruptedException, ExecutionException {
		JournalEntry entry = new JournalEntry(1L, 211L, 21011022L, 766.21);
		when(journalEntryRepositoryMock.save(entry)).thenReturn(entry);
		
		CompletableFuture<Long> result = journalEntryServiceMock.insertEntry(entry);
		Long resultId = result.get();
		assertEquals(Long.valueOf(1), resultId);
	}
	
	@Test
	public void testGetStats() throws InterruptedException, ExecutionException {
		List<JournalEntry> entryList = new ArrayList<JournalEntry>();		
		entryList.add(new JournalEntry(1L, 211L, 21011022L, 8.0));
		entryList.add(new JournalEntry(2L, 311L, 21011022L, 6.0));
		when(journalEntryRepositoryMock.findAll()).thenReturn(entryList);
		
		CompletableFuture<Stats> result = journalEntryServiceMock.getStats(null);
		Stats resultStats = result.get();
		assertEquals(Double.valueOf(14.0), resultStats.getSum());
		assertEquals(Double.valueOf(6.0), resultStats.getMin());
		assertEquals(Double.valueOf(8.0), resultStats.getMax());
		assertEquals(Double.valueOf(7.0), resultStats.getAverage());
		assertEquals(Long.valueOf(2), resultStats.getQuantity());
	}
	
	@Test
	public void testGetStatsByJournalAccount() throws InterruptedException, ExecutionException {
		List<JournalEntry> entryList = new ArrayList<JournalEntry>();		
		entryList.add(new JournalEntry(1L, 211L, 21011022L, 8.0));
		entryList.add(new JournalEntry(2L, 211L, 21011022L, 6.0));
		when(journalEntryRepositoryMock.findByJournalAccount(211L)).thenReturn(entryList);
		
		CompletableFuture<Stats> result = journalEntryServiceMock.getStats(211L);
		Stats resultStats = result.get();
		assertEquals(Double.valueOf(14.0), resultStats.getSum());
		assertEquals(Double.valueOf(6.0), resultStats.getMin());
		assertEquals(Double.valueOf(8.0), resultStats.getMax());
		assertEquals(Double.valueOf(7.0), resultStats.getAverage());
		assertEquals(Long.valueOf(2), resultStats.getQuantity());
	}
	
}
