# StringView Library

**StringView** is a powerful **Java** utility class designed to provide flexible and convenient methods for parsing, converting, and manipulating strings. It offers a robust set of tools to transform string data into various types and collections with minimal effort.

## Key Features

- Easy conversion of strings to primitive types
- Support for converting strings to arrays and lists
- Flexible mapping and transformation of strings
- Wrapped index handling for substring operations
- Comprehensive error handling

## Usage

### Basic Type Conversions

```java
StringView view = new StringView("42");
int number = view.asInt(); // Converts to 42
double decimal = view.asDouble(); // Converts to 42.0
boolean flag = new StringView("true").asBoolean(); // Converts to true
```

### Array Conversions

```java
StringView view = new StringView("1,2,3,4,5");
int[] numbers = view.asIntArray(","); // Creates [1, 2, 3, 4, 5]
List<Integer> numberList = view.asIntList(","); // Creates a list of integers
```

### Map Conversions

```java
StringView view = new StringView("name:John,age:30,city:New York");
Map<String, String> map = view.asMap(",", ":", String::valueOf,  String::valueOf);
// Results in {name=John, age=30, city=New York}
```

### String Manipulation

```java
StringView view = new StringView("  Hello World  ");
String trimmed = view.trim().toUpperCase().asString(); // "HELLO WORLD"
```

### Substring with Wrapped Indexing

```java
StringView view = new StringView("Hello");
String substring = view.substring(2, 7).asString(); // "llo H"
```

## Error Handling

The library uses a custom `StringViewException` for conversion errors:

```java
try {
    int number = new StringView("not a number").asInt();
} catch (StringViewException e) {
    System.out.println(e.getMessage());
}
```

## Method Categories

### Type Conversion Methods

- `asString()`
- `asBoolean()`
- `asByte()`, `asShort()`, `asInt()`, `asLong()`
- `asFloat()`, `asDouble()`

### Collection Conversion Methods

- `asArray()`, `asStringArray()`
- `asList()`, `asStringList()`
- `asMap()`

### String Manipulation Methods

- `trim()`
- `toUpperCase()`
- `toLowerCase()`
- `substring()`
- `concat()`
- `replace()`
- `replaceFirst()`
- `replaceAll()`

## Error Handling

- `StringViewException` for conversion errors