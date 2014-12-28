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
package cn.lhfei.fu.orm.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.fu.orm.persistence.HomeworkBaseDAO;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;
import cn.lhfei.identity.orm.persistence.impl.AbstractBaseDAO;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */
@Repository("homeworkBaseDAO")
public class HomeworkBaseDAOImpl extends AbstractBaseDAO<HomeworkBase, Integer>
		implements HomeworkBaseDAO {

	@Override
	public SearchAndCountModel<HomeworkBase> getPageAndCount(
			HomeworkBaseModel homeworkModel) {
		SearchAndCountModel<HomeworkBase> resultModel = new SearchAndCountModel<HomeworkBase>();
		List<HomeworkBase> list = new ArrayList<HomeworkBase>();

		//String sql = "From HomeworkBase as hb left join hb.archives with hb.className = :className ";
		Session session = null;
		int total;

		try {
			session = getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getSessionFactory().openSession();
		}

		Criteria criteria = session.createCriteria(HomeworkBase.class, "homeworkBase");

		for (SimpleExpression exp : homeworkModel.wrapperFilter()) {
			criteria.add(exp);
		}
		
		//criteria.setFetchMode("homeworkBase.archives", FetchMode.JOIN);
		
		criteria.createCriteria("homeworkBase.archives", JoinType.LEFT_OUTER_JOIN);
//		criteria.createAlias("homeworkBase.archives", "archives");

		total = ((Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();

		criteria.setProjection(null);

		list = criteria.setFirstResult(homeworkModel.getFirst())
				.setMaxResults(homeworkModel.getPageSize()).setCacheable(true)
				.list();

		resultModel.setTotal(total);
		resultModel.setRows(list);
		

		return resultModel;
	}
}
