/*
 * Copyright 2019 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.logger;

/**
 * This LoggerFactory is expected to be implemented by modules that provides implementations for
 * logging in a Cora based system, to factor implemented Logger instances.
 */
public interface LoggerFactory {

	/**
	 * Factors a Logger for the supplied class. The implementation SHOULD handle multiple factor
	 * calls for the same class as is appropriate for the implementation.
	 * 
	 * @param javaClass
	 *            to get a Logger for
	 * @return {@link Logger} for the supplied class
	 */
	Logger factorForClass(Class<? extends Object> javaClass);

}
