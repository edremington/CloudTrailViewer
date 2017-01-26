/*
CloudTrail Viewer, is a Java desktop application for reading AWS CloudTrail logs
files.

Copyright (C) 2017  Mark P. Haskins

This program is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package io.haskins.java.cloudtrailviewer.model.dao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *
 * Created by markhaskins on 05/01/2017.
 */
public class ResultSetRow extends LinkedHashMap {

    private static final long serialVersionUID = 5573616437008138154L;

    /**
     * Default constructor
     */
    public ResultSetRow() {
        super();
    }

    public ResultSetRow(int initialCapacity) {
        super(initialCapacity);
    }

    public ResultSetRow(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ResultSetRow(Map m) {
        super(m);
    }

    public Object get(String key)  {

        if (super.get(key) == null) {
            return super.get(key.toUpperCase());
        }

        return super.get(key);
    }
}

