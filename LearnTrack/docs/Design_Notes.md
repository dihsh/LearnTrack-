# Design Notes

## Why ArrayList Instead of Array?

In LearnTrack, all three core data stores (`students`, `courses`, `enrollments`) use `ArrayList<T>` instead of plain arrays.

The key reason is **dynamic sizing**. A Java array has a fixed length set at creation time — you'd have to decide upfront how many students or courses the system could hold, which is impractical for a real-world application. `ArrayList` automatically grows as you add elements, so there's no artificial limit and no manual resizing code required.

`ArrayList` also provides convenient methods like `.add()`, `.remove()`, and iteration via `for-each` loops, which keep the service code clean and readable. For a project at this stage — where data lives in memory only — `ArrayList` is the right balance of simplicity and flexibility. As the project grows, these could be replaced with a database or a `HashMap` for faster lookups by ID.

---

## Where Static Members Were Used and Why

Static members belong to the **class itself**, not to any particular instance. In LearnTrack, the `IdGenerator` utility class uses static fields and methods:

```java
private static int studentIdCounter = 1;
public static int getNextStudentId() { return studentIdCounter++; }
```

This is appropriate because the ID counter is a single global state shared across the entire application — there should only ever be one counter, not one per service instance. If `studentIdCounter` were an instance variable, creating two `IdGenerator` objects would reset the counter and generate duplicate IDs. Making it `static` ensures there is exactly one counter for the lifetime of the application.

Similarly, `InputValidator` methods are static because they are pure utility functions — they take input, validate it, and return a result. They hold no state and don't need an object to operate.

---

## Where Inheritance Was Used and What Was Gained

LearnTrack introduces a simple inheritance hierarchy:

```
Person  ←  Student
Person  ←  Trainer
```

`Person` holds the common fields shared by all people in the system: `id`, `firstName`, `lastName`, and `email`. `Student` extends `Person` and adds `batch` and `active`, while `Trainer` adds `expertise`.

**What was gained:**

1. **Code reuse** — The common fields and their getters/setters are defined once in `Person`. `Student` and `Trainer` don't need to repeat them.

2. **Polymorphism** — Both `Student` and `Trainer` override `getDisplayName()`, each returning a format appropriate to their context. Code that works with a `Person` reference can call `getDisplayName()` and get the right result without knowing the specific subtype.

3. **Extensibility** — Adding new person types (e.g., `Admin`, `Mentor`) in the future is straightforward: simply extend `Person` and add the relevant fields.

4. **`super` usage** — Student and Trainer constructors call `super(id, firstName, lastName, email)` to initialize the inherited fields, demonstrating how constructor chaining works in Java inheritance.
