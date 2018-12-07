package com.craitz.comexport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.craitz.comexport.domains.JournalEntry;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

}
