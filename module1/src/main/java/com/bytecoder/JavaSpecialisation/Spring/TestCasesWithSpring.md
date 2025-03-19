### **Spring Boot JUnit Test Annotations and Their Uses**

Spring Boot provides various annotations to help write JUnit test cases effectively. These annotations can be categorized into different sections based on their use:

---

## **1. Core JUnit Annotations**
These annotations come from JUnit and are used in both Spring and non-Spring applications.

### **JUnit 5 Annotations:**
| Annotation | Description |
|------------|------------|
| `@Test` | Marks a method as a test case. |
| `@BeforeEach` | Runs before each test case to set up test data. |
| `@AfterEach` | Runs after each test case to clean up resources. |
| `@BeforeAll` | Runs once before all test cases in the class. Used for expensive setup. |
| `@AfterAll` | Runs once after all test cases are executed. Used for cleanup. |
| `@Disabled` | Disables a test case temporarily. |
| `@Nested` | Groups test cases inside a nested class for better organization. |
| `@DisplayName` | Sets a custom name for test cases to improve readability. |

---

## **2. Spring Boot Test Annotations**
Spring Boot provides additional test annotations to help with dependency injection, context loading, and mocking.

### **Spring Boot Test Annotations:**
| Annotation | Description |
|------------|------------|
| `@SpringBootTest` | Loads the full application context for integration testing. |
| `@WebMvcTest` | Loads only the MVC components (Controller layer) for testing. |
| `@DataJpaTest` | Loads JPA repositories and an embedded database for repository testing. |
| `@MockBean` | Replaces a bean in the Spring context with a mock instance. |
| `@SpyBean` | Creates a spy of a bean in the application context, allowing partial mocking. |
| `@TestConfiguration` | Defines additional beans specifically for test cases. |
| `@ContextConfiguration` | Loads a specific Spring configuration for the test. |
| `@DirtiesContext` | Indicates that the application context should be reset after a test. |
| `@Transactional` | Rolls back database changes after each test. |

---

## **3. Mocking and Verification Annotations**
When writing unit tests, you often need to mock dependencies.

### **Mockito Annotations:**
| Annotation | Description |
|------------|------------|
| `@Mock` | Creates a mock object of the class to be injected. |
| `@InjectMocks` | Injects mock dependencies into the test subject. |
| `@Spy` | Creates a partial mock of a real object, allowing method stubbing. |
| `@Captor` | Captures method arguments to perform assertions. |

---

## **4. Advanced Testing Annotations**
For more advanced testing scenarios, Spring Boot provides additional annotations.

| Annotation | Description |
|------------|------------|
| `@RunWith(SpringRunner.class)` | Integrates Spring with JUnit 4 (not needed in JUnit 5). |
| `@ExtendWith(SpringExtension.class)` | Required for Spring integration in JUnit 5. |
| `@ParameterizedTest` | Runs the same test multiple times with different parameters. |
| `@CsvSource` | Supplies test parameters from CSV-formatted strings. |
| `@MethodSource` | Supplies test parameters from a method. |

---

## **Example Usage**
### **Unit Test for Service Layer Using Mockito**
```java
@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }
}
```

---

### **Testing REST Controllers with @WebMvcTest**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserById() throws Exception {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
```

---

### **Testing JPA Repositories with @DataJpaTest**
```java
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User(null, "Alice", "alice@example.com");
        user = userRepository.save(user);

        Optional<User> found = userRepository.findById(user.getId());
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
    }
}
```

---

## **Conclusion**
- Use `@SpringBootTest` for full application testing.
- Use `@WebMvcTest` for controller layer testing.
- Use `@DataJpaTest` for repository layer testing.
- Use `@MockBean` and `@InjectMocks` for unit testing.
- Use `@MockMvc` for REST API testing.








### **Spring Boot Integration Testing with Edge Cases and Security**

Integration tests in Spring Boot ensure that multiple components work together as expected. In this example, I'll cover:
- **Edge cases** like invalid input, unauthorized access, and non-existing resources.
- **Spring Security Integration** to test authentication and authorization.
- **Mocking authentication** using `@WithMockUser` for role-based testing.

---

## **Setup**
Ensure you have **Spring Security**, **Spring Boot Test**, and **H2 Database** (if needed) in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## **1. Sample `UserController` with Security**
Here is a sample **Spring Boot REST controller** with authentication and role-based authorization:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
```

---

