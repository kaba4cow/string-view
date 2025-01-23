package com.kaba4cow.stringview;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as lists.
 */
public class ListStringView extends StringView {

	/**
	 * Constructs a {@link ListStringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public ListStringView(CharSequence string) {
		super(string);
	}

	/**
	 * Converts the string to a list of elements of type {@link T} using the provided delimiter and conversion function.
	 * 
	 * @param delimiter the delimiter to split the string
	 * @param function  the function to convert each part of the string
	 * @param <T>       the type of elements in the list
	 * 
	 * @return a list of type {@link T} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public <T> List<T> asList(String delimiter, Function<String, T> function) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(function)//
					.collect(Collectors.toList());
		} catch (Exception exception) {
			throw new StringViewException("list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link String} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link String} containing the split parts
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<String> asStringList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("string list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Boolean} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Boolean} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Boolean> asBooleanList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Boolean::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("boolean list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Byte} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Byte} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Byte> asByteList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Byte::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("byte list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Short} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Short} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Short> asShortList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Short::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("short list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Integer} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Integer} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Integer> asIntList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Integer::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("int list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Long} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Long} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Long> asLongList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Long::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("long list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Float} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Float} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Float> asFloatList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Float::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("float list", exception);
		}
	}

	/**
	 * Converts the string to a list of {@link Double} elements using the provided delimiter.
	 * 
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a list of {@link Double} containing the converted elements
	 * 
	 * @throws StringViewException if the string cannot be converted to a list
	 */
	public List<Double> asDoubleList(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter))//
					.map(Double::valueOf)//
					.collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("double list", exception);
		}
	}

}
