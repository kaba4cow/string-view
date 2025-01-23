package com.kaba4cow.stringview;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

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
	 * @param function  the function to apply to each part
	 * @param type      the type of elements in the array
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
			String[] parts = string.split(delimiter);
			int[] result = new int[parts.length];
			for (int i = 0; i < parts.length; i++)
				result[i] = Integer.parseInt(parts[i]);
			return result;
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

}
