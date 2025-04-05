

Here are your revised, clean **REST API notes** for quick revision later:

---

## 🧠 REST API - Quick Notes

### 🔹 What is REST API?
- **REST** = **REpresentational State Transfer**
- A **REST API** is an interface that follows REST principles for how **clients** and **servers** communicate over **HTTP**
- Used to **access or manipulate resources** (like users, products, orders)

---

### 🔹 Key REST Concepts

✅ **Client-Server Architecture**
- Client sends a request
- Server sends a response
- Communication happens over **HTTP** (application-layer protocol)

✅ **Resources** are identified by URLs (called **endpoints**):
- `/users`, `/orders/123`, etc.

✅ REST uses **HTTP Methods** to define actions:
| HTTP Method | Action        | Example Endpoint     |
|-------------|---------------|----------------------|
| `GET`       | Read          | `/users`             |
| `POST`      | Create        | `/users`             |
| `PUT`       | Update        | `/users/123`         |
| `DELETE`    | Delete        | `/users/123`         |

---

## 📤 HTTP Request Format

```
<REQUEST LINE>
<HEADERS>
<BLANK LINE>
<BODY> (optional)
```

- **Request Line** → HTTP method, URL, and version
- **Headers** → Metadata (e.g., content type, host)
- **Blank Line** → Separates headers from the body
- **Body** → Actual data (optional, used with POST, PUT)

---

### 📌 Example: HTTP POST Request (Form URL Encoded)

```http
POST /register HTTP/1.1
Host: www.example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 52
User-Agent: Mozilla/5.0
Accept: */*

username=john_doe&email=john@example.com&password=123456
```

---

### 📌 Example: HTTP POST Request (JSON)

```http
POST /register HTTP/1.1
Host: www.example.com
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "123456"
}
```

---

## 📥 HTTP Response Format

```
<STATUS LINE>
<HEADERS>
<BLANK LINE>
<BODY>
```

- **Status Line** → Protocol + Status Code + Status Message
- **Headers** → Metadata (e.g., content type, length)
- **Body** → Response data (often JSON)

---

### 📌 Example: HTTP 201 Created Response

```http
HTTP/1.1 201 Created
Content-Type: application/json
Content-Length: 72
Date: Fri, 28 Mar 2025 12:00:00 GMT
Connection: keep-alive

{
  "message": "User registered successfully",
  "userId": 101
}
```

---







### **Corrected Flow of Serialization & Deserialization:**

1. **Java Object → Serialization → JSON String**
    - Convert a Java object into a JSON string.

2. **JSON String → UTF-8 Encoding → Byte Stream**
    - Convert the JSON string into a byte stream (UTF-8 encoded) for transmission over a network or storage.

3. **Transfer (e.g., Network, Disk, Message Queue)**
    - The byte stream is transmitted via a network, stored in a file, or sent through a message queue like Kafka.

4. **Byte Stream → UTF-8 Decoding → JSON String**
    - Convert the received byte stream back into a JSON string using UTF-8 decoding.

5. **JSON String → Deserialization → Java Object**
    - Convert the JSON string back into a Java object.

### **Final Flow:**
✅ **Java Object → Serialization → JSON String → UTF-8 Encoding → Byte Stream → Transfer → Byte Stream → UTF-8 Decoding → JSON String → Deserialization → Java Object**

---

### **Example in Java using Jackson**
#### **Serialization: Java Object → JSON → Byte Stream**
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;

class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializationExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person("Alice", 30);

        // Step 1: Convert Java object to JSON string
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println("JSON String: " + jsonString);

        // Step 2: Convert JSON string to byte stream (UTF-8 encoding)
        byte[] byteStream = jsonString.getBytes(StandardCharsets.UTF_8);
        System.out.println("Byte Stream: " + new String(byteStream, StandardCharsets.UTF_8));
    }
}
```

#### **Deserialization: Byte Stream → JSON → Java Object**
```java
// Step 3: Convert byte stream back to JSON string
String receivedJsonString = new String(byteStream, StandardCharsets.UTF_8);

