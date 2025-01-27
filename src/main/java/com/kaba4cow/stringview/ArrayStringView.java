package com.kaba4cow.stringview;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as arrays.
 */
public class ArrayStringView extends StringView {

	/**
	 * Constructs a {@link ArrayStringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public ArrayStringView(CharSequence string) {
		super(string);
	}

	/**
	 * Converts the string to an array of the specified type using a delimiter and function.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param type      the type of elements in the array
	 * @param function  the function to apply to each part
	 * @param <T>       the type of elements in the array
	 * 
	 * @return an array of the specified type
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] asArray(String delimiter, Class<T> type, Function<String, T> function) {
		return Arrays.stream(string.split(delimiter))//
				.map(function)//
				.toArray(size -> (T[]) Array.newInstance(type, size));
	}

	/**
	 * Converts the string to an array of {@code enum} elements of the specified type using a delimiter.
	 *
	 * @param type      the {@code enum} type
	 * @param delimiter the delimiter to split the string
	 * @param <T>       the {@code enum} type
	 * 
	 * @return an array of {@code enum} elements of the specified type
	 */
	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T[] asEnumArray(Class<T> type, String delimiter) {
		return Arrays.stream(string.split(delimiter))//
				.map(name -> Enum.valueOf(type, name))//
				.toArray(size -> (T[]) Array.newInstance(type, size));
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
	 * Converts the string to a {@code char} array.
	 *
	 * @return a {@code char} array
	 */
	public char[] asCharArray() {
		return string.toCharArray();
	}

	/**
	 * Converts the string to a {@code byte} array using default {@link Charset}.
	 *
	 * @return a {@code byte} array
	 */
	public byte[] asByteArray() {
		return string.getBytes();
	}

	/**
	 * Converts the string to a {@code byte} array using the specified {@link Charset}.
	 * 
	 * @param charset the {@link Charset} to encode the string
	 *
	 * @return a {@code byte} array
	 */
	public byte[] asByteArray(Charset charset) {
		return string.getBytes(charset);
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
	 */
	public byte[] asByteArray(String delimiter) {
		String[] parts = string.split(delimiter);
		byte[] result = new byte[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Byte.parseByte(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code short} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code short} array
	 */
	public short[] asShortArray(String delimiter) {
		String[] parts = string.split(delimiter);
		short[] result = new short[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Short.parseShort(parts[i]);
		return result;
	}

	/**
	 * Converts the string to an {@code int} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return an {@code int} array
	 */
	public int[] asIntArray(String delimiter) {
		String[] parts = string.split(delimiter);
		int[] result = new int[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Integer.parseInt(parts[i]);
		return result;
	}

	/**
	 * Converts the string to an {@code int} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return an {@code int} array
	 */
	public int[] asIntArray(String delimiter, ToIntFunction<String> function) {
		String[] parts = string.split(delimiter);
		int[] result = new int[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = function.applyAsInt(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code long} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code long} array
	 */
	public long[] asLongArray(String delimiter) {
		String[] parts = string.split(delimiter);
		long[] result = new long[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Long.parseLong(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code long} array using the specified delimiter and a custom function.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param function  the function to apply for each part to convert it into a {@code long}
	 * 
	 * @return a {@code long} array containing the transformed values
	 */
	public long[] asLongArray(String delimiter, ToLongFunction<String> function) {
		String[] parts = string.split(delimiter);
		long[] result = new long[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = function.applyAsLong(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code float} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code float} array
	 */
	public float[] asFloatArray(String delimiter) {
		String[] parts = string.split(delimiter);
		float[] result = new float[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Float.parseFloat(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code double} array using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * 
	 * @return a {@code double} array
	 */
	public double[] asDoubleArray(String delimiter) {
		String[] parts = string.split(delimiter);
		double[] result = new double[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = Double.parseDouble(parts[i]);
		return result;
	}

	/**
	 * Converts the string to a {@code double} array using the specified delimiter and a custom function.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param function  the function to apply for each part to convert it into a {@code double}
	 * 
	 * @return a {@code double} array containing the transformed values
	 */
	public double[] asDoubleArray(String delimiter, ToDoubleFunction<String> function) {
		String[] parts = string.split(delimiter);
		double[] result = new double[parts.length];
		for (int i = 0; i < parts.length; i++)
			result[i] = function.applyAsDouble(parts[i]);
		return result;
	}

}
