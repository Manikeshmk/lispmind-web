# LispMind Web — A Rule-Based AI Assistant

> **Symbolic AI for the Browser** — A complete chatbot implementation powered by ClojureScript, demonstrating rule-based reasoning, pattern matching, and inference systems.

## 🎯 Overview

LispMind Web is an intelligent chatbot assistant that runs entirely in your browser. Built with **ClojureScript** (a Lisp dialect), it showcases modern symbolic AI concepts:

- **Rule-Based Reasoning**: If-then rules for decision making
- **Pattern Matching**: Sophisticated text analysis
- **Memory Management**: Persistent user information
- **Inference Engine**: Symptom-to-diagnosis reasoning
- **Natural Language Processing**: Intent and emotion detection
- **Functional Programming**: Pure functions and immutable state

## ✨ Key Features

### 1. **Greeting Detection**
The AI recognizes greetings like "hello", "hi", "hey" and responds warmly.

```
User: hello
AI: Hello! Nice to meet you.
```

### 2. **Memory System**
Store and retrieve user information using ClojureScript atoms.

```
User: my name is Arun
AI: I will remember your name Arun. Nice to meet you!

User: what is my name
AI: Your name is Arun.
```

### 3. **Emotion Detection**
Identifies emotions and responds with empathy.

```
User: I am sad
AI: I'm sorry to hear that. I hope things improve soon. Remember that better days are ahead.
```

### 4. **Inference Engine**
Applies symbolic reasoning rules to diagnose based on symptoms.

```
User: I have fever and cough
AI: You may have flu symptoms. Rest and stay hydrated.
```

### 5. **Knowledge Questions**
Answer domain-specific questions about Lisp and AI.

```
User: what is lisp
AI: Lisp is a symbolic programming language used in AI systems...

User: what is AI
AI: Artificial Intelligence is the simulation of human intelligence by machines...
```

### 6. **Smart Fallback**
Graceful handling of unknown inputs.

```
User: xyzabc
AI: I do not understand. Could you rephrase that? Try saying 'help' for suggestions.
```

## 🛠 Technologies

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **AI Logic** | ClojureScript | Symbolic reasoning, rule engine |
| **Frontend** | HTML5, CSS3 | Modern responsive UI |
| **Build Tool** | shadow-cljs | ClojureScript compilation |
| **Deployment** | GitHub Pages | Free hosting |

## 📋 Project Structure

```
lispmind-web/
├── public/
│   ├── index.html          # Main HTML interface
│   ├── style.css           # Dark modern theme
│   └── js/
│       └── main.js         # Compiled ClojureScript (generated)
├── src/
│   └── core.cljs           # AI logic & chatbot engine
├── package.json            # Node dependencies
├── shadow-cljs.edn         # ClojureScript compiler config
├── .gitignore
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Node.js** v16+ and **npm**
- **Java** (required by shadow-cljs)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Manikeshmk/lispmind-web.git
   cd lispmind-web
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

### Development

1. **Start watch mode** (live reload enabled)
   ```bash
   npm run watch
   ```

2. **Open in browser**
   ```
   http://localhost:8080
   ```

The build will watch for changes and recompile automatically.

### Production Build

1. **Compile for production**
   ```bash
   npm run build
   ```

2. **Artifacts** are generated in `public/js/`

## 🌐 Deployment on GitHub Pages

### Step 1: Prepare GitHub Repository

```bash
git init
git add .
git commit -m "Initial commit: LispMind Web"
git branch -M main
git remote add origin https://github.com/Manikeshmk/lispmind-web.git
git push -u origin main
```

### Step 2: Enable GitHub Pages

1. Go to your repository settings
2. Navigate to **Settings → Pages**
3. Select **Deploy from a branch**
4. Choose **main** branch and **/root** folder
5. Save

### Step 3: Deploy

```bash
npm run build
git add public/js/
git commit -m "Build for production"
git push
```

Your site will be live at: `https://Manikeshmk.github.io/lispmind-web`

## 💬 Live Demo Conversation

Here's an example chat session demonstrating the chatbot's capabilities:

```
╔════════════════════════════════════════════════════════════╗
║          LispMind Web — Live Demo Conversation              ║
╚════════════════════════════════════════════════════════════╝

User: hello
AI: Hello! Nice to meet you.

User: my name is Arun
AI: I will remember your name Arun. Nice to meet you!

User: what is my name
AI: Your name is Arun.

User: I am sad
AI: I'm sorry to hear that. I hope things improve soon. 
    Remember that better days are ahead.

User: I have fever and cough
AI: You may have flu symptoms. Rest and stay hydrated.

User: what is lisp
AI: Lisp is a symbolic programming language used in AI systems. 
    Created by John McCarthy in 1958, it emphasizes code-as-data 
    and homoiconicity.

User: what is AI
AI: Artificial Intelligence is the simulation of human intelligence 
    by machines. It includes machine learning, reasoning, perception, 
    and natural language understanding.

User: bye
AI: Goodbye! Come back soon!
```

## 🧠 AI Architecture

