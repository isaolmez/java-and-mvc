package com.isa.java.mvc.core.router.rules;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Predicate;

public interface RouteRule extends Predicate<HttpServletRequest> {

}
