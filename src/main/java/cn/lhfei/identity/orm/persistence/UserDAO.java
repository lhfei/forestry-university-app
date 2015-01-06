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
package cn.lhfei.identity.orm.persistence;

import cn.lhfei.identity.orm.domain.User;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 5, 2015
 */

public interface UserDAO extends GenericDAO<User, Integer> {

}
