# StringView Library

**StringView** is a **Java** library that provides a flexible and intuitive way to parse, convert, and manipulate strings. It offers a fluent API for transforming string data into various types and collections, making string manipulation and conversion operations more readable and maintainable.

## Features

- **Type Conversion**: Convert strings to primitive types (`int`, `double`, `boolean`, etc.)
- **Collection Conversions**: Transform strings into arrays, collections, maps, and buffers with customizable delimiters
- **String Manipulation**: Perform common string operations (trim, case conversion, substring, etc.) with a fluent API
- **Buffer Support**: Direct conversion to various **java.nio** buffer types (`ByteBuffer`, `IntBuffer`, etc.)
- **Extensible Design**: Easy to extend with custom string view implementations
- **Null Safety**: Built-in null handling with **Optional** support

## Installation

Clone and build:

```bash
git clone https://github.com/kaba4cow/string-view.git
cd string-view
mvn clean install
```

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.kaba4cow</groupId>
    <artifactId>string-view</artifactId>
    <version>3.3.0</version>
</dependency>
```

Requirements: 

- **Java** version **8** or higher.

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
DayOfWeek day = new StringView("TUESDAY").asEnum(DayOfWeek.class); // DayOfWeek.TUESDAY
```

### Custom Type Conversion

```java
new StringView("2016-06-28").asObject(string -> LocalDate.parse(string, DateTimeFormatter.ISO_DATE));
new StringView("https://www.example.com").asObject(URI::create);
```

### Array Conversion

```java
ArrayStringView view = new StringView("1 2 3 4 5").asArrayView();
int[] numbers = view.asIntArray(" ");
```

or

```java
ArrayStringView view = new StringView("1 2 3 4 5").asArrayView();
Integer[] numbers = view.asArray(" ", Integer::valueOf);
```

Result: `[1, 2, 3, 4, 5]`.

### Buffer Conversion

```java
BufferStringView view = new StringView("1 2 3 4 5").asBufferView();
IntBuffer buffer = view.asIntBuffer(" ", false);
```

Result: `IntBuffer` containing `[1, 2, 3, 4, 5]`.

### Collection Conversion

```java
CollectionStringView view = new StringView("hello,world").asCollectionView();
List<String> words = view.asStringCollection(",", ArrayList::new);
```

or

```java
CollectionStringView view = new StringView("hello,world").asCollectionView();
Set<String> words = view.asCollection(",", String::toString, TreeSet::new);
```

Result: `["hello", "world"]`.

### Map Conversion

```java
MapStringView view = new StringView("a=0.57;b=6.63;c=72.14").asMapView();
Map<String, Float> map = view.asMap(";", "=",  String::toString, Float::parseFloat, HashMap::new);
```

Result: `{a=0.57, b=6.63, c=72.14}`.

### Nested Map and Collection Conversion

```java
String mapData = "list1:1,2,3 list2:4,5,6";
Map<String, List<Integer>> map = new StringView(mapData)
		.asMapView()
		.asMap(" ", ":", 
				String::toString,
				value -> new StringView(value)
					.asCollectionView()
					.asIntegerCollection(",", ArrayList::new), 
				HashMap::new);
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

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.