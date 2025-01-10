package com.kaba4cow.stringview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * The {@code StringView} class provides utility methods for parsing, converting, and manipulating strings in various ways. It
 * is designed to offer a flexible way to transform string data into different types, such as lists, maps, and various primitive
 * types.
 * </p>
 * <p>
 * This class allows for easy conversion of strings into collections like lists and maps, supporting different data types,
 * including {@link Integer}, {@link Boolean}, {@link Double}, {@link String}, and many more. It also supports extracting
 * substrings with wrapping indices, providing additional flexibility when working with string ranges.
 * </p>
 * <p>
 * The {@code StringView} class is an essential tool for working with and transforming string data in Java, providing powerful
 * utilities for working with text in a highly customizable and structured manner.
 * </p>
 * <p>
 * <b>Note</b>: All methods assume the input string is non-null, but null checks should be handled by the user if needed in
 * other parts of the code.
 * </p>
 */
public class StringView {

	private final String string;

	/**
	 * Constructs a {@link StringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public StringView(String string) {
		this.string = string;
	}

	/**
	 * Returns a new {@link StringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public static StringView view(String string) {
		return new StringView(string);
	}

	/**
	 * Trims the string by removing leading and trailing spaces.
	 * 
	 * @return a new {@link StringView} with the trimmed string
	 */
	public StringView trim() {
		return new StringView(string.trim());
	}

	/**
	 * Converts the string to uppercase.
	 * 
	 * @return a new {@link StringView} with the string in uppercase
	 */
	public StringView toUpperCase() {
		return new StringView(string.toUpperCase());
	}

	/**
	 * Converts the string to lowercase.
	 * 
	 * @return a new {@link StringView} with the string in lowercase
	 */
	public StringView toLowerCase() {
		return new StringView(string.toLowerCase());
	}

	/**
	 * Returns a substring starting from the specified index. The index is adjusted to handle wrapping around the string.
	 * 
	 * @param beginIndex the index to start the substring
	 * 
	 * @return a new {@link StringView} containing the substring
	 */
	public StringView substring(int beginIndex) {
		int length = string.length();
		beginIndex = (beginIndex % length + length) % length;
		return new StringView(string.substring(beginIndex));
	}

	/**
	 * Returns a substring starting from the specified begin index to the specified end index. Both indices are adjusted to
	 * handle wrapping around the string.
	 * 
	 * @param beginIndex the index to start the substring
	 * @param endIndex   the index to end the substring
	 * 
	 * @return a new {@link StringView} containing the substring
	 */
	public StringView substring(int beginIndex, int endIndex) {
		int length = string.length();
		beginIndex = (beginIndex % length + length) % length;
		endIndex = (endIndex % length + length) % length;
		return new StringView(string.substring(beginIndex, endIndex));
	}

	/**
	 * Concatenates the specified string to the end of this string.
	 *
	 * @param str the string to concatenate
	 * 
	 * @return a new {@link StringView} containing the concatenated string
	 */
	public StringView concat(String str) {
		return new StringView(string.concat(str));
	}

	/**
	 * Returns a new {@link StringView} resulting from replacing all occurrences of oldChar in this string with newChar.
	 *
	 * @param oldChar the character to be replaced
	 * @param newChar the character to replace with
	 * 
	 * @return a new {@link StringView} with all occurrences of oldChar replaced with newChar
	 */
	public StringView replace(char oldChar, char newChar) {
		return new StringView(string.replace(oldChar, newChar));
	}

	/**
	 * Replaces the first substring of this string that matches the given regular expression with the given replacement.
	 *
	 * @param regex       the regular expression to which this string is to be matched
	 * @param replacement the string to be substituted for the first match
	 * 
	 * @return a new {@link StringView} with the first match replaced
	 */
	public StringView replaceFirst(String regex, String replacement) {
		return new StringView(string.replaceFirst(regex, replacement));
	}

	/**
	 * Replaces each substring of this string that matches the given regular expression with the given replacement.
	 *
	 * @param regex       the regular expression to which this string is to be matched
	 * @param replacement the string to be substituted for each match
	 * 
	 * @return a new {@link StringView} with all matches replaced
	 */
	public StringView replaceAll(String regex, String replacement) {
		return new StringView(string.replaceAll(regex, replacement));
	}

	/**
	 * Replaces each substring of this string that matches the literal target sequence with the specified literal replacement
	 * sequence.
	 *
	 * @param target      the sequence of characters to be replaced
	 * @param replacement the replacement sequence of characters
	 * 
	 * @return a new {@link StringView} with all matches replaced
	 */
	public StringView replace(CharSequence target, CharSequence replacement) {
		return new StringView(string.replace(target, replacement));
	}

	/**
	 * Maps the string using the provided function.
	 *
	 * @param mapper the function to apply to the string
	 * 
	 * @return a reference to this object with the mapped string
	 */
	public StringView map(Function<String, String> mapper) {
		return new StringView(mapper.apply(string));
	}

	/**
	 * Converts the string to the specified type using the provided function.
	 *
	 * @param function the function to apply to the string
	 * @param <T>      the type to convert to
	 * 
	 * @return the result of applying the function to the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public <T> T as(Function<String, T> function) {
		try {
			return Objects.requireNonNull(function, "Function must not be null").apply(string);
		} catch (Exception exception) {
			throw new StringViewException("object", exception);
		}
	}

	/**
	 * Converts the string to a {@link String}.
	 *
	 * @return the original string
	 */
	public String asString() {
		return string;
	}

