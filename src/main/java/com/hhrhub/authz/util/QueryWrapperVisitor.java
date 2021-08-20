package com.hhrhub.authz.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

import java.util.List;

public class QueryWrapperVisitor<T> implements RSQLVisitor<QueryWrapper<T>, QueryWrapper<T>> {

    @Override
    public QueryWrapper<T> visit(AndNode node, QueryWrapper<T> queryWrapper) {
        for (Node next : node.getChildren()) {
            if (next instanceof AndNode) {
                queryWrapper.and(i -> visit((AndNode) next, i));
            } else if (next instanceof OrNode) {
                queryWrapper.and(i -> visit((OrNode) next, i));
            } else if (next instanceof ComparisonNode) {
                queryWrapper.and(i -> visit((ComparisonNode) next, i));
            }
        }
        return queryWrapper;
    }

    @Override
    public QueryWrapper<T> visit(OrNode node, QueryWrapper<T> queryWrapper) {
        for (Node next : node.getChildren()) {
            if (next instanceof AndNode) {
                queryWrapper.or(i -> visit((AndNode) next, i));
            } else if (next instanceof OrNode) {
                queryWrapper.or(i -> visit((OrNode) next, i));
            } else if (next instanceof ComparisonNode) {
                queryWrapper.or(i -> visit((ComparisonNode) next, i));
            }
        }
        return queryWrapper;
    }

    @Override
    public QueryWrapper<T> visit(ComparisonNode node, QueryWrapper<T> queryWrapper) {
        ComparisonOperator operator = node.getOperator();
        String selector = node.getSelector();
        List<String> arguments = node.getArguments();
        if (ComparisonOperatorProxy.asEnum(operator) != null) {
            switch (ComparisonOperatorProxy.asEnum(operator)) {
                case EQUAL:
                    return queryWrapper.eq(selector, arguments.get(0));
                case NOT_EQUAL:
                    return queryWrapper.ne(selector, arguments.get(0));
                case GREATER_THAN:
                    return queryWrapper.gt(selector, arguments.get(0));
                case GREATER_THAN_OR_EQUAL:
                    return queryWrapper.ge(selector, arguments.get(0));
                case LESS_THAN:
                    return queryWrapper.lt(selector, arguments.get(0));
                case LESS_THAN_OR_EQUAL:
                    return queryWrapper.le(selector, arguments.get(0));
                case IN:
                    return queryWrapper.in(selector, arguments);
                case NOT_IN:
                    return queryWrapper.notIn(selector, arguments);
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + operator);
    }
}
