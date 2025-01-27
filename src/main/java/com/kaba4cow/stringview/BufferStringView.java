package com.kaba4cow.stringview;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;

/**
 * Extends {@link StringView} providing methods for viewing {@link String} as buffers.
 */
public class BufferStringView extends StringView {

	/**
	 * Constructs a {@link BufferStringView} with the specified string.
	 *
	 * @param string the string to wrap
	 */
	public BufferStringView(CharSequence string) {
		super(string);
	}

	/**
	 * Converts the string to a {@link CharBuffer}.
	 * 
	 * @param direct if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 *
	 * @return a {@link CharBuffer} containing the string characters
	 */
	public CharBuffer asCharBuffer(boolean direct) {
		char[] chars = string.toCharArray();
		CharBuffer buffer = direct //
				? ByteBuffer.allocateDirect(chars.length * Character.BYTES).asCharBuffer() //
				: CharBuffer.allocate(chars.length);
		for (char c : chars)
			buffer.put(c);
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link ByteBuffer} using default {@link Charset}.
	 * 
	 * @param direct if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 *
	 * @return a {@link ByteBuffer} containing the string bytes
	 */
	public ByteBuffer asByteBuffer(boolean direct) {
		byte[] bytes = string.getBytes();
		ByteBuffer buffer = direct //
				? ByteBuffer.allocateDirect(bytes.length * Byte.BYTES)//
				: ByteBuffer.allocate(bytes.length);
		for (byte b : bytes)
			buffer.put(b);
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link ByteBuffer} using the specified {@link Charset}.
	 * 
	 * @param charset the {@link Charset} to encode the string
	 * @param direct  if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 *
	 * @return a {@link ByteBuffer} containing the string bytes
	 */
	public ByteBuffer asByteBuffer(Charset charset, boolean direct) {
		byte[] bytes = string.getBytes(charset);
		ByteBuffer buffer = direct //
				? ByteBuffer.allocateDirect(bytes.length * Byte.BYTES)//
				: ByteBuffer.allocate(bytes.length);
		for (byte b : bytes)
			buffer.put(b);
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link ByteBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link ByteBuffer} containing the parsed byte values
	 */
	public ByteBuffer asByteBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		ByteBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Byte.BYTES) //
				: ByteBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Byte.parseByte(part));
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link ShortBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link ShortBuffer} containing the parsed byte values
	 */
	public ShortBuffer asShortBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		ShortBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Short.BYTES).asShortBuffer() //
				: ShortBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Short.parseShort(part));
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link IntBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link IntBuffer} containing the parsed byte values
	 */
	public IntBuffer asIntBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		IntBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Integer.BYTES).asIntBuffer() //
				: IntBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Integer.parseInt(part));
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link LongBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link LongBuffer} containing the parsed byte values
	 */
	public LongBuffer asLongBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		LongBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Long.BYTES).asLongBuffer() //
				: LongBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Long.parseLong(part));
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link FloatBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link FloatBuffer} containing the parsed byte values
	 */
	public FloatBuffer asFloatBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		FloatBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Float.BYTES).asFloatBuffer() //
				: FloatBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Float.parseFloat(part));
		buffer.flip();
		return buffer;
	}

	/**
	 * Converts the string to a {@link DoubleBuffer} using the specified delimiter.
	 *
	 * @param delimiter the delimiter to split the string
	 * @param direct    if {@code true}, creates a direct buffer; otherwise, a heap buffer
	 * 
	 * @return a {@link DoubleBuffer} containing the parsed byte values
	 */
	public DoubleBuffer asDoubleBuffer(String delimiter, boolean direct) {
		String[] parts = string.split(delimiter);
		DoubleBuffer buffer = direct //
				? ByteBuffer.allocateDirect(parts.length * Double.BYTES).asDoubleBuffer() //
				: DoubleBuffer.allocate(parts.length);
		for (String part : parts)
			buffer.put(Double.parseDouble(part));
		buffer.flip();
		return buffer;
	}

}
