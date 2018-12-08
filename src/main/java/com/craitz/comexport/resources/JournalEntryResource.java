package com.craitz.comexport.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllJournalEntries() {
		return ResponseEntity.ok(journalEntryService.getAllJournalEntries());			
	}
	
//	@GetMapping
//	public void getJournalEntries(@RequestParam("conta-contabil") String journalAcccount ) ) {
//		if (journalAcccount != null && !journalAcccount.isEmpty()) {
//			journalEntryRepository.
//		}
//	}

	@PostMapping
	public ResponseEntity<?> insertEntry(@RequestBody JournalEntry journalEntry) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceCreated(journalEntryService.insertEntry(journalEntry)));			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findEntry(@PathVariable("id") Long id) {
		return ResponseEntity.ok(journalEntryService.findEntry(id));
	}
	
}
	