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

import java.util.function.Supplier;

public interface Logger {
	/**
	 * Severe errors that cause the application to malfunction.
	 * 
	 * @param message
	 */
	public void logFatalUsingMessage(String message);

	/**
	 * Severe errors that cause the application to malfunction.
	 * 
	 * @param message
	 * @param exception
	 */
	public void logFatalUsingMessageAndException(String message, Exception exception);

	/**
	 * Runtime errors that cause problems for requests to the application, but not the entire
	 * application.
	 * 
	 * @param message
	 */
	public void logErrorUsingMessage(String message);

	/**
	 * Runtime errors that cause problems for requests to the application, but not the entire
	 * application.
	 * 
	 * @param message
	 * @param exception
	 */
	public void logErrorUsingMessageAndException(String message, Exception exception);

	/**
	 * Runtime events that are unexpected or undesired but are handled by the application and does
	 * not cause errors.
	 * 
	 * @param message
	 */
	public void logWarnUsingMessage(String message);

	/**
	 * Runtime events that are unexpected or undesired but are handled by the application and does
	 * not cause errors.
	 * 
	 * @param message
	 * @param exception
	 */
	public void logWarnUsingMessageAndException(String message, Exception exception);

	/**
	 * Runtime events and configurations that are expected and give useful information about the
	 * application, such as the currently active configuration parameters, services starting /
	 * stopping etc.
	 * 
	 * @param message
	 */
	public void logInfoUsingMessage(String message);

	/**
	 * Information about data flow through the application, normally used for debugging purposes.
	 * 
	 * @param message
	 */
	public void logDebugUsingMessage(String message);

	/**
	 * Information about data flow through the application, normally used for debugging purposes.The
	 * supplier is only called if trace level is enabled
	 * 
	 * @return
	 */
	public void logDebugUsingMessageSupplier(Supplier<String> messageSupplier);

	/**
	 * Extremely detailed information about data flow through the application, normally used for
	 * debugging purposes.
	 * 
	 * @param message
	 */
	public void logTraceUsingMessage(String message);

	/**
	 * Extremely detailed information about data flow through the application, normally used for
	 * debugging purposes. The supplier is only called if trace level is enabled.
	 * 
	 * @param messageSupplier
	 */
	public void logTraceUsingMessageSupplier(Supplier<String> messageSupplier);
}
