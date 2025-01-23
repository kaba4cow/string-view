package com.kaba4cow.stringview;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as maps.
 */
public class MapStringView extends StringView {

	/**
	 * Constructs a {@link MapStringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public MapStringView(CharSequence string) {
		super(string);
	}

	/**
	 * Converts the string to a map where keys and values are determined by the provided key-value delimiter and conversion
	 * functions. The string is split by the specified entry delimiter.
	 * 
	 * @param delimiter     the delimiter to split the entries in the string
	 * @param keyFunction   the function to convert a part of the string into a key
	 * @param valueFunction the function to convert a part of the string into a value
	 * @param <K>           the type of the keys in the map
	 * @param <V>           the type of the values in the map
	 * 
	 * @return a map containing the converted keys and values
	 * 
	 * @throws StringViewException if the string cannot be converted to a map
	 */
	public <K, V> Map<K, V> asMap(String delimiter, Function<String, K> keyFunction, Function<String, V> valueFunction) {
		try {
			String[] parts = string.split(delimiter);
			Map<K, V> map = new LinkedHashMap<>();
			for (int i = 0; i < parts.length; i += 2)
				map.put(//
						keyFunction.apply(parts[i]), //
						(i < parts.length - 1) ? valueFunction.apply(parts[i + 1]) : null);
			return map;
		} catch (Exception exception) {
			throw new StringViewException("map", exception);
		}
	}

	/**
	 * Converts the string to a map where keys and values are determined by the provided key-value delimiter and conversion
	 * functions. The string is split by the specified entry delimiter.
	 * 
	 * @param entryDelimiter    the delimiter to split the entries in the string
	 * @param keyValueDelimiter the delimiter to split the key and value in each entry
	 * @param keyFunction       the function to convert a part of the string into a key
	 * @param valueFunction     the function to convert a part of the string into a value
	 * @param <K>               the type of the keys in the map
	 * @param <V>               the type of the values in the map
	 * 
	 * @return a map containing the converted keys and values
	 * 
	 * @throws StringViewException if the string cannot be converted to a map
	 */
	public <K, V> Map<K, V> asMap(String entryDelimiter, String keyValueDelimiter, Function<String, K> keyFunction,
			Function<String, V> valueFunction) {
		try {
			String[] parts = string.split(entryDelimiter);
			Map<K, V> map = new LinkedHashMap<>();
			for (int i = 0; i < parts.length; i++) {
				String[] pair = parts[i].split(keyValueDelimiter);
				if (pair.length > 0)
					map.put(//
							keyFunction.apply(pair[0]), //
							pair.length > 1 ? valueFunction.apply(pair[1]) : null);
			}
			return map;
		} catch (Exception exception) {
			throw new StringViewException("map", exception);
		}
	}

}