## **2. Integration Test for `UserController`**
### **Testing Authentication & Edge Cases**

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setup() {
        userRepository.save(new User(1L, "John Doe", "john@example.com"));
    }

    @AfterAll
    void tearDown() {
        userRepository.deleteAll();
    }

    // ✅ Test: Accessing without authentication (401 Unauthorized)
    @Test
    void testGetUserById_Unauthorized() throws Exception {
        mockMvc.perform(get("/users/1"))
               .andExpect(status().isUnauthorized());  // Expect 401 Unauthorized
    }

    // ✅ Test: Accessing with USER role (200 OK)
    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testGetUserById_Authorized() throws Exception {
        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    // ✅ Test: Trying to create user with USER role (403 Forbidden)
    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testCreateUser_Unauthorized() throws Exception {
        User newUser = new User(null, "Alice", "alice@example.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isForbidden());  // Expect 403 Forbidden
    }

    // ✅ Test: Creating user with ADMIN role (201 Created)
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testCreateUser_AdminAuthorized() throws Exception {
        User newUser = new User(null, "Alice", "alice@example.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    // ✅ Test: Fetching non-existing user (404 Not Found)
    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testGetUserById_NotFound() throws Exception {
        mockMvc.perform(get("/users/99"))  // ID 99 does not exist
               .andExpect(status().isNotFound());
    }

    // ✅ Test: Passing invalid input when creating user (400 Bad Request)
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testCreateUser_InvalidInput() throws Exception {
        User newUser = new User(null, "", "invalid-email"); // Invalid name and email

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest());  // Expect 400 Bad Request
    }
}
```

---

## **3. Breakdown of Edge Cases Tested**
| **Test Case**  | **Scenario** | **Expected Result** |
|---------------|-------------|------------------|
| Unauthorized access | Try to fetch user without authentication | `401 Unauthorized` |
| Authorized access | Fetch user with `USER` role | `200 OK` |
| Forbidden action | User with `USER` role tries to create a user | `403 Forbidden` |
| Admin access | User with `ADMIN` role creates a user | `201 Created` |
| User not found | Fetch non-existing user | `404 Not Found` |
| Invalid input | Send invalid user data (empty name, invalid email) | `400 Bad Request` |

---

## **4. Key Takeaways**
1. **Security Testing**
    - Used `@WithMockUser(username = "user", roles = "USER")` to test authentication and authorization.
    - Verified that unauthorized users receive `401 Unauthorized`.
    - Verified role-based access with `403 Forbidden` for unauthorized actions.

2. **Edge Case Handling**
    - Tested fetching non-existing users (`404 Not Found`).
    - Tested invalid input scenarios (`400 Bad Request`).

3. **Mocking & Database**
    - Used `H2` for in-memory database testing.
    - Used `@BeforeAll` and `@AfterAll` to set up and clean up test data.


### **Adding JWT-Based Authentication Tests in Spring Boot Integration Testing**

To test JWT authentication in Spring Boot integration tests, we will:
- Configure **JWT authentication** in Spring Security.
- Generate **mock JWT tokens** in tests.
- Test secured endpoints with **valid and invalid tokens**.


## **2. Implement JWT Utility Class**
We need a helper class to generate and validate JWT tokens.

```java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // Use a strong secret
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

---

## **3. Configure JWT Security in Spring Security**
We need to configure Spring Security to use JWT authentication.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
```

---

## **4. Authentication Controller for JWT Token Generation**
We need an endpoint to get JWT tokens for testing.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String role) {
        String token = jwtUtil.generateToken(username, role);
        return ResponseEntity.ok(token);
    }
}
```

---

## **5. Secured Controller Example**
This endpoint is secured with JWT authentication.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/admin")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Admin Data Accessed: " + SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
```

---

## **6. Integration Tests for JWT Security**
We will now write test cases for:
1. **Generating a JWT token**
2. **Accessing secured endpoints with valid and invalid tokens**
3. **Accessing secured endpoints without authentication**

### **Test Class**
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;
    
    private static String adminToken;
    private static String userToken;

    @BeforeAll
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        adminToken = jwtUtil.generateToken("admin", "ADMIN");
        userToken = jwtUtil.generateToken("user", "USER");
    }

    // ✅ Test: Login and get JWT token
    @Test
    void testLoginAndGenerateToken() throws Exception {
        mockMvc.perform(post("/auth/login")
                .param("username", "testuser")
                .param("role", "USER"))
                .andExpect(status().isOk());
    }

    // ✅ Test: Access secured endpoint without a token (401 Unauthorized)
    @Test
    void testAccessWithoutToken() throws Exception {
        mockMvc.perform(get("/secure/admin"))
                .andExpect(status().isUnauthorized());
    }

    // ✅ Test: Access secured endpoint with valid admin token (200 OK)
    @Test
    void testAccessWithAdminToken() throws Exception {
        mockMvc.perform(get("/secure/admin")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin Data Accessed: admin"));
    }

    // ✅ Test: Access secured endpoint with invalid token (403 Forbidden)
    @Test
    void testAccessWithInvalidToken() throws Exception {
        mockMvc.perform(get("/secure/admin")
                .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isForbidden());
    }
}
```

---

## **7. Edge Cases Tested**
| **Test Case** | **Scenario** | **Expected Result** |
|--------------|-------------|------------------|
| Generate JWT | User logs in with valid credentials | `200 OK` and returns token |
| No token | Access secured endpoint without token | `401 Unauthorized` |
| Valid token | Access secured endpoint with valid admin token | `200 OK` |
| Invalid token | Access secured endpoint with incorrect token | `403 Forbidden` |
| Expired token | Access with expired token (not implemented in test) | `401 Unauthorized` |

---
