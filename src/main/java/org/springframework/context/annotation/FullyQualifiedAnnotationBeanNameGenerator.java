package org.springframework.context.annotation;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * Compatibility shim aligned with Spring Framework 6 behavior.
 *
 * Spring 6 provides this class and exposes a public static {@code INSTANCE}
 * field. Some framework code references that field directly. If this shim is
 * present on the classpath and doesn't provide the {@code INSTANCE} field, a
 * NoSuchFieldError can occur at runtime. To remain compatible, we provide the
 * same API and behavior: generate fully-qualified bean names by default and
 * expose a singleton {@code INSTANCE}.
 */
public class FullyQualifiedAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

	public static final FullyQualifiedAnnotationBeanNameGenerator INSTANCE =
			new FullyQualifiedAnnotationBeanNameGenerator();

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		// Use the fully qualified class name as the default bean name
		return definition.getBeanClassName();
	}
}
