package com.craitz.comexport.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.craitz.comexport.domains.JournalEntry;
import com.craitz.comexport.repositories.JournalEntryRepository;

@RestController
@RequestMapping("/journalEntries")
public class JournalEntryResource {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@GetMapping()
	public List<JournalEntry> list() {
		return journalEntryRepository.findAll();
	}
	
	@PostMapping()
	public void save(@RequestBody JournalEntry journalEntry) {
		journalEntryRepository.save(journalEntry);
	}
}
	