/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package io.narayana;

import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@Path("/")
public class StringsResource {

    private final StringDao stringDao;

    private final TransactionManager transactionManager;

    public StringsResource() throws NamingException, SQLException {
        stringDao = new StringDao();
        transactionManager = InitialContext.doLookup("java:comp/env/TransactionManager");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStrings() throws SQLException {
        System.out.println(this.getClass().getSimpleName() + " GET");
        return stringDao.getAll();
    }

    @POST
    public void saveString(String string) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " POST");
        System.out.println(this.getClass().getSimpleName() + " begin transaction");
        transactionManager.begin();
        System.out.println(this.getClass().getSimpleName() + " save string");
        stringDao.save(string);
        System.out.println(this.getClass().getSimpleName() + " commit transaction");
        transactionManager.commit();
        System.out.println(this.getClass().getSimpleName() + " transaction committed successfully");
    }

}
