package com.craitz.comexport.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.repositories.JournalEntryRepository;

@RestController
@RequestMapping("/journal-entries")
public class JournalEntryResource {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@GetMapping()
	public List<JournalEntry> getAllEntries() {
		return journalEntryRepository.findAll();
	}
	
	@PostMapping()
	public void insertEntry(@RequestBody JournalEntry journalEntry) {
		journalEntryRepository.save(journalEntry);
	}
	
	@GetMapping(value = "/{id}")
	public JournalEntry findEntry(@PathVariable("id") Long id) {
		JournalEntry entry = journalEntryRepository.findById(id).get();
	}
}
	