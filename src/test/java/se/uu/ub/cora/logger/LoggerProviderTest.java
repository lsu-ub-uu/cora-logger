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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.logger.starter.LoggerModuleStarter;
import se.uu.ub.cora.logger.starter.LoggerModuleStarterImp;

public class LoggerProviderTest {
	@BeforeMethod
	public void beforeMethod() {
		LoggerProvider.setLoggerFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<LoggerProvider> constructor = LoggerProvider.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<LoggerProvider> constructor = LoggerProvider.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testLogger() throws Exception {
		LoggerFactorySpy loggerFactory = new LoggerFactorySpy();
		LoggerProvider.setLoggerFactory(loggerFactory);
		Logger log = LoggerProvider.getLoggerForClass(String.class);

		assertEquals(loggerFactory.classNames.get(0), String.class.getName());
		assertEquals(log, loggerFactory.factored);
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		LoggerModuleStarterSpy starter = startLoggerModuleInitializerWithStarterSpy();
		LoggerProvider.getLoggerForClass(String.class);
		assertTrue(starter.startWasCalled);
	}

	private LoggerModuleStarterSpy startLoggerModuleInitializerWithStarterSpy() {
		LoggerModuleStarter starter = new LoggerModuleStarterSpy();
		LoggerProvider.setStarter(starter);
		return (LoggerModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		LoggerModuleStarter starter = LoggerProvider.getStarter();
		assertStarterIsGatekeeperModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			LoggerProvider.getLoggerForClass(String.class);
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof LoggerInitializationException);
		assertEquals(caughtException.getMessage(), "No implementations found for LoggerFactory");
	}

	private void assertStarterIsGatekeeperModuleStarter(LoggerModuleStarter starter) {
		assertTrue(starter instanceof LoggerModuleStarterImp);
	}

}
