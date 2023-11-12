package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Address;

public interface AddressDao {
//add a method to link adr details to emp
	String assignAddress(Long empId,Address address);
}
