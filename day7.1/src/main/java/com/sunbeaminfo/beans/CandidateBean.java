package com.sunbeaminfo.beans;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.sunbeaminfo.dao.CandidateDaoImpl;
import com.sunbeaminfo.pojos.Candidate;

public class CandidateBean {
//dependency : dao 
	private CandidateDaoImpl candidateDao;
	//def ctor 
	public CandidateBean() throws SQLException{
		candidateDao=new CandidateDaoImpl();
		System.out.println("candidate bean created !");
	}
	public CandidateDaoImpl getCandidateDao() {
		return candidateDao;
	}
	public void setCandidateDao(CandidateDaoImpl candidateDao) {
		this.candidateDao = candidateDao;
	}
	//B.L method to get list of top 2 candidates
	public List<Candidate> getTopCandidates() throws SQLException
	{
		//invokes candidate dao's method
		return candidateDao.getTop2Candidates();
	}
	//B.L method to get partywise votes
	public LinkedHashMap<String, Integer> getPartyVotes()throws SQLException {
		return candidateDao.getPartywiseVotes();
	}
}
