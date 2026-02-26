# Guardia

**Guardia** is a minimalistic JavaFX application designed for mass-generating and exporting cryptographically secure passwords. It boasts a simple UI along with robust logic to ensure unpredictable, secure passwords are generated.

---

## Features

* **Secure Generation**: Uses `java.security.SecureRandom` to ensure high entropy.
* **Guaranteed Complexity**: Every password is guaranteed to include at least one uppercase letter, one lowercase letter, one number, and one symbol.
* **Mass Production**: Generate anywhere from **1 to 10,000** passwords at once.
* **Custom Lengths**: Supports password lengths between **8 and 64** characters.
* **Intelligent Copy**: Double-click any item in the list to copy the password. The app automatically strips the list index and extra padding before sending it to the clipboard.
* **Flexible Exporting**: Save your list as a `.txt` file via a native `FileChooser`. Use the **"Include numbering?"** checkbox to decide if you want the indices saved to the file.
* **Dynamic UI Alerts**: "Password copied!" and "Exported!" labels appear temporarily via `PauseTransition` to provide non-intrusive feedback.

---

## Architecture

The project is built using a modular approach to separate UI management from core logic:

| Class | Responsibility |
| :--- | :--- |
| **AppLauncher** | Entry point for the JAR. |
| **Main** | Configures the primary stage, sets the app icon, and loads the FXML. |
| **Controller** | Manages UI interactions, `ObservableList` binding, and clipboard logic. |
| **Generator** | A dedicated utility class for the password-shuffling algorithm. |
| **Exporter** | Handles file system interactions and output formatting. |

---

## Installation

1. **Prerequisites**: Ensure you have **Java 17+** and the **JavaFX SDK** installed.
2. **Clone the Repo**:
    ```bash
    git clone [https://github.com/ramen106/guardia.git](https://github.com/ramen106/guardia.git)
    ```
3. **Compile & Run**: Launch the app through your preferred IDE or via Maven/Gradle using the `Main.java` entry point.

---

## Security Logic
To prevent predictable patterns, the program:
1. Picks one character from each required pool (Upper, Lower, Number, Symbol).
2. Fills the remaining length with random characters from the combined pool.
3. **Shuffles** those first four guaranteed characters into random positions within the string to ensure the "secure" characters aren't always at the start.
