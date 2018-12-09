package com.craitz.comexport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.repositories.JournalEntryRepository;
import com.craitz.comexport.services.JournalEntryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ComexportApiApplicationTests {

	@Mock
	private JournalEntry journalEntryMock;
	
	@Mock
	private JournalEntryRepository journalEntryRepositoryMock;
	
	@Mock
	private JournalEntryServiceImpl journalEntryServiceMock;
		
	@InjectMocks
	private Optional<JournalEntry> optionalEntry;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(journalEntryMock);
		assertNotNull(journalEntryRepositoryMock);
		assertNotNull(journalEntryServiceMock);
		assertNotNull(optionalEntry);
	}
	
	@Test
	public void testJounalEntries() throws InterruptedException, ExecutionException {
		Long entryId = 5L;
		Long accountId = 2775L;
		List<JournalEntry> entries = new ArrayList<JournalEntry>();
		
	    when(journalEntryRepositoryMock.findAll()).thenReturn(entries);
	    assertEquals(entries, journalEntryRepositoryMock.findAll());
	    
	    when(journalEntryRepositoryMock.findById(entryId)).thenReturn(optionalEntry);
	    assertEquals(optionalEntry, journalEntryRepositoryMock.findById(entryId));

	    when(journalEntryRepositoryMock.findByJournalAccount(accountId)).thenReturn(entries);
	    assertEquals(entries, journalEntryRepositoryMock.findByJournalAccount(accountId));

	    journalEntryServiceMock.getAllJournalEntries();
	    verify(journalEntryRepositoryMock).findAll();
	    
	    journalEntryServiceMock.findEntry(entryId);
	    verify(journalEntryRepositoryMock).findById(entryId);

	    journalEntryServiceMock.findEntryByJournalAccount(accountId);
	    verify(journalEntryRepositoryMock).findByJournalAccount(accountId);

	    journalEntryServiceMock.getStats(null);
	    assertEquals(null, journalEntryServiceMock.getStats(null));

	    journalEntryServiceMock.getStats(accountId);
	    assertEquals(null, journalEntryServiceMock.getStats(accountId));

	    InOrder orderRepo = inOrder(journalEntryRepositoryMock);
		orderRepo.verify(journalEntryRepositoryMock).findAll();
		orderRepo.verify(journalEntryRepositoryMock).findById(entryId);
		orderRepo.verify(journalEntryRepositoryMock).findByJournalAccount(accountId);

		InOrder orderService = inOrder(journalEntryServiceMock);
		orderService.verify(journalEntryServiceMock).getAllJournalEntries();
		orderService.verify(journalEntryServiceMock).findEntry(entryId);
	    orderService.verify(journalEntryServiceMock).findEntryByJournalAccount(accountId);
	    orderService.verify(journalEntryServiceMock, times(2)).getStats(null);
	    orderService.verify(journalEntryServiceMock, times(2)).getStats(accountId);
	}
}
