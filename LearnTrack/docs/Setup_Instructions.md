# Setup Instructions

## JDK Version Used
- **JDK 17** (Long-Term Support release)
- Download from: https://www.oracle.com/java/technologies/downloads/ or https://adoptium.net/

## Installation Steps

### Windows
1. Download the JDK 17 installer from the official Oracle or Adoptium website.
2. Run the `.exe` installer and follow the prompts.
3. Set the `JAVA_HOME` environment variable:
   - Right-click **This PC** → Properties → Advanced System Settings → Environment Variables
   - Add new System variable: `JAVA_HOME` = `C:\Program Files\Java\jdk-17`
   - Add `%JAVA_HOME%\bin` to the `Path` variable
4. Verify installation:
   ```
   java -version
   javac -version
   ```

### macOS / Linux
```bash
# macOS with Homebrew
brew install openjdk@17

# Ubuntu/Debian
sudo apt-get install openjdk-17-jdk

# Verify
java -version
javac -version
```

---

## Hello World — Verification

Create a file `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:
```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:
```
Hello, World!
```

---

## VS Code Setup

1. Install **Visual Studio Code** from https://code.visualstudio.com/
2. Install the **Extension Pack for Java** (by Microsoft) from the Extensions marketplace
3. Open the `LearnTrack` folder in VS Code
4. VS Code will auto-detect the Java project structure
5. Open `Main.java` and click the **▶ Run** button above the `main` method
