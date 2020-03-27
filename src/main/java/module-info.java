/**
 * Module logger provides interfaces and access needed to use logging in a Cora based system. The
 * logger implementation in use can be accessed through the {@link LoggerProvider} class and its
 * getLoggerForClass method.
 * <p>
 * Logging implementations are provided through other modules and MUST implement the
 * {@link LoggerFactory} interface.
 * 
 * @uses se.uu.ub.cora.logger.LoggerFactory
 */
module se.uu.ub.cora.logger {
	exports se.uu.ub.cora.logger;

	uses se.uu.ub.cora.logger.LoggerFactory;
}