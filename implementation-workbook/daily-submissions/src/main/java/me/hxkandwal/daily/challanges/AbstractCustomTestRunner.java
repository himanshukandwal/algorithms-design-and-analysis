package me.hxkandwal.daily.challanges;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A simple test runner to execute multiple similar test cases, starting with '_'. Example :
 * 
 * 	_methodWithImplementation
 * 	_methodWithAnotherImplementation 
 * 
 * @author Hxkandwal
 *
 */
public class AbstractCustomTestRunner {
	
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Object answer = null;

		try {
			answer = method.invoke(this, externalVariables);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return answer;
	}
	
	public List<Object> runAll(Class<?> clazz, Object[] externalVariables) {
		List<Object> results = new ArrayList<>();
		
		Stream.of(clazz.getDeclaredMethods())
				.filter(method -> method.getName().startsWith("_"))
				.forEach(method -> results.add(coreTestRun(method, externalVariables)));

		return results;
	}

}
