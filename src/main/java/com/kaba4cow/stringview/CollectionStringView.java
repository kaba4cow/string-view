package com.kaba4cow.stringview;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as collections.
 */
public class CollectionStringView extends StringView {

	/**
	 * Constructs a {@link CollectionStringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public CollectionStringView(CharSequence string) {
		super(string);
	}

	/**
	 * Converts the string to a collection of elements of type {@link T} using the provided delimiter and conversion function.
	 * 
	 * @param <T>                the type of elements in the collection
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param function           the function to convert each part of the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the converted elements
	 */
	public <T, C extends Collection<T>> C asCollection(String delimiter, Function<String, T> function,
			Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(function).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Enum} values of type {@link T} using the provided delimiter.
	 * 
	 * @param <T>                the type of enum
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param type               the enum type
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the converted enum values
	 */
	public <T extends Enum<T>, C extends Collection<T>> C asEnumCollection(String delimiter, Class<T> type,
			Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(name -> Enum.valueOf(type, name)).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Character} objects.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the characters of the string
	 */
	public <C extends Collection<Character>> C asCharacterCollection(Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		for (char c : string.toCharArray())
			collection.add(c);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Byte} values using the default charset.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the byte values of the string
	 */
	public <C extends Collection<Byte>> C asByteCollection(Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		for (byte b : string.getBytes())
			collection.add(b);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Byte} values using the specified charset.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param charset            the charset to encode the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the byte values of the string
	 */
	public <C extends Collection<Byte>> C asByteCollection(Charset charset, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		for (byte b : string.getBytes(charset))
			collection.add(b);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link String} elements using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the string parts
	 */
	public <C extends Collection<String>> C asStringCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Boolean} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the boolean values of the string
	 */
	public <C extends Collection<Boolean>> C asBooleanCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Boolean::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Byte} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the byte values of the string
	 */
	public <C extends Collection<Byte>> C asByteCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Byte::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Short} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the short values of the string
	 */
	public <C extends Collection<Short>> C asShortCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Short::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Integer} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the integer values of the string
	 */
	public <C extends Collection<Integer>> C asIntegerCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Integer::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Long} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the long values of the string
	 */
	public <C extends Collection<Long>> C asLongCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Long::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Float} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the float values of the string
	 */
	public <C extends Collection<Float>> C asFloatCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Float::valueOf).forEach(collection::add);
		return collection;
	}

	/**
	 * Converts the string to a collection of {@link Double} values using the provided delimiter.
	 * 
	 * @param <C>                the type of collection to be returned
	 * @param delimiter          the delimiter to split the string
	 * @param collectionSupplier the supplier to provide a new collection instance
	 * 
	 * @return a collection of type {@link C} containing the double values of the string
	 */
	public <C extends Collection<Double>> C asDoubleCollection(String delimiter, Supplier<C> collectionSupplier) {
		C collection = collectionSupplier.get();
		Arrays.stream(string.split(delimiter)).map(Double::valueOf).forEach(collection::add);
		return collection;
	}

}
