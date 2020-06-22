package com.cognizant.jaxrstest;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class JustInTimeServiceResolver implements JustInTimeInjectionResolver {
	@Inject
	private ServiceLocator serviceLocator;
	@Override
	public boolean justInTimeResolution(Injectee failedInjectionPoint) {
		final Type requiredType = failedInjectionPoint.getRequiredType();
		if (failedInjectionPoint.getRequiredQualifiers().isEmpty() && requiredType instanceof Class) {
			final Class<?> requiredClass = (Class<?>) requiredType;
			if(requiredClass.getName().startsWith("com.cognizant")) {
				final List<ActiveDescriptor<?>> descriptors = ServiceLocatorUtilities.addClasses(serviceLocator,  requiredClass);
				return !descriptors.isEmpty();
			}
		}
		return false;
	}

}
