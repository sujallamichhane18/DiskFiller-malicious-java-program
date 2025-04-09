

---

# DiskFiller

**DiskFiller** is a Java program designed to quickly and aggressively consume available disk space on a specified drive. This tool is typically used for stress testing or in controlled environments (e.g., virtual machines) to test system behavior when disk space is exhausted.

**WARNING:**  
Running this tool on your system drive (C:) will fill the disk completely, causing severe system instability, loss of data, and potential system crashes. **Use only in safe, controlled environments** (e.g., virtual machines or non-critical systems).

---

## Features

- **Fast File Creation:** Concurrent threads write 1GB files (customizable size) to the disk.
- **Customizable Parameters:** Easily modify file size, target directory, and buffer size.
- **Multi-Threading:** Uses multiple threads to write files simultaneously for quicker disk filling.

---

## Setup and Usage

### Prerequisites

- Java 8 or later installed on your machine.
- Proper permissions to write to the target directory (default is `C:\`).

### Installation

1. Clone the repository or download the `DiskFiller.java` file.

   ```bash
   git clone https://github.com/ysujallamichhane18/DiskFiller.git
   cd DiskFiller
   ```

2. Compile the Java file:

   ```bash
   javac DiskFiller.java
   ```

3. Run the program:

   ```bash
   java DiskFiller
   ```

   **WARNING:** This will start filling your disk drive. Use at your own risk!

---

## Configuration

You can modify the following constants in the `DiskFiller.java` file to suit your needs:

- `TARGET_DIRECTORY`: Set the target directory to fill. The default is `C:\`.
- `FILE_SIZE_MB`: The size of each file created in MB (default is 1024MB, or 1GB).
- `BUFFER_SIZE`: Size of the buffer used for writing data. Default is 8MB.
- `NUM_THREADS`: The number of concurrent threads for faster file creation. Default is 4.

---

## Example

If you want to create files of 500MB each and write to a different directory, you can modify the configuration like so:

```java
private static final String TARGET_DIRECTORY = "D:\\StorageTest"; // Custom directory
private static final int FILE_SIZE_MB = 500; // Files of 500MB
private static final int NUM_THREADS = 8; // 8 concurrent threads
```

---

## Important Notes

- **Data Loss Risk:** Running this tool on your system drive will cause your disk to fill up, which may make your operating system or applications stop working properly. **Do not run this on a system you rely on.**
- **Testing Environment:** It is highly recommended to run this on virtual machines or isolated environments where you can safely test without affecting critical data.



## Disclaimer

This tool is for **educational purposes** and should be used with **extreme caution**. **The author is not responsible for any damage, data loss, or system failures** that result from using this software.

---
