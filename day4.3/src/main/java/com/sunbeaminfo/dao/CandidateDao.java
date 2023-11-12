package com.sunbeaminfo.dao;

import java.sql.SQLException;
import java.util.List;

import com.sunbeaminfo.pojos.Candidate;

public interface CandidateDao {
	List<Candidate> getAllCandidates() throws SQLException;
	// add a method to increment the votes
	String updateVotes(int candidateId) throws SQLException;
}
