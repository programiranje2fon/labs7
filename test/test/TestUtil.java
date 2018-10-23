package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Utility methods used for test execution.
 */
public class TestUtil {

	/**
	 * Returns a value of a field named 'fieldName' from the passed object.
	 * 
	 * @param obj
	 *            object to retrieve the field value from
	 * @param fieldName
	 *            field name
	 * @param failMessage
	 *            message to be written with a failed test in case there is no
	 *            field with the given name
	 * @return value of the field (to be casted to the proper type)
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		try {
			// collect all fields from the obj class and all super classes
			List<Field> result = new ArrayList<Field>();

			Class<?> clazz = obj.getClass();
			while (clazz != null && clazz != Object.class) {
				Collections.addAll(result, clazz.getDeclaredFields());
				clazz = clazz.getSuperclass();
			}

			// iterate through all fields
			for (Field field : result) {
				if (field.getName().equals(fieldName)) {
					field.setAccessible(true);

					return field.get(obj);
				}
			}
		} catch (IllegalAccessException e1) {
		}
		return null;
	}

	public static boolean doesFieldExist(Class<?> c, String fieldName) {
		try {
			c.getDeclaredField(fieldName);
			
			return true;
		} catch (NoSuchFieldException e1) {
			return false;
		}
	}
	
	public static boolean hasFieldModifier(Class<?> c, String fieldName, int modifier) {
		try {
			Field field = c.getDeclaredField(fieldName);

			switch (modifier) {
			case Modifier.PUBLIC:
				return Modifier.isPublic(field.getModifiers());
			case Modifier.PRIVATE:
				return Modifier.isPrivate(field.getModifiers());
			case Modifier.PROTECTED:
				return Modifier.isProtected(field.getModifiers());
			case 0:	// package scope
				return field.getModifiers() == 0;
			default:
				return false;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasMethodModifier(Class<?> c, String methodName, Class<?>[] parameterTypes, int modifier) {
		try {
			Method method = c.getDeclaredMethod(methodName, parameterTypes);
			
			switch (modifier) {
			case Modifier.PUBLIC:
				return Modifier.isPublic(method.getModifiers());
			case Modifier.PRIVATE:
				return Modifier.isPrivate(method.getModifiers());
			case Modifier.PROTECTED:
				return Modifier.isProtected(method.getModifiers());
			case 0:	// package scope
				return method.getModifiers() == 0;
			default:
				return false;
			}
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasConstructorModifier(Class<?> c, Class<?>[] parameterTypes, int modifier) {
		try {
			Constructor<? extends Object> constructor = c.getDeclaredConstructor(parameterTypes);
			
			switch (modifier) {
			case Modifier.PUBLIC:
				return Modifier.isPublic(constructor.getModifiers());
			case Modifier.PRIVATE:
				return Modifier.isPrivate(constructor.getModifiers());
			case Modifier.PROTECTED:
				return Modifier.isProtected(constructor.getModifiers());
			case 0:	// package scope
				return constructor.getModifiers() == 0;
			default:
				return false;
			}
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}


	
	
	/*
	 * This code was copied from the https://dzone.com/articles/get-all-classes-within-package
	 */

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 *
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		
		ArrayList<Class<?>> classes = new ArrayList<>();
		
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 *
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		
		File[] files = directory.listFiles();
		
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}
