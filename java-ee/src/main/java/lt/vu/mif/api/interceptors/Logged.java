package lt.vu.mif.api.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

@InterceptorBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logged {
}
