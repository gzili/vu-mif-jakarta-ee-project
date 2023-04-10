package lt.vu.mif.api.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logged
@Interceptor
public class LoggingInterceptor {
    private final Logger logger = Logger.getLogger(LoggingInterceptor.class.getCanonicalName());

    @AroundInvoke
    public Object logInvocation(InvocationContext ctx) throws Exception {
        logger.info(ctx.getMethod().getDeclaringClass().getCanonicalName() + "." + ctx.getMethod().getName() + " called");
        return ctx.proceed();
    }
}