### Rule-Based Reasoning Pipeline

```
User Input
    ↓
[Normalize Text]
    ↓
[Pattern Matching]
    ├→ Name Storage/Recall
    ├→ Greeting Detection
    ├→ Knowledge Lookup
    ├→ Emotion Detection
    ├→ Symptom Inference
    └→ Fallback
    ↓
AI Response
```

### Key ClojureScript Features Used

1. **Atoms for State**: `(atom {})` for persistent memory
2. **Pattern Matching**: `cond` and `str/includes?` for rule matching
3. **Pure Functions**: Immutable data transformations
4. **Higher-Order Functions**: `some`, `reduce`, `swap!`
5. **Regular Expressions**: `re-find`, `re-seq` for text parsing

## 📝 Code Examples

### Adding a New Greeting

```clojure
;; In src/core.cljs, update greeting-patterns:
(def greeting-patterns
  {:hello "Hello! Nice to meet you."
   :hi "Hi there! How can I help you?"
   :howdy "Howdy partner!"  ;; New greeting
  })
```

### Adding a New Symptom Rule

```clojure
;; In src/core.cljs, update symptom-rules:
(def symptom-rules
  {
   #{:fever :cough} {:diagnosis "You may have flu symptoms." :recommendation "Rest and stay hydrated."}
   #{:runny-nose :congestion} {:diagnosis "You may have allergies." :recommendation "Consider antihistamines."}  ;; New rule
  })
```

### Adding Knowledge

```clojure
;; In src/core.cljs, update knowledge-base:
(def knowledge-base
  {:lisp "Lisp is..."
   :python "Python is a versatile programming language..."  ;; New entry
  })
```

## 🎨 UI Customization

The interface uses CSS custom properties (variables) for easy theming:

```css
:root {
    --primary-color: #00d4ff;          /* Cyan accent */
    --secondary-color: #6366f1;        /* Indigo accent */
    --background-dark: #0f0f1e;        /* Very dark blue */
    --text-primary: #ffffff;           /* White text */
    /* ... more colors ... */
}
```

Change colors in `public/style.css` to customize the theme.

## 🔍 Browser Console

The chatbot logs initialization messages. Open your browser's developer tools (F12) to see:

```
🧠 LispMind Web — Symbolic AI Assistant Initialized!
📚 Built with ClojureScript & Rule-Based Reasoning
```

## 📊 Statistics

- **80%+ ClojureScript**: All AI logic in Lisp-family code
- **20% HTML/CSS**: UI presentation layer
- **~500 lines**: Complete implementation
- **0 external dependencies**: Pure symbolic AI

## 🚀 Advanced Features (Optional)

### localStorage Persistence
```clojure
(defn persist-memory []
  (js/localStorage.setItem "user-memory" (pr-str @memory)))
```

### Typing Animation
```css
.message-bubble {
  animation: typing 1s steps(1, end);
}
```

### Voice Input
```javascript
const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
```

### Markdown Support
Use markdown in responses for formatted text.

### Learning New Responses
Extend `respond` to learn from user feedback.

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| `npm install` fails | Update Node.js to v16+ |
| `shadow-cljs` not found | Run `npm install` again |
| No JavaScript generated | Run `npm run build` instead of watch |
| Page shows blank | Check browser console (F12) for errors |
| GitHub Pages not updating | Wait 1-2 minutes and hard-refresh (Ctrl+Shift+R) |

## 📚 Learning Resources

- [ClojureScript Official](https://clojurescript.org/)
- [shadow-cljs Documentation](https://shadow-cljs.github.io/user-guide/)
- [Clojure for the Brave and True](https://www.braveclojure.com/)
- [Symbolic AI](https://en.wikipedia.org/wiki/Symbolic_artificial_intelligence)

## 🎓 Educational Value

This project demonstrates:

1. **Symbolic AI**: Rule-based systems vs. neural networks
2. **Functional Programming**: Immutable data & pure functions
3. **Lisp Dialects**: ClojureScript syntax and semantics
4. **Web Architecture**: Frontend logic + UI interaction
5. **Browser APIs**: DOM manipulation & event handling
6. **NLP Basics**: Intent detection & entity extraction
7. **Inference**: Forward chaining with symptom rules
8. **State Management**: Atoms for persistent state

## 🤝 Contributing

Contributions welcome! Ideas:

- Add more symptom inference rules
- Implement dialogue management
- Add fuzzy matching for typo tolerance
- Create multi-turn conversation memory
- Implement a simple learning mechanism

## 📄 License

MIT License - Feel free to use this for learning and projects!

## 👨‍💻 Author

Built with ❤️ as a demonstration of Lisp-family AI systems in the browser.

---

## 🔗 Quick Links

- **Live Demo**: https://Manikeshmk.github.io/lispmind-web
- **Repository**: https://github.com/Manikeshmk/lispmind-web
- **Issues**: https://github.com/Manikeshmk/lispmind-web/issues

---

**Made with 🧠 + 🎨 + 📝**

Start chatting with LispMind and explore symbolic AI in your browser!
#
