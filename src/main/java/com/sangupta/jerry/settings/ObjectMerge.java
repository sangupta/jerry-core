package com.sangupta.jerry.settings;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.ReflectionUtils;

public class ObjectMerge {
	
	public static <T> T merge(Class<T> classOfT, T instance, List<T> settings) throws InstantiationException, IllegalAccessException {
		if(AssertUtils.isEmpty(settings)) {
			return instance;
		}
		
		// check if we can sort this list or not
		if(MergeComparable.class.isAssignableFrom(classOfT)) {
			// sort settings list
			settings.sort(new Comparator<T>() {
	
				@Override
				public int compare(T o1, T o2) {
					MergeComparable p1 = (MergeComparable) o1;
					MergeComparable p2 = (MergeComparable) o2;
					return p1.compareForMerge(p2);
				}
				
			});
		}

		// now start copying fields
		List<Field> fields = ReflectionUtils.getAllFields(classOfT);
		for(Field field : fields) {
			// for each field, find the value in each object
			for(T setting : settings) {
				copyFieldIfApplicable(field, instance, setting);
			}
		}
		
		
		return instance;
	}

	private static <T> void copyFieldIfApplicable(Field field, T destination, T source) throws IllegalArgumentException, IllegalAccessException {
		Class<?> type = field.getType();

		// set accessible so that we can work with private fields
		field.setAccessible(true);
		
		// obtain current source value
		Object srcValue = field.get(source);
		
		ConflictResolution resolution = field.getAnnotation(ConflictResolution.class);
		ConflictResolutionPolicy policy = resolution.policy();
		
		switch (policy) {
		case BLIND_COPY:
			field.set(destination, srcValue);
			return;
			
		case INTERSECTION:
			break;
		
		case MAX:
			break;
		
		case MIN:
			break;
		
		case NON_ZERO:
			if(ReflectionUtils.isFieldNumber(type)) {
				if(ReflectionUtils.isEqualNumber(srcValue, 0)) {
					
				}
			}
			break;
			
		case NON_NULL:
			if(srcValue != null) {
				field.set(destination, srcValue);
			}
			return;
		
		case UNION:
			break;
		
		default:
			throw new RuntimeException("Unknown conflict resolution policy");
		
		}
	}

}
