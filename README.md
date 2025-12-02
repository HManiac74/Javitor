# Javitor - Java Text Editor

A lightweight, feature-rich text editor built with Java Swing, following modern software architecture principles and best practices.

![Version](https://img.shields.io/badge/version-1.0-blue.svg)
![Java](https://img.shields.io/badge/java-21-orange.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)

---

## 📋 Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Building from Source](#building-from-source)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## ✨ Features

### Core Functionality
- 📄 **File Operations**: New, Open, Save with UTF-8 encoding support
- 🔍 **Find & Replace**: Case-insensitive search with replace all functionality
- ✂️ **Edit Operations**: Clear text area, undo/redo with full history
- 💾 **Unsaved Changes Detection**: Automatic tracking with confirmation dialogs
- 📜 **Scrollable Text Area**: Full scroll support for large documents
- 🔢 **Line Numbers**: Automatic line numbering for better code navigation
- 📊 **Status Bar**: Real-time display of file name, type, and line count

### User Experience
- ⌨️ **Keyboard Shortcuts**: 
  - `Ctrl+N` - New file
  - `Ctrl+O` - Open file
  - `Ctrl+S` - Save file
  - `Ctrl+F` - Find
  - `Ctrl+Z` - Undo
  - `Ctrl+Y` - Redo
  - `Ctrl+K` - Clear
  - `Ctrl+F4` - Close
- 🎨 **Icon-based Toolbar**: Quick access to common operations
- 📊 **Dynamic Window Title**: Shows filename and modification status (*)
- 📍 **Status Bar**: Shows current file info and dirty state indicator
- ⚠️ **Error Handling**: User-friendly error dialogs with helpful messages
- 🔄 **Dialog Reuse**: Efficient memory management for find dialog

### Technical Features
- 🏗️ **MVC Architecture**: Clean separation of Model, View, and Controller
- 🎯 **Action-Based UI**: Swing Actions for consistent behavior
- 📦 **Resource Management**: Efficient icon loading with caching
- 🛡️ **Thread Safety**: Proper EDT (Event Dispatch Thread) usage
- 💪 **Modern Java**: NIO for file I/O, lambdas, try-with-resources

---

## 🏛️ Architecture

Javitor follows an MVC-inspired architecture with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                    SimpleJavaTextEditor                  │
│                    (Entry Point)                         │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                         UI                               │
│                   (Main Frame)                           │
├─────────────────────────────────────────────────────────┤
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ DocumentModel│  │ FileManager  │  │ResourceManager│  │
│  │   (State)    │  │    (I/O)     │  │  (Resources) │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│                                                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ FileActions  │  │ EditActions  │  │ FindActions  │  │
│  │   (New,      │  │   (Clear)    │  │   (Find)     │  │
│  │ Open, Save)  │  │              │  │              │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
```

### Package Structure

```
jv/
├── SimpleJavaTextEditor.java    # Application entry point
├── UI.java                      # Main application window
├── Find.java                    # Find/Replace dialog
├── About.java                   # About dialogs
│
├── model/
│   └── DocumentModel.java       # Document state management
│
├── controller/
│   └── FileManager.java         # File I/O operations
│
├── actions/
│   ├── FileActions.java         # File operation actions
│   ├── EditActions.java         # Edit operation actions
│   └── FindActions.java         # Search operation actions
│
├── components/
│   ├── LineNumberComponent.java # Line number display
│   ├── StatusBar.java           # Status bar component
│   ├── UIMenuBar.java           # Menu bar component
│   └── UIToolBar.java           # Toolbar component
│
└── util/
    ├── Constants.java           # Application constants
    ├── DialogUtils.java         # Reusable dialogs
    └── ResourceManager.java     # Resource loading & caching
```

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Version 3.6 or higher
- **Operating System**: Windows, macOS, or Linux

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/HManiac74/Javitor.git
   cd Javitor
   ```

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the application**
   ```bash
   java -jar target/javitor-1.0-SNAPSHOT.jar
   ```

---

## 🔨 Building from Source

### Using Maven

```bash
# Clean and package
mvn clean package

# Run
java -jar target/javitor-1.0-SNAPSHOT.jar
```

---

## 📁 Project Structure

```
Javitor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── jv/               # Java source code
│   │   └── resources/
│   │       └── icons/            # Application icons
│
├── target/                       # Compiled output (generated)
├── pom.xml                       # Maven configuration
├── .gitignore                    # Git ignore rules
└── README.md                     # This file
```

---

## 💻 Development

### Code Style

- **Indentation**: Tabs
- **Naming**: 
  - Classes: PascalCase
  - Methods: camelCase
  - Constants: UPPER_SNAKE_CASE
- **Documentation**: JavaDoc for public methods and classes

### Key Design Patterns

1. **MVC Pattern**: Separation of Model (DocumentModel), View (UI), and Controller (FileManager)
2. **Action Pattern**: Swing Actions for UI operations
3. **Singleton Pattern**: ResourceManager for icon caching
4. **Factory Pattern**: Static factory methods in About dialog
5. **Observer Pattern**: DocumentListener for state changes

### Adding New Features

#### Example: Adding a New Menu Item

```java
// 1. Create an Action class in actions/
public class MyAction extends AbstractAction {
    public MyAction(Component parent) {
        super("My Action");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Your logic here
    }
}

// 2. Initialize in UI.java
private void initializeActions() {
    // ... existing actions
    myAction = new MyAction(this);
}

// 3. Add to menu in setupMenuBar()
JMenuItem myItem = createMenuItem("My Action", myAction, 
    ResourceManager.getIcon("icons/my_icon.png"), KeyEvent.VK_M);
menuEdit.add(myItem);
```

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit your changes** (`git commit -m 'Add amazing feature'`)
4. **Push to the branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### Areas for Improvement

- [x] Add undo/redo functionality
- [ ] Implement syntax highlighting
- [x] Add line numbers
- [ ] Create recent files menu
- [ ] Add print support
- [ ] Implement themes/dark mode
- [ ] Add auto-save functionality
- [ ] Support for multiple tabs
- [ ] Add unit tests

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**hmaniac28**

- Email: [hmaniac28@yahoo.de](mailto:hmaniac28@yahoo.de)
- GitHub: [@HManiac74](https://github.com/HManiac74)

---

## 🙏 Acknowledgments

- Built with Java Swing
- Icons designed for clarity and usability
- Inspired by classic text editors with modern architecture

---

## 📊 Version History

### Version 1.0 (Current)
- ✅ Complete refactoring with MVC architecture
- ✅ Modern Java practices (NIO, lambdas, try-with-resources)
- ✅ Undo/redo functionality with keyboard shortcuts
- ✅ Unsaved changes detection
- ✅ Scrollable text area
- ✅ Improved error handling
- ✅ Resource management with caching
- ✅ Memory leak fixes
- ✅ Action-based UI architecture
- ✅ Updated to Java 21
- ✅ Added Line Numbers feature
- ✅ Added Status Bar with file info display
- ✅ Refactored UI components (MenuBar, ToolBar)

---

## 🐛 Known Issues

None currently reported. Please [open an issue](https://github.com/HManiac74/Javitor/issues) if you find any bugs.

---

## 💡 Tips & Tricks

- **Large Files**: The editor handles large files efficiently with scrolling support
- **UTF-8 Encoding**: All files are saved with UTF-8 encoding by default
- **Keyboard Shortcuts**: Use keyboard shortcuts for faster workflow
- **Find Dialog**: The find dialog stays open for multiple searches
- **Unsaved Changes**: Always prompted before losing unsaved work

---

## 📞 Support

For questions, issues, or suggestions:
- Open an issue on GitHub
- Email: hmaniac28@yahoo.de

---

<div align="center">

**Made with ❤️ using Java Swing**

⭐ Star this repository if you find it helpful!

</div>
