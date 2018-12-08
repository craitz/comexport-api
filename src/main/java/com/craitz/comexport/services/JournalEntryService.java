package com.craitz.comexport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.repositories.JournalEntryRepository;
import com.craitz.comexport.services.exceptions.JournalEntryNotFoundException;

@Service
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	public List<JournalEntry> getAllJournalEntries() {
		try {
			return journalEntryRepository.findAll();			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Long insertEntry(JournalEntry journalEntry) {
		try {
			return journalEntryRepository.save(journalEntry).getId();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
		
	public JournalEntry findEntry(Long id) {
		Optional<JournalEntry> entry = null;
		
		try {
			entry = journalEntryRepository.findById(id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		if (entry.isPresent()) {
			return entry.get();
		} else {
			throw new JournalEntryNotFoundException();
		}
	}
}
