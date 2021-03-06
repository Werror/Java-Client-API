package com.sendsafely;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.sendsafely.dto.ContactGroupMember;

/**
 * A java bean containing the information for a contact group.
 * A ContactGroup contains information about a contact group including the following,
 * contactGroupId, contactGroupName, ContactGroupUsers, and users.
 * Only the Getters should be used from this object, since the server will populate the object. 
 * Updating the setters will not change any state on the server and should be avoided.
 * @author michaelnowak
 *
 */
public class ContactGroup {
	private String contactGroupId;
	private String contactGroupName;
	private List<ContactGroupMember> users = new ArrayList<ContactGroupMember>(0);
	
	/**
	 * @description Get the contact group id
	 * @return contactGroupId The id representation of the contact group.
	 */
	public String getContactGroupId() {
		return contactGroupId;
	}
	
	/**
	 * @description Set internally by the API
	 * @param contactGroupId
	 */
	public void setContactGroupId(String contactGroupId) {
		this.contactGroupId = contactGroupId;
	}
	
	/**
	 * @description Get the contact group Name
	 * @return contactGroupName
	 */
	public String getContactGroupName() {
		return contactGroupName;
	}
	
	/**
	 * @description Set internally by the API
	 * @param contactGroupName
	 */
	public void setContactGroupName(String contactGroupName) {
		this.contactGroupName = contactGroupName;
	}
	
	/**
	 * @description Get a list of users
	 * @return List<Map<String,String>> users
	 */
	public List<ContactGroupMember> getContactGroupMembers() {
		return users;
	}
	
	/**
	 * @description Set internally by the API
	 * @param users
	 */
	public void setContactGroupMembers(List<ContactGroupMember> users) {
		this.users = users;
	}
}
