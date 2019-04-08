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

package se.uu.ub.cora.logger.starter;

import static org.testng.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.logger.LoggerFactory;
import se.uu.ub.cora.logger.LoggerFactorySpy;
import se.uu.ub.cora.logger.LoggerInitializationException;

public class LoggerModuleStarterTest {
	LoggerModuleStarterImp starter;
	List<LoggerFactory> loggerFactoryImplementations;
	LoggerFactorySpy loggerFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new LoggerModuleStarterImp();
		loggerFactoryImplementations = new ArrayList<>();
		loggerFactorySpy = new LoggerFactorySpy();

	}

	@Test(expectedExceptions = LoggerInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for LoggerFactory")
	public void testStartModuleThrowsErrorIfNoLoggerFactoryImplementations() throws Exception {
		starter.startUsingLoggerFactoryImplementations(loggerFactoryImplementations);
	}

	@Test(expectedExceptions = LoggerInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for LoggerFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneUserStorageImplementations()
			throws Exception {
		loggerFactoryImplementations.add(new LoggerFactorySpy());
		loggerFactoryImplementations.add(new LoggerFactorySpy());
		starter.startUsingLoggerFactoryImplementations(loggerFactoryImplementations);
	}

	@Test
	public void testGetLoggerFactory() throws Exception {
		loggerFactoryImplementations.add(loggerFactorySpy);
		starter.startUsingLoggerFactoryImplementations(loggerFactoryImplementations);
		assertSame(starter.getLoggerFactory(), loggerFactorySpy);
	}
}
