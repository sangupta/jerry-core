/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sangupta.jerry.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.sangupta.jerry.ds.SimpleMultiMap;

/**
 * Cache class that holds various {@link Gson} instances based on their field
 * naming policy. This class is highly recommended for situations where lot of
 * JSON parsing will be done.
 *
 * This class is not recommended for one of usage of {@link Gson} objects as
 * they live in cache for-ever.
 *
 * @author sangupta
 *
 */
public abstract class GsonUtils {

	/**
	 * Our holder for multiple instances
	 */
	private static final Map<FieldNamingPolicy, Gson> gsons = new HashMap<FieldNamingPolicy, Gson>();

	/**
	 * Holds custom adapters
	 */
	private static final SimpleMultiMap<Type, Object> customAdapters = new SimpleMultiMap<Type, Object>();

	/**
	 * The date serializer to long
	 */
	private static final JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>() {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(src.getTime());
		}

	};

	/**
	 * The date deserializer from <code>long<code>
	 */
	private static final JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {

		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return json == null ? null : new Date(json.getAsLong());
		}

	};

	/**
	 * Returns the {@link Gson} instance based on the
	 * {@link FieldNamingPolicy#IDENTITY}.
	 *
	 * @return the {@link Gson} object
	 */
	public static Gson getGson() {
		return getGson(FieldNamingPolicy.IDENTITY);
	}

	/**
	 * Method to fetch the singleton object with the specified naming policy. If one
	 * does not exist, it is created.
	 *
	 * @param fieldNamingPolicy the field naming policy for which the {@link Gson}
	 *                          instance is required
	 *
	 * @return the {@link Gson} object for the given field naming policy
	 *
	 */
	public static Gson getGson(FieldNamingPolicy fieldNamingPolicy) {
		if (gsons.containsKey(fieldNamingPolicy)) {
			return gsons.get(fieldNamingPolicy);
		}

		// create a new version
		GsonBuilder gsonBuilder = new GsonBuilder().setFieldNamingPolicy(fieldNamingPolicy)
				.registerTypeAdapter(Date.class, dateSerializer).registerTypeAdapter(Date.class, dateDeserializer);

		registerMoreTypeAdapters(gsonBuilder);

		Gson gson = gsonBuilder.create();
		synchronized (gsons) {
			gsons.put(fieldNamingPolicy, gson);
		}

		// return the created instance
		return gson;
	}

	/**
	 * Register a new custom type adapter.
	 *
	 * @param type    the type for which the adapter is to be added
	 *
	 * @param adapter the adapter itself
	 */
	public static void addCustomTypeAdapter(Type type, Object adapter) {
		customAdapters.put(type, adapter);
	}

	/**
	 * Clear all previously generated {@link Gson} instances.
	 *
	 */
	public static void clearAllGsons() {
		gsons.clear();
	}
	
	/**
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 * @throws JsonSyntaxException
	 * @since 3.1.0
	 */
	public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
		return getGson().fromJson(json, classOfT);
	}

	/**
	 * 
	 * @param json
	 * @param typeOfT
	 * @return
	 * @throws JsonSyntaxException
	 * @since 3.1.0
	 */
	public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
		return getGson().fromJson(json, typeOfT);
	}

	/**
	 * 
	 * @param source
	 * @return
	 * @since 3.1.0
	 */
	public static String toJson(Object source) {
		return getGson().toJson(source);
	}

	/**
	 * 
	 * @param jsonElement
	 * @return
	 * @since 3.1.0
	 */
	public static String toJson(JsonElement jsonElement) {
		return getGson().toJson(jsonElement);
	}

	/**
	 * 
	 * @param source
	 * @param file
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static void toFile(Object source, File file) throws IOException {
		FileUtils.writeStringToFile(file, toJson(source), Charset.defaultCharset());
	}

	/**
	 * 
	 * @param source
	 * @param file
	 * @param encoding
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static void toFile(Object source, File file, Charset encoding) throws IOException {
		FileUtils.writeStringToFile(file, toJson(source), encoding);
	}

	/**
	 * 
	 * @param jsonElement
	 * @param file
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static void toFile(JsonElement jsonElement, File file) throws IOException {
		FileUtils.writeStringToFile(file, toJson(jsonElement), Charset.defaultCharset());
	}
	
	/**
	 * 
	 * @param classOfT
	 * @param file
	 * @return
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static <T> T fromFile(Class<T> classOfT, File file) throws IOException {
		return GsonUtils.fromFile(classOfT, file, Charset.defaultCharset());
	}
	
	/**
	 * 
	 * @param classOfT
	 * @param file
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static <T> T fromFile(Class<T> classOfT, File file, Charset encoding) throws IOException {
		return getGson().fromJson(FileUtils.readFileToString(file, encoding), classOfT);
	}

	/**
	 * 
	 * @param jsonElement
	 * @param file
	 * @param encoding
	 * @throws IOException
	 * @since 3.1.0
	 */
	public static void toFile(JsonElement jsonElement, File file, Charset encoding) throws IOException {
		FileUtils.writeStringToFile(file, toJson(jsonElement), encoding);
	}

	/**
	 * Remove the {@link Gson} instance against the given {@link FieldNamingPolicy}
	 * so that it is regenerated again.
	 *
	 * @param policy the {@link FieldNamingPolicy} for which to remove previosuly
	 *               created {@link Gson} instance is to be removed
	 */
	public static void clearGson(FieldNamingPolicy policy) {
		gsons.remove(policy);
	}

	/**
	 * Remove all custom type adapters for the given type
	 *
	 * @param type the type for which adapters are to be removed
	 */
	public static void removeTypeAdapters(Type type) {
		customAdapters.remove(type);
	}

	/**
	 * Register custom adapters for Gson
	 *
	 * @param gsonBuilder
	 */
	private static void registerMoreTypeAdapters(GsonBuilder gsonBuilder) {
		if (customAdapters == null || customAdapters.isEmpty()) {
			return;
		}

		Set<Type> keySet = customAdapters.keySet();
		for (Type key : keySet) {
			List<Object> values = customAdapters.getValues(key);
			if (values != null) {
				for (Object value : values) {
					gsonBuilder.registerTypeAdapter(key, value);
				}
			}
		}
	}

}