// Step 4: Convert JSON string back to Java object
Person deserializedPerson = objectMapper.readValue(receivedJsonString, Person.class);
System.out.println("Deserialized Person Name: " + deserializedPerson.name);
```

---

### **Key Takeaways**
- JSON is a **string representation** of an object.
- UTF-8 encoding converts JSON **string** into a **byte stream** for transmission.
- Deserialization reverses the process: **byte stream → JSON string → Java object**.



### how system knows how many bytes to take for a value and how UTF-8 works here

ASCII (for headers)
UTF-8 (for JSON body or other textual data)
Binary encoding (for images, videos, or other non-textual data)

Unicode, ascii and UTF-8




### Understanding How a System Determines Byte Size for Different Encodings

When a system processes data, it needs to determine how many bytes to allocate for each value. This depends on the encoding scheme used for representing the data.

1. **ASCII (for headers)**
2. **UTF-8 (for JSON body or other textual data)**
3. **Binary encoding (for images, videos, or other non-textual data)**

To transfer data we need encoding or stream of bytes but since unicode single value contains multiple bytes. so we need to to encode and transfer

#### Network Stack Encapsulation
* HTTP Data (Application Layer)
* TCP Segment (Transport Layer, adds TCP headers)
* IP Packet (Network Layer, adds IP headers)
* Ethernet Frame (Link Layer, adds MAC address and CRC)

Yes, exactly! When storing Unicode characters in a database, disk, or memory, they are encoded using a specific UTF format (usually UTF-8), and when displaying them on the screen, they are decoded back into their original characters.

---

## 1️⃣ **ASCII (for headers)**
- ASCII (American Standard Code for Information Interchange) is a **fixed-width encoding**.
- Each character is **exactly 1 byte (8 bits)**.
- It uses values from **0 to 127 (7-bit representation)**, but the 8th bit is often left as 0.
- Example:
  ```
  "GET" -> 'G' = 0x47 (71), 'E' = 0x45 (69), 'T' = 0x54 (84)
  ```
  This takes **3 bytes**.

📌 **How the system knows the size?**
- Since ASCII is a **fixed-size encoding (1 byte per character)**, the system just counts the number of characters.
- Headers are usually terminated by special characters like `\r\n` (CRLF) to indicate the end.

---

## 2️⃣ **UTF-8 (for JSON body or other textual data)**
UTF-8 is a **variable-width encoding** where:
- **ASCII characters (0-127) take 1 byte**.
- **Extended characters (128 and above) take 2 to 4 bytes**.

### UTF-8 Encoding Rules:
| Code Point (Unicode) | Byte Sequence |
|---------------------|--------------|
| U+0000 - U+007F (ASCII) | 1 byte (same as ASCII) |
| U+0080 - U+07FF | 2 bytes |
| U+0800 - U+FFFF | 3 bytes |
| U+10000 - U+10FFFF | 4 bytes |

### Example: Encoding "hello" vs. "हेलो" (Hindi word for "hello")

#### **"hello" (ASCII case)**
Each letter is ASCII → **1 byte each**.
```
'h' = 0x68
'e' = 0x65
'l' = 0x6C
'l' = 0x6C
'o' = 0x6F
Total: 5 bytes
```

#### **"हेलो" (Hindi)**
| Character | Unicode Code Point | UTF-8 Bytes |
|-----------|-------------------|-------------|
| ह | U+0939 | **E0 A4 B9** (3 bytes) |
| े | U+0947 | **E0 A4 87** (3 bytes) |
| ल | U+0932 | **E0 A4 B2** (3 bytes) |
| ो | U+094B | **E0 A4 AD** (3 bytes) |

**Total: 12 bytes**.

📌 **How does the system determine the size?**
- The system reads the **first byte**.
- Based on the **leading bits**, it determines the character length:
    - `0xxxxxxx` → 1 byte (ASCII)
    - `110xxxxx` → 2 bytes
    - `1110xxxx` → 3 bytes
    - `11110xxx` → 4 bytes

Example: If a byte starts with `1110`, the system knows it must read **3 bytes** to get a complete character.

---

## 3️⃣ **Binary Encoding (for images, videos, or other non-textual data)**
Binary data (like images, videos, and compressed files) is **not encoded as text**.
- These files are **raw sequences of bytes**.
- The system knows the size from **metadata** (headers, file format specifications).

### Example: JPEG Image
A JPEG file has a header like:
```
FFD8 (Start of Image)
FFE0 (APP0 marker, metadata follows)
```
The metadata tells the system:
- **File size**
- **Compression type**
- **Resolution**
- **Color depth**

📌 **How does the system determine the size?**
- The system reads the **header metadata**.
- For **network transmission**, binary data is often **length-prefixed**, meaning:
    - First few bytes specify **how many bytes follow**.

Example:
```
Content-Length: 524288  (Tells the system to expect 524288 bytes)
```

---

## 🔥 Summary: How the System Knows Byte Size

| Encoding | Byte Size | How System Knows? |
|----------|----------|-------------------|
| **ASCII** | 1 byte per character | Fixed size (just count characters) |
| **UTF-8** | 1-4 bytes per character | Leading bits tell length (variable size) |
| **Binary** | Varies (raw bytes) | Metadata specifies size |

This is why UTF-8 is efficient: it keeps **ASCII as-is (1 byte)** but supports all languages by using extra bytes only when needed. 🚀



