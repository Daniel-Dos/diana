/*
 *
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 *
 */

package org.jnosql.diana.api.document.query;


import org.jnosql.diana.api.document.DocumentQuery;

/**
 * The Document Where whose define the condition in the query.
 */
public interface DocumentWhere {


    /**
     * Starts a new condition in the select using {@link org.jnosql.diana.api.document.DocumentCondition#and(org.jnosql.diana.api.document.DocumentCondition)}
     *
     * @param name a condition to be added
     * @return the same {@link DocumentNameCondition} with the condition appended
     * @throws NullPointerException when condition is null
     */
    DocumentNameCondition and(String name) throws NullPointerException;

    /**
     * Appends a new condition in the select using {@link org.jnosql.diana.api.document.DocumentCondition#or(org.jnosql.diana.api.document.DocumentCondition)}
     *
     * @param name a condition to be added
     * @return the same {@link DocumentNameCondition} with the condition appended
     * @throws NullPointerException when condition is null
     */
    DocumentNameCondition or(String name) throws NullPointerException;

    /**
     * Defines the position of the first result to retrieve.
     *
     * @param start the first result to retrive
     * @return a query with first result defined
     */
    DocumentStart start(long start);


    /**
     * Defines the maximum number of results to retrieve.
     *
     * @param limit the limit
     * @return a query with the limit defined
     */
    DocumentLimit limit(long limit);

    /**
     * Add the order how the result will returned
     *
     * @param name the order
     * @return a query with the sort defined
     * @throws NullPointerException when name is null
     */
    DocumentOrder orderBy(String name)  throws NullPointerException;

    /**
     * Creates a new instance of {@link DocumentQuery}
     *
     * @return a new {@link DocumentQuery} instance
     */
    DocumentQuery build();
}
