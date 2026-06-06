# JVM Basics

## JDK vs JRE vs JVM

### JVM — Java Virtual Machine
The JVM is the engine that actually runs your Java program. It reads **bytecode** (the compiled `.class` files) and translates the instructions into native machine code that your operating system understands. The JVM handles memory management (garbage collection), security checks, and runtime optimizations. Importantly, there is a different JVM implementation for Windows, macOS, and Linux — but they all understand the same bytecode format.

### JRE — Java Runtime Environment
The JRE is the JVM plus the standard Java class libraries (like `java.util`, `java.io`, etc.) that your program may rely on at runtime. If you only need to **run** a compiled Java application (not compile new code), the JRE is enough.

### JDK — Java Development Kit
The JDK is the complete package for Java developers. It includes the JRE (and therefore the JVM), plus the **Java compiler (`javac`)**, debugging tools, and other utilities needed to write, compile, and test Java code. When developing a project like LearnTrack, you need the JDK.

**Summary:**
```
JDK  ⊃  JRE  ⊃  JVM
```

---

## What is Bytecode?

When you write Java code and compile it with `javac`, the compiler does not produce native machine code (like C/C++ does). Instead, it produces **bytecode** — a compact, intermediate set of instructions stored in `.class` files. Bytecode is not specific to any operating system or CPU architecture; it's a universal format designed to be read by the JVM.

When you run a program with the `java` command, the JVM reads this bytecode and either interprets it line by line, or compiles it on-the-fly to native code using a **Just-In-Time (JIT) compiler** for better performance.

---

## "Write Once, Run Anywhere" (WORA)

One of Java's most famous principles is **Write Once, Run Anywhere**. Because Java compiles to bytecode (not to platform-specific machine code), the same `.class` file produced on a Windows machine can be executed on a Linux server or a macOS laptop — as long as a JVM is installed on that platform.

In practice, this means a developer doesn't need to rewrite or recompile code for every operating system. The JVM acts as an abstraction layer between the Java program and the underlying hardware. This was a revolutionary idea when Java was introduced in 1995 and remains one of its most significant advantages for cross-platform software development.
