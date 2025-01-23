# StringView Library

**StringView** is a **Java** library that provides a flexible and intuitive way to parse, convert, and manipulate strings. It offers a fluent API for transforming string data into various types and collections, making string manipulation and conversion operations more readable and maintainable.

## Features

- **Type Conversion**: Convert strings to primitive types (`int`, `double`, `boolean`, etc.) with built-in error handling
- **Collection Conversions**: Transform strings into arrays, lists, maps, and buffers with customizable delimiters
- **String Manipulation**: Perform common string operations (trim, case conversion, substring, etc.) with a fluent API
- **Buffer Support**: Direct conversion to various **java.nio** buffer types (`ByteBuffer`, `IntBuffer`, etc.)
- **Extensible Design**: Easy to extend with custom string view implementations
- **Null Safety**: Built-in null handling with **Optional** support

## Usage

### Basic String Operations

```java
StringView view = new StringView("Hello World");
view.toUpperCase().asString(); // "HELLO WORLD"
view.reverse().asString(); // "dlroW olleH"
view.substring(0, 5).asString(); // "Hello"
```

### Type Conversion

```java
int number = new StringView("64").asInt(); // 64
double decimal = new StringView("3.14159").asDouble(); // 3.14159
boolean flag = new StringView("TRVE").asBoolean(); // false
```

### Custom Type Conversion

```java
new StringView("2016-06-28").as(string -> LocalDate.parse(string, DateTimeFormatter.ISO_DATE));
new StringView("monday").toUpperCase().as(DayOfWeek::valueOf);
new StringView("https://www.example.com").as(URI::create);
```

### Array Conversion

```java
ArrayStringView view = new StringView("1 2 3 4 5").asArrayView();
int[] numbers = view.asIntArray(" ");
```

or

```java
ArrayStringView view = new StringView("1 2 3 4 5").asArrayView();
Integer[] numbers = view.asArray(" ", Integer::parseInt);
```

Result: `[1, 2, 3, 4, 5]`.

### Buffer Conversion

```java
BufferStringView view = new StringView("1 2 3 4 5").asBufferView();
IntBuffer buffer = view.asIntBuffer(" ", false);
```

Result: `IntBuffer` containing `[1, 2, 3, 4, 5]`.

### List Conversion

```java
ListStringView view = new StringView("hello,world").asListView();
List<String> words = view.asStringList(",");
```

or

```java
ListStringView view = new StringView("hello,world").asListView();
List<String> words = view.asList(",", String::toString);
```

Result: `["hello", "world"]`.

### Map Conversion

```java
MapStringView view = new StringView("a=0.57;b=6.63;c=72.14").asMapView();
Map<String, Float> map = view.asMap(";", "=",  String::toString, Float::parseFloat);
```

Result: `{a=0.57, b=6.63, c=72.14}`.

### Nested Collections Conversion

```java
String mapData = "list1:1,2,3 list2:4,5,6";
Map<String, List<Integer>> map = new StringView(mapData)
    .asMapView()
    .asMap(" ", ":", 
        String::toString, 
        value -> new StringView(value)
            .asListView()
            .asIntList(","));
```

Result: `{list1=[1, 2, 3], list2=[4, 5, 6]}`.

```java
String arrayData = "1;2;3 4;5;6 7;8;9";
int[][] array = new StringView(arrayData)
    .asArrayView()
    .asArray(" ", 
        int[].class, 
        row -> new StringView(row)
            .asArrayView()
            .asIntArray(";"));
```

Result: `[[1, 2, 3], [4, 5, 6], [7, 8, 9]]`.

### Null-Safe Chaining

Safe method chaining with `null` values:

```java
String result = new StringView(null)
    .orElse("Default")
    .trim()
    .invertCase()
    .asString();
```

Combining with `Optional` operations:

```java
String value = new StringView(null)
    .asOptional()
    .map(view -> view.trim().asString())
    .orElse("default");
```

Custom `null` handling with mapping:

```java
String result = new StringView(null)
    .orElseGet(() -> getDefaultValue())
    .map(str -> str.isEmpty() ? null : str)
    .orElse("fallback")
    .asString();
```

## Error Handling

- `StringViewException` - for conversion errors:

```java
try {
    new StringView("not a number").asInt();
} catch (StringViewException exception) {}
```