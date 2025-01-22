# StringView Library

**StringView** is a powerful **Java** class designed to provide flexible and convenient methods for parsing, converting, and manipulating strings. It offers a robust set of tools to transform string data into various types and collections with minimal effort.

## Key Features

- String manipulation methods (trim, case conversion, substring, replace)
- Primitive type conversions (`boolean`, `byte`, `short`, `int`, `long`, `float`, `double`)
- Collection conversions (arrays, lists, maps)
- Support for custom type conversions via mapping functions
- Extensibility through subclassing
- Comprehensive error handling

## Usage

### Basic Type Conversions

```java
StringView view = StringView.view("42");
int number = view.asInt(); // Converts to 42
double decimal = view.asDouble(); // Converts to 42.0
boolean flag = StringView.view("true").asBoolean(); // Converts to true
```

### Array Conversions

```java
StringView view = StringView.view("1,2,3,4,5");
int[] numbers = view.asIntArray(","); // Creates [1, 2, 3, 4, 5]
List<Integer> numberList = view.asIntList(","); // Creates a list of integers
```

### Map Conversions

```java
StringView view = StringView.view("name:John,age:30,city:New York");
Map<String, String> map = view.asMap(",", ":", String::valueOf,  String::valueOf);
// Results in {name=John, age=30, city=New York}
```

### String Manipulation

```java
StringView view = StringView.view("  hello world  ");
String trimmed = view.trim().capitalize().asString(); // "Hello world"
```

### Null Handling

```java
StringView view = StringView.view(null);
String notnull = view.orElse("Hello!").asString();
```

## Error Handling

The library uses a custom `StringViewException` for conversion errors:

```java
StringView.view("not a number").asInt(); // will throw StringViewException
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
- `capitalize()`
- `reverse`

## Error Handling

- `StringViewException` for conversion errors