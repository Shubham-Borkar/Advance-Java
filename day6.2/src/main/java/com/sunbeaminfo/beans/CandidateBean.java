package com.sunbeaminfo.beans;

import java.sql.SQLException;
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
		return candidateDao.getTop2Candidates();
	}
}
