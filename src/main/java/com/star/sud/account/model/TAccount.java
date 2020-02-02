/**
 * 
 */
package com.star.sud.account.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.star.sud.abs.entity.AbstractEntity;
import com.star.sud.employee.model.TEmployee;

/**
 * @author Sudarshan
 *
 */
@Entity
@Table(name = "T_ACCOUNT", catalog = "research")
public class TAccount extends AbstractEntity<TAccount> {

	// Static Attributes
	/////////////////////
	private static final long serialVersionUID = 1063956056033217027L;

	// Attributes
	//////////////
	private String accnId;
	private String accnNumber;
	private String accnName;
	private String accnBranch;
	private char accnStatus;
	private TEmployee accnEmployee;

	// Override Methods
	////////////////////
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TAccount arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.abs.entity.AbstractEntity#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	// Constructors
	//////////////////

	/**
	 * @param accnId
	 * @param accnNumber
	 * @param accnName
	 * @param accnBranch
	 * @param accnStatus
	 * @param accnEmployee
	 */
	public TAccount(String accnId, String accnNumber, String accnName, String accnBranch, char accnStatus,
			TEmployee accnEmployee) {
		super();
		this.accnId = accnId;
		this.accnNumber = accnNumber;
		this.accnName = accnName;
		this.accnBranch = accnBranch;
		this.accnStatus = accnStatus;
		this.accnEmployee = accnEmployee;
	}

	/**
	 * 
	 */
	public TAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the accnId
	 */
	@Id
	@Column(name = "ACCN_ID", unique = true, nullable = false, length = 35)
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
	 * @return the accnNumber
	 */
	@Column(name = "ACCN_NUMBER", nullable = false, length = 100)
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
	@Column(name = "ACCN_BRANCH", nullable = false, length = 100)
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
	@Column(name = "ACCN_STATUS", nullable = false, length = 1)
	public char getAccnStatus() {
		return accnStatus;
	}

	/**
	 * @param accnStatus the accnStatus to set
	 */
	public void setAccnStatus(char accnStatus) {
		this.accnStatus = accnStatus;
	}

	/**
	 * @return the accnEmployee
	 */
	@OneToOne(mappedBy = "tAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public TEmployee getAccnEmployee() {
		return accnEmployee;
	}

	/**
	 * @param accnEmployee the accnEmployee to set
	 */
	public void setAccnEmployee(TEmployee accnEmployee) {
		this.accnEmployee = accnEmployee;

		if (accnEmployee == null) {
			if (this.accnEmployee != null) {
				this.accnEmployee.settAccount(null);
			}
		} else {
			accnEmployee.settAccount(this);
		}
		this.accnEmployee = accnEmployee;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the accnName
	 */
	@Column(name = "ACCN_NAME")
	public String getAccnName() {
		return accnName;
	}

	/**
	 * @param accnName the accnName to set
	 */
	public void setAccnName(String accnName) {
		this.accnName = accnName;
	}

}
