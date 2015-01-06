/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.lhfei.identity.orm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 26, 2014
 */
@Entity
@Table(name="SYS_MENU")
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY,region="SYS_MENU")
public class Menu extends AbstractDomain {
	private static final long serialVersionUID = 2675598634806655044L;
	
	@Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MENU_ID")
	private int menuId;

	@Column(name="M_NAME")
	private String mName;
	
	@Column(name="M_CODE")
	private String mCode;
	
	@Column(name="PID")
	private int pid;
	
	@Column(name="M_URL")
	private String mUrl;
	
	@Column(name="M_CLS")
	private String mCls;
	
	@Column(name="M_STATUS")
	private String mStatus;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getmCls() {
		return mCls;
	}

	public void setmCls(String mCls) {
		this.mCls = mCls;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}
	
}
