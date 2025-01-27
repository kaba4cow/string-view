package com.kaba4cow.stringview;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as maps.
 */
public class MapStringView extends StringView {

	/**
	 * Constructs a {@link MapStringView} with the specified string.
	 *
	 * @param value the string to wrap
	 */
	public MapStringView(Object value) {
		super(value);
	}

	/**
	 * Converts the string to a map where keys and values are determined by the provided key-value delimiter and conversion
	 * functions.
	 * 
	 * @param <M>           the type of the map to be returned
	 * @param <K>           the type of the keys in the map
	 * @param <V>           the type of the values in the map
	 * @param delimiter     the delimiter to split the entries in the string
	 * @param keyFunction   the function to convert a part of the string into a key
	 * @param valueFunction the function to convert a part of the string into a value
	 * @param mapSupplier   a supplier that provides a new map instance
	 * 
	 * @return a map containing the converted keys and values
	 */
	public <M extends Map<K, V>, K, V> M asMap(String delimiter, Function<String, K> keyFunction,
			Function<String, V> valueFunction, Supplier<M> mapSupplier) {
		String[] parts = string.split(delimiter);
		M map = mapSupplier.get();
		for (int i = 0; i < parts.length; i += 2)
			map.put(keyFunction.apply(parts[i]), (i < parts.length - 1) ? valueFunction.apply(parts[i + 1]) : null);
		return map;
	}

	/**
	 * Converts the string to a map where keys and values are determined by the provided key-value delimiter and conversion
	 * functions.
	 * 
	 * @param <M>               the type of the map to be returned
	 * @param <K>               the type of the keys in the map
	 * @param <V>               the type of the values in the map
	 * @param entryDelimiter    the delimiter to split the entries in the string
	 * @param keyValueDelimiter the delimiter to split the key and value in each entry
	 * @param keyFunction       the function to convert a part of the string into a key
	 * @param valueFunction     the function to convert a part of the string into a value
	 * @param mapSupplier       a supplier that provides a new map instance
	 * 
	 * @return a map containing the converted keys and values
	 */
	public <M extends Map<K, V>, K, V> M asMap(String entryDelimiter, String keyValueDelimiter, Function<String, K> keyFunction,
			Function<String, V> valueFunction, Supplier<M> mapSupplier) {
		String[] parts = string.split(entryDelimiter);
		M map = mapSupplier.get();
		for (int i = 0; i < parts.length; i++) {
			String[] pair = parts[i].split(keyValueDelimiter);
			if (pair.length > 0)
				map.put(keyFunction.apply(pair[0]), pair.length > 1 ? valueFunction.apply(pair[1]) : null);
		}
		return map;
	}

}
