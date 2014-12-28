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
package cn.lhfei.identity.orm.persistence.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.lhfei.identity.orm.domain.AbstractDomain;
import cn.lhfei.identity.orm.persistence.IdentityDAO;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 27, 2014
 */
@Repository("identityDAOImpl")
public class IdentityDAOImpl extends AbstractBaseDAO<AbstractDomain, Integer>
		implements IdentityDAO {

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(this.sessionFactory);
	}

	/*@Override
	@Transactional
	public boolean save(AbstractDomain entity) {
		Session session = sessionFactory.getCurrentSession();

		session.save(entity);

		return true;
	}*/

	@Resource
	private SessionFactory sessionFactory;

}
