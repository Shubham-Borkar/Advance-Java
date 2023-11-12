package com.sunbeaminfo.dao;

import static com.sunbeaminfo.utils.DBUtils.closeConnection;
import static com.sunbeaminfo.utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	public CandidateDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		System.out.println("candidate dao created !");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			// candidateId, String name, String partyName, int votes
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}
	

	@Override
	public String updateVotes(int candidateId) throws SQLException {
		// set IN param : cid
		pst2.setInt(1, candidateId);
		int updateCount=pst2.executeUpdate();		
		return "Updated votes!";		
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
		System.out.println("candidate dao cleaned up !");

	}

}
