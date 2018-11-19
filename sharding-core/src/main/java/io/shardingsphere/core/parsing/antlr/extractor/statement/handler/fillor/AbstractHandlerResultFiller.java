/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler.fillor;

import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import lombok.RequiredArgsConstructor;

/**
 * Abstract handler result filler.
 *
 * @author duhongjun
 */
@RequiredArgsConstructor
public abstract class AbstractHandlerResultFiller implements HandlerResultFiller {

    protected final Class<?> extractResultClass;

    @Override
    public final void fill(final Object extractResult, final SQLStatement statement) {
        if (!compatClass(extractResult)) {
            return;
        }
        fillSQLStatement(extractResult, statement);
    }

    private boolean compatClass(final Object extractResult) {
        if (null == extractResultClass) {
            return false;
        }
        if (extractResultClass == extractResult.getClass()) {
            return true;
        }
        if (extractResultClass.isAssignableFrom(extractResult.getClass())) {
            return true;
        }
        return false;
    }

    /**
     * Fill result to SQLStatement.
     *
     * @param extractResult extract result from AST
     * @param statement SQL statement
     */
    protected abstract void fillSQLStatement(Object extractResult, SQLStatement statement);
}