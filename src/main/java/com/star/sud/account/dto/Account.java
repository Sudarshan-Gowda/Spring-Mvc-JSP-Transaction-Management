/**
 * 
 */
package com.star.sud.account.dto;

import com.star.sud.abs.dto.AbstractDTO;

/**
 * @author Sudarshan
 *
 */
public class Account extends AbstractDTO<Account> {

	// Static Attributes
	/////////////////////////
	private static final long serialVersionUID = -1617780873443973033L;

	// Attributes
	////////////////
	private String accnId;
	private String accnName;
	private String accnNumber;
	private String accnBranch;
	private String accnStatus;

	// Properties
	//////////////////
	/**
	 * @return the accnId
	 */
	public String getAccnId() {
		return accnId;
	}

	/**
	 * @param accnId the accnId to set
	 */
	public void setAccnId(String accnId) {
		this.accnId = accnId;
	}

	/**
	 * @return the accnName
	 */
	public String getAccnName() {
		return accnName;
	}

	/**
	 * @param accnName the accnName to set
	 */
	public void setAccnName(String accnName) {
		this.accnName = accnName;
	}

	/**
	 * @return the accnNumber
	 */
	public String getAccnNumber() {
		return accnNumber;
	}

	/**
	 * @param accnNumber the accnNumber to set
	 */
	public void setAccnNumber(String accnNumber) {
		this.accnNumber = accnNumber;
	}

	/**
	 * @return the accnBranch
	 */
	public String getAccnBranch() {
		return accnBranch;
	}

	/**
	 * @param accnBranch the accnBranch to set
	 */
	public void setAccnBranch(String accnBranch) {
		this.accnBranch = accnBranch;
	}

	/**
	 * @return the accnStatus
	 */
	public String getAccnStatus() {
		return accnStatus;
	}

	/**
	 * @param accnStatus the accnStatus to set
	 */
	public void setAccnStatus(String accnStatus) {
		this.accnStatus = accnStatus;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}