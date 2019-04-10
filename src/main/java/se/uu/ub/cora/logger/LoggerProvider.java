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

import java.util.ServiceLoader;

import se.uu.ub.cora.logger.starter.LoggerModuleStarter;
import se.uu.ub.cora.logger.starter.LoggerModuleStarterImp;

public class LoggerProvider {

	private static LoggerFactory loggerFactory;
	private static LoggerModuleStarter loggerModuleStarter = new LoggerModuleStarterImp();

	private LoggerProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a Logger for the entered class using an implementation of LoggerFactory, that will be
	 * loaded through javas ServiceLoader load method.
	 * 
	 * @param javaClass
	 *            A Class to get a Logger for
	 * @return Logger for the provided class
	 */
	public static Logger getLoggerForClass(Class<? extends Object> javaClass) {
		ensureLoggerFactoryIsSet();
		return loggerFactory.factorForClass(javaClass);
	}

	private static synchronized void ensureLoggerFactoryIsSet() {
		if (null == loggerFactory) {
			getLoggerFactoryImpUsingModuleStarter();
		}
	}

	private static void getLoggerFactoryImpUsingModuleStarter() {
		Iterable<LoggerFactory> loggerFactoryImplementations = ServiceLoader
				.load(LoggerFactory.class);
		loggerModuleStarter.startUsingLoggerFactoryImplementations(loggerFactoryImplementations);
		loggerFactory = loggerModuleStarter.getLoggerFactory();
	}

	/**
	 * Sets a LoggerFactory that will be used to factor loggers for Classes. This possibility to set
	 * a LoggerFactory is provided to enable testing of logging in other classes and is not intented
	 * to be used in production. The LoggerFactory to use should be provided through an
	 * implementation of LoggerFactory in a seperate java module.
	 * 
	 * @param loggerFactory
	 *            A LoggerFactory to use to create loggers for testing
	 */
	public static void setLoggerFactory(LoggerFactory loggerFactory) {
		LoggerProvider.loggerFactory = loggerFactory;
	}

	static LoggerModuleStarter getStarter() {
		return loggerModuleStarter;
	}

	static void setStarter(LoggerModuleStarter starter) {
		loggerModuleStarter = starter;
	}

}
