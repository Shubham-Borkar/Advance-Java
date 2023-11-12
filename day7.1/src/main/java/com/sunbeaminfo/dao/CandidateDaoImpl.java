package com.sunbeaminfo.dao;

import static com.sunbeaminfo.utils.DBUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.sunbeaminfo.pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3, pst4, pst5, pst6;

	// def ctor
	public CandidateDaoImpl() throws SQLException {
		// open cn , pst1
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3 = cn.prepareStatement("select * from candidates order by votes desc limit 2");
		pst4 = cn.prepareStatement("select party,sum(votes) from candidates group by party");
		pst5 = cn.prepareStatement("delete from candidates where id=?");
		pst6 = cn.prepareStatement("update candidates set party=?,votes=? where id=?");
		System.out.println("candidate dao created");

	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		// create empty AL
		List<Candidate> candidates = new ArrayList<>();
		// exec query
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}

	@Override
	public String updateVotes(int candidateId) throws SQLException {
		// set IN param : candidate id
		pst2.setInt(1, candidateId);
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "Updated vote counts...";
		return "Updation of votes failed!!!!!";

	}

	@Override
	public List<Candidate> getTop2Candidates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try (ResultSet rst = pst3.executeQuery()) {
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}

	@Override
	public LinkedHashMap<String, Integer> getPartywiseVotes() throws SQLException {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		try (ResultSet rst = pst4.executeQuery()) {
			while (rst.next())
				map.put(rst.getString(1), rst.getInt(2));
		}
		return map;
	}

	@Override
	public String deleteCandidateDetails(int candidateId) throws SQLException {
		// set IN param
		pst5.setInt(1, candidateId);
		int rows = pst5.executeUpdate();
		return "Deleted " + rows + " candidate";
	}

	@Override
	public String updateCandidateDetails(String newParty, int votes,int candidateId) throws SQLException {
		// set IN param
		pst6.setString(1, newParty);
		pst6.setInt(2, votes);
		pst6.setInt(3, candidateId);
		int rows = pst6.executeUpdate();
		return "Updated " + rows + " candidate";
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();
		if (pst5 != null)
			pst5.close();
		if (pst6 != null)
			pst6.close();
		closeConnection();
		System.out.println("candidate dao cleaned up....");
	}

}
