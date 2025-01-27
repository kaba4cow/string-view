package com.kaba4cow.stringview;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * Provides methods for parsing, converting, and manipulating strings in various ways. It is designed to offer a flexible way to
 * transform string data into different types, such as lists, maps, and various primitive types.
 * </p>
 * <p>
 * This class allows for easy conversion of strings into collections like lists and maps, supporting different data types,
 * including {@link Integer}, {@link Boolean}, {@link Double}, {@link String}, and many more.
 * </p>
 * <p>
 * The {@link StringView} class is an essential tool for working with and transforming string data in Java, providing powerful
 * utilities for working with text in a highly customizable and structured manner.
 * </p>
 * <p>
 * <b>Note:</b> All methods in this class assume the input string is non-{@code null}. If {@code null} is passed to any method,
 * a {@link NullPointerException} will be thrown unless explicitly handled by the user. In cases where {@code null} is
 * acceptable, either use {@link #asOptional()} or {@link #orElse(CharSequence)} and {@link #orElseGet(Supplier)} methods, or
 * handle {@code null} in your implementation to ensure proper behavior.
 * </p>
 */
public class StringView implements Comparable<StringView>, CharSequence {

	protected final String string;

	/**
	 * Constructs a {@link StringView} with the specified string.
	 *
	 * @param value the string to wrap
	 */
	public StringView(Object value) {
		this.string = Objects.isNull(value) ? null : value.toString();
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
	 * Inverts the character cases in the string.
	 * 
	 * @return a new {@link StringView} with the string with inverted case.
	 */
	public StringView invertCase() {
		StringBuilder builder = new StringBuilder();
		for (char c : string.toCharArray())
			if (Character.isLowerCase(c))
				builder.append(Character.toUpperCase(c));
			else if (Character.isUpperCase(c))
				builder.append(Character.toLowerCase(c));
			else
				builder.append(c);
		return new StringView(builder);
	}

	/**
	 * Returns a substring starting from the specified index.
	 * 
	 * @param beginIndex the index to start the substring
	 * 
	 * @return a new {@link StringView} containing the substring
	 */
	public StringView substring(int beginIndex) {
		return new StringView(string.substring(beginIndex));
	}

	/**
	 * Returns a substring starting from the specified begin index to the specified end index.
	 * 
	 * @param beginIndex the index to start the substring
	 * @param endIndex   the index to end the substring
	 * 
	 * @return a new {@link StringView} containing the substring
	 */
	public StringView substring(int beginIndex, int endIndex) {
		return new StringView(string.substring(beginIndex, endIndex));
	}

	/**
	 * Concatenates the specified string to the beginning of this {@link StringView} string.
	 *
	 * @param str the string to concatenate
	 * 
	 * @return a new {@link StringView} containing the concatenated string
	 */
	public StringView prefix(String str) {
		return new StringView(str.concat(string));
	}

	/**
	 * Concatenates the specified string to the end of this {@link StringView} string.
	 *
	 * @param str the string to concatenate
	 * 
	 * @return a new {@link StringView} containing the concatenated string
	 */
	public StringView concat(String str) {
		return new StringView(string.concat(str));
	}

	/**
	 * Returns a new {@link StringView} resulting from replacing all occurrences of oldChar in this {@link StringView} string
	 * with newChar.
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
	 * Replaces the first substring of this {@link StringView} string that matches the given regular expression with the given
	 * replacement.
	 *
	 * @param regex       the regular expression to which this {@link StringView} string is to be matched
	 * @param replacement the string to be substituted for the first match
	 * 
	 * @return a new {@link StringView} with the first match replaced
	 */
	public StringView replaceFirst(String regex, String replacement) {
		return new StringView(string.replaceFirst(regex, replacement));
	}

	/**
	 * Replaces each substring of this {@link StringView} string that matches the given regular expression with the given
	 * replacement.
	 *
	 * @param regex       the regular expression to which this {@link StringView} string is to be matched
	 * @param replacement the string to be substituted for each match
	 * 
	 * @return a new {@link StringView} with all matches replaced
	 */
	public StringView replaceAll(String regex, String replacement) {
		return new StringView(string.replaceAll(regex, replacement));
	}

	/**
	 * Replaces each substring of this {@link StringView} string that matches the literal target sequence with the specified
	 * literal replacement sequence.
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
	 * Capitalizes this {@link StringView} string.
	 * 
	 * @return a new {@link StringView} with capitalized string
	 */
	public StringView capitalize() {
		return new StringView(string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase());
	}

	/**
	 * Reverses this {@link StringView} string.
	 * 
	 * @return a new {@link StringView} with reversed string
	 */
	public StringView reverse() {
		return new StringView(new StringBuilder(string).reverse().toString());
	}

	/**
	 * Returns the current {@link StringView} if the internal string is not {@code null}, or a new {@link StringView} with the
	 * provided alternative value.
	 * 
	 * @param value the default string value which will be used if the current string is {@code null}
	 * 
	 * @return current {@link StringView} if string is not {@code null}, or a new {@link StringView} with the provided value
	 */
	public StringView orElse(Object value) {
		return Objects.isNull(string) ? new StringView(value) : this;
	}

	/**
	 * Returns the current {@link StringView} if the internal string is not {@code null}, or a new {@link StringView} with an
	 * alternative value from the supplier.
	 * 
	 * @param value a supplier that provides a fallback string value which will be used if the current string is {@code null}
	 * 
	 * @return current {@link StringView} if string is not {@code null}, or a new {@link StringView} with the supplier's result
	 */
	public StringView orElseGet(Supplier<? extends Object> value) {
		return Objects.isNull(string) ? new StringView(value.get()) : this;
	}

	/**
	 * Maps the string using the provided function.
	 *
	 * @param mapper the function to apply to the string
	 * 
	 * @return a reference to this object with the mapped string
	 */
	public StringView map(Function<String, ? extends Object> mapper) {
		return new StringView(mapper.apply(string));
	}

	/**
	 * Wraps the current string in an {@link Optional} if it's not {@code null}, otherwise returns an empty {@link Optional}.
	 *
	 * @return an {@link Optional} containing this {@link StringView} if the underlying string is not {@code null}, otherwise
	 *             empty
	 */
	public Optional<StringView> asOptional() {
		return Objects.isNull(string) ? Optional.empty() : Optional.of(this);
	}

	/**
	 * Converts the string to the specified type using the provided function.
	 *
	 * @param function the function to apply to the string
	 * @param <T>      the type to convert to
	 * 
	 * @return the result of applying the function to the string
	 */
	public <T> T asObject(Function<String, T> function) {
		return function.apply(string);
	}

	/**
	 * Converts this {@link StringView} to another {@link StringView} implementation using the provided function.
	 *
	 * @param view the function to convert this {@link StringView} to another implementation
	 * @param <T>  the type of {@link StringView} to convert to
	 * 
	 * @return a new {@link StringView} of the specified type containing the same string
	 */
	public <T extends StringView> T asView(Function<String, T> view) {
		return view.apply(string);
	}

	/**
	 * Converts this {@link StringView} to {@link ArrayStringView}.
	 *
	 * @return a new {@link ArrayStringView} of the specified type containing the same string
	 */
	public ArrayStringView asArrayView() {
		return asView(ArrayStringView::new);
	}

	/**
	 * Converts this {@link StringView} to {@link BufferStringView}.
	 *
	 * @return a new {@link BufferStringView} of the specified type containing the same string
	 */
	public BufferStringView asBufferView() {
		return asView(BufferStringView::new);
	}

	/**
	 * Converts this {@link StringView} to {@link CollectionStringView}.
	 *
	 * @return a new {@link CollectionStringView} of the specified type containing the same string
	 */
	public CollectionStringView asCollectionView() {
		return asView(CollectionStringView::new);
	}

	/**
	 * Converts this {@link StringView} to {@link MapStringView}.
	 *
	 * @return a new {@link MapStringView} of the specified type containing the same string
	 */
	public MapStringView asMapView() {
		return asView(MapStringView::new);
	}

	/**
	 * Converts the string to an {@code enum} constant of the specified type.
	 *
	 * @param type the class of the {@code enum} type
	 * @param <T>  the {@code enum} type
	 * 
	 * @return the {@code enum} constant of the specified type matching the string
	 */
	public <T extends Enum<T>> T asEnum(Class<T> type) {
		return Enum.valueOf(type, string);
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
	 */
	public byte asByte() {
		return Byte.parseByte(string);
	}

	/**
	 * Converts the string to a {@code short}.
	 *
	 * @return the short value of the string
	 */
	public short asShort() {
		return Short.parseShort(string);
	}

	/**
	 * Converts the string to a {@code int}.
	 *
	 * @return the integer value of the string
	 */
	public int asInt() {
		return Integer.parseInt(string);
	}

	/**
	 * Converts the string to a {@code long}.
	 *
	 * @return the long value of the string
	 */
	public long asLong() {
		return Long.parseLong(string);
	}

	/**
	 * Converts the string to a {@code float}.
	 *
	 * @return the float value of the string
	 */
	public float asFloat() {
		return Float.parseFloat(string);
	}

	/**
	 * Converts the string to a {@code double}.
	 *
	 * @return the double value of the string
	 */
	public double asDouble() {
		return Double.parseDouble(string);
	}

	@Override
	public int compareTo(StringView other) {
		return string.compareTo(other.string);
	}

	@Override
	public int length() {
		return string.length();
	}

	@Override
	public char charAt(int index) {
		return string.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return string.subSequence(start, end);
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
		return String.format("StringView [%s]", string);
	}

}
