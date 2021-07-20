/*
 * @Date created: 7/12/21, 8:43 PM
 * @Last-Modified: 7/12/21, 8:43 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

/*
 * @Date created: 7/12/21, 8:41 PM
 * @Last-Modified: 7/12/21, 8:41 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.common.builder;

import java.util.List;

/**
 * Abstract base class for builders. Contains helper methods.
 */
public abstract class AbstractSqlBuilder {
    /**
     * Constructs a list of items with given separators.
     *
     * @param sql  StringBuilder to which the constructed string will be
     *             appended.
     * @param list List of objects (usually strings) to join.
     * @param init String to be added to the start of the list, before any of the
     *             items.
     * @param sep  Separator string to be added between items in the list.
     */
    protected void appendList(StringBuilder sql, List<?> list, String init, String sep) {
        boolean first = true;

        for (Object s : list) {
            if (first) {
                sql.append(init);
            } else {
                sql.append(sep);
            }
            sql.append(s);
            first = false;
        }
    }

    public abstract String build();

}