	/**
	 * Converts the string to a {@code boolean}.
	 *
	 * @return the boolean value of the string
	 */
	public boolean asBoolean() {
		return Boolean.parseBoolean(string);
	}

	/**
	 * Converts the string to a {@code byte}.
	 *
	 * @return the byte value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public byte asByte() {
		try {
			return Byte.parseByte(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("byte", exception);
		}
	}

	/**
	 * Converts the string to a {@code short}.
	 *
	 * @return the short value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public short asShort() {
		try {
			return Short.parseShort(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("short", exception);
		}
	}

	/**
	 * Converts the string to a {@code int}.
	 *
	 * @return the integer value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public int asInt() {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("int", exception);
		}
	}

	/**
	 * Converts the string to a {@code long}.
	 *
	 * @return the long value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public long asLong() {
		try {
			return Long.parseLong(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("long", exception);
		}
	}

	/**
	 * Converts the string to a {@code float}.
	 *
	 * @return the float value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public float asFloat() {
		try {
			return Float.parseFloat(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("float", exception);
		}
	}

	/**
	 * Converts the string to a {@code double}.
	 *
	 * @return the double value of the string
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public double asDouble() {
		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException exception) {
			throw new StringViewException("double", exception);
		}
	}

	/**
	 * Converts the string to an array of the specified type using a delimiter and function.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param function  the function to apply to each part
	 * @param <T>       the type of elements in the array
	 * 
	 * @return an array of the specified type
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] asArray(String delimiter, Function<String, T> function) {
		return Arrays.stream(string.split(delimiter))//
				.map(function)//
				.toArray(size -> (T[]) new Object[size]);
	}

	/**
	 * Converts the string to a {@link String} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@link String} array
	 */
	public String[] asStringArray(String delimiter) {
		return string.split(delimiter);
	}

	/**
	 * Converts the string to a {@code boolean} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code boolean} array
	 */
	public boolean[] asBooleanArray(String delimiter) {
		String[] parts = string.split(delimiter);
		boolean[] result = new boolean[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Boolean.parseBoolean(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code byte} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code byte} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public byte[] asByteArray(String delimiter) {
		try {
			String[] parts = string.split(delimiter);
			byte[] result = new byte[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Byte.parseByte(parts[i]);
			return result;
		} catch (NumberFormatException exception) {
			throw new StringViewException("byte array", exception);
		}
	}

	/**
	 * Converts the string to a {@code short} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code short} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public short[] asShortArray(String delimiter) {
		try {
			String[] parts = string.split(delimiter);
			short[] result = new short[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Short.parseShort(parts[i]);
			return result;
		} catch (NumberFormatException exception) {
			throw new StringViewException("short array", exception);
		}
	}

	/**
	 * Converts the string to an {@code int} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return an {@code int} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public int[] asIntArray(String delimiter) {
		try {
			return Arrays.stream(string.split(delimiter)).mapToInt(Integer::parseInt).toArray();
		} catch (NumberFormatException exception) {
			throw new StringViewException("int array", exception);
		}
	}

	/**
	 * Converts the string to a {@code long} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code long} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public long[] asLongArray(String delimiter) {
		try {
			String[] parts = string.split(delimiter);
			long[] result = new long[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Long.parseLong(parts[i]);
			return result;
		} catch (NumberFormatException exception) {
			throw new StringViewException("long array", exception);
		}
	}

	/**
	 * Converts the string to a {@code float} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code float} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public float[] asFloatArray(String delimiter) {
		try {
			String[] parts = string.split(delimiter);
			float[] result = new float[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Float.parseFloat(parts[i]);
			return result;
		} catch (NumberFormatException exception) {
			throw new StringViewException("float array", exception);
		}
	}

	/**
	 * Converts the string to a {@code double} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code double} array
	 * 
	 * @throws StringViewException if the conversion fails
	 */
	public double[] asDoubleArray(String delimiter) {
		try {
			String[] parts = string.split(delimiter);
			double[] result = new double[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Double.parseDouble(parts[i]);
			return result;
		} catch (NumberFormatException exception) {
			throw new StringViewException("double array", exception);
		}
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
			return Arrays.stream(string.split(delimiter)).map(function).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Boolean::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Byte::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Short::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Integer::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Long::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Float::valueOf).collect(Collectors.toList());
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
			return Arrays.stream(string.split(delimiter)).map(Double::valueOf).collect(Collectors.toList());
		} catch (NumberFormatException exception) {
			throw new StringViewException("double list", exception);
		}
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

	@Override
	public int hashCode() {
		return Objects.hash(string);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringView other = (StringView) obj;
		return Objects.equals(string, other.string);
	}

	@Override
	public String toString() {
		return String.format("StringView [string=%s]", string);
	}

	/**
	 * Thrown to indicate a conversion error occured in {@link StringView}.
	 */
	public class StringViewException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		private StringViewException(String target, Throwable cause) {
			super(String.format("Cannot view string as %s: %s", target, string), cause);
		}

	}

}
