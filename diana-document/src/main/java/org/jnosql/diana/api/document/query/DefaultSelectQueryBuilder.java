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


import org.jnosql.diana.api.Sort;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * The default implementation of the Select in the document
 */
class DefaultSelectQueryBuilder extends BaseQueryBuilder implements DocumentSelect, DocumentFrom, DocumentLimit,
        DocumentStart, DocumentOrder, DocumentWhereName, DocumentNotCondition, DocumentNameOrder, DocumentWhere {


    private String documentCollection;

    private long start;

    private long limit;

    private final List<Sort> sorts = new ArrayList<>();

    private final List<String> documents;


    DefaultSelectQueryBuilder(List<String> documents) {
        this.documents = documents;
    }


    @Override
    public DocumentFrom from(String documentCollection) throws NullPointerException {
        requireNonNull(documentCollection, "documentCollection is required");
        this.documentCollection = documentCollection;
        return this;
    }


    @Override
    public DocumentWhereName where(String name) throws NullPointerException {
        requireNonNull(name, "name is required");
        this.name = name;
        return this;
    }

    @Override
    public DocumentNameCondition and(String name) throws NullPointerException {
        requireNonNull(name, "name is required");
        this.name = name;
        this.and = true;
        return this;
    }

    @Override
    public DocumentNameCondition or(String name) throws NullPointerException {
        requireNonNull(name, "name is required");
        this.name = name;
        this.and = false;
        return this;
    }

    @Override
    public DocumentStart start(long start) {
        this.start = start;
        return this;
    }

    @Override
    public DocumentLimit limit(long limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public DocumentOrder orderBy(String name) throws NullPointerException {
        requireNonNull(name, "name is required");
        this.name = name;
        return this;
    }


    @Override
    public DocumentNotCondition not() {
        this.negate = true;
        return this;
    }

    @Override
    public <T> DocumentWhere eq(T value) throws NullPointerException {
        eqImpl(value);
        return this;
    }

    @Override
    public DocumentWhere like(String value) throws NullPointerException {
        likeImpl(value);
        return this;
    }

    @Override
    public DocumentWhere gt(Number value) throws NullPointerException {
        gtImpl(value);
        return this;
    }

    @Override
    public DocumentWhere gte(Number value) throws NullPointerException {
        gteImpl(value);
        return this;
    }

    @Override
    public DocumentWhere lt(Number value) throws NullPointerException {
        ltImpl(value);
        return this;
    }


    @Override
    public DocumentWhere lte(Number value) throws NullPointerException {
        lteImpl(value);
        return this;
    }

    @Override
    public DocumentWhere between(Number valueA, Number valueB) throws NullPointerException {
        betweenImpl(valueA, valueB);
        return this;
    }


    @Override
    public <T> DocumentWhere in(Iterable<T> values) throws NullPointerException {
        inImpl(values);
        return this;
    }


    @Override
    public DocumentNameOrder asc() {
        this.sorts.add(Sort.of(name, Sort.SortType.ASC));
        return this;
    }

    @Override
    public DocumentNameOrder desc() {
        this.sorts.add(Sort.of(name, Sort.SortType.DESC));
        return this;
    }


    @Override
    public DocumentQuery build() {
        return new DefaultDocumentQuery(limit, start, documentCollection, documents, sorts, condition);
    }


}
