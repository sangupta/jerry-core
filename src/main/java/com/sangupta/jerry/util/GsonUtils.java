/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class GsonUtils {

	/**
	 * Our holder for multiple instances
	 */
	private static final Map<FieldNamingPolicy, Gson> gsons = new HashMap<FieldNamingPolicy, Gson>();

	private static final JsonSerializer<Date> serializer = new JsonSerializer<Date>() {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(src.getTime());
		}

	};

	private static final JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
	
		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
	 * Method to fetch the singleton object with the specified naming policy. If
	 * one does not exist, it is created.
	 * 
	 * @return the {@link Gson} object for the given field naming policy
	 * 
	 */
	public static Gson getGson(FieldNamingPolicy fieldNamingPolicy) {
		if (gsons.containsKey(fieldNamingPolicy)) {
			return gsons.get(fieldNamingPolicy);
		}

		// create a new version
		Gson gson = new GsonBuilder()
						.setFieldNamingPolicy(fieldNamingPolicy)
						.registerTypeAdapter(Date.class, serializer)
						.registerTypeAdapter(Date.class, deserializer)
						.create();
		
		synchronized (gsons) {
			gsons.put(fieldNamingPolicy, gson);
		}

		// return the created instance
		return gson;
	}

}
