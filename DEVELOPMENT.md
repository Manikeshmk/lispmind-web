# Development Guide

## Setup Development Environment

### Prerequisites

- Node.js 18+ (check `.nvmrc`)
- Java 11+ (required by shadow-cljs)
- Git

### Initial Setup

```bash
# Clone repository
git clone https://github.com/Manikeshmk/lispmind-web.git
cd lispmind-web

# Install Node version (optional, using nvm)
nvm use

# Install dependencies
npm install
```

## Running Locally

### Development Mode (Watch)

```bash
npm run watch
```

This starts the shadow-cljs watch server. The app will be available at `http://localhost:8080` with live reload enabled.

### Production Build

```bash
npm run build
```

Generates optimized JavaScript in `public/js/`.

### REPL (Interactive)

```bash
npm run repl
```

Starts a browser REPL for interactive ClojureScript development.

## Project Structure Explained

```
src/
└── core.cljs
    ├── Memory System (atoms)
    ├── String Preprocessing
    ├── Knowledge Base
    ├── Emotion Detection
    ├── Symptom Inference Engine
    ├── Response Generation
    ├── UI/DOM Interaction
    └── Event Listeners
```

## Key Code Sections

### 1. Memory System

```clojure
(def memory (atom {}))

(remember :name "Alice")
(recall :name)  ;; => "Alice"
```

### 2. Pattern Matching

```clojure
(defn respond [input]
  (let [text (normalize input)]
    (cond
      (str/includes? text "hello") "Hi there!"
      :else "I don't understand")))
```

### 3. Rule-Based Inference

```clojure
(def symptom-rules
  {#{:fever :cough} {:diagnosis "Flu" :recommendation "Rest"}})

(infer-diagnosis "I have fever and cough")
;; => {:diagnosis "Flu" :recommendation "Rest"}
```

### 4. DOM Manipulation

```clojure
(add-message :user "Hello")
(add-message :ai "Hi there!")
```

## Extending the Chatbot

### Adding a New Greeting

1. Open `src/core.cljs`
2. Update `greeting-patterns`:

```clojure
(def greeting-patterns
  {:hello "Hello! Nice to meet you."
   :hi "Hi there!"
   :sup "Sup! What's good?"  ;; NEW
  })
```

3. Save and watch mode will auto-reload

### Adding a New Symptom Rule

1. Update `symptom-rules` in `src/core.cljs`:

```clojure
(def symptom-rules
  {
   #{:fever :cough} {...}
   #{:headache :nausea} {...}  ;; NEW
  })
```

### Adding Knowledge

1. Update `knowledge-base`:

```clojure
(def knowledge-base
  {:lisp "..."
   :rust "Rust is a systems programming language..."  ;; NEW
  })
```

Then handle it in `respond`:

```clojure
(str/includes? normalized "what is rust")
(lookup-knowledge :rust)
```

## Building Features

### Emotion Detection Logic

The emotion detection system:

1. Normalizes input (lowercase)
2. Checks against `emotion-keywords` map
3. Returns matching emotion keyword
4. Generates appropriate response

Example flow:

```
"I am SO SAD"
  → normalize
  → "i am so sad"
  → detect-emotion
  → :sad
  → emotion-response
  → "I'm sorry to hear that..."
```

### Inference Engine

The symptom inference system:

1. Extracts symptom mentions from text
2. Creates a set of identified symptoms
3. Matches against `symptom-rules`
4. Returns diagnosis and recommendation

Example:

```
"I have fever and cough"
  → extract-symptoms
  → #{:fever :cough}
  → infer-diagnosis
  → {:diagnosis "Flu" ...}
```

## Testing in Browser

### Console Access

Open DevTools (F12) and check:

- Memory state: `lispmind.core.memory.deref()`
- Test respond: `lispmind.core.respond("hello")`

### Manual Testing Scenarios

```
1. Greeting
   Input: "hello"
   Expected: "Hello! Nice to meet you."

2. Name Storage
   Input: "my name is alice"
   Expected: "I will remember your name Alice..."

3. Name Recall
   Input: "what is my name"
   Expected: "Your name is Alice."

4. Emotion
   Input: "i am sad"
   Expected: "I'm sorry to hear that..."

5. Symptom Inference
   Input: "i have fever and cough"
   Expected: "You may have flu symptoms..."

6. Knowledge
   Input: "what is lisp"
   Expected: [Knowledge about Lisp]

7. Unknown
   Input: "xyzabc123"
   Expected: "I do not understand..."
```

## Performance Tips

1. **Symbol Resolution**: ClojureScript is optimized for large apps
2. **String Operations**: `str/includes?` is fast for keyword detection
3. **Atom Operations**: `swap!` is atomic and thread-safe in browser context
4. **Regex Caching**: Consider caching `re-find` patterns for frequent use

## Debugging

### Browser Console

```javascript
// Access ClojureScript functions
lispmind.core.respond("hello");

// Access memory
lispmind.core.memory.deref();

// Call other functions
lispmind.core.normalize("HELLO");
```

### shadow-cljs Console Output

Watch for ClojureScript warnings:

```
[:app] Build complete
```

### Common Errors

| Error                   | Fix                              |
| ----------------------- | -------------------------------- |
| `Cannot find module`    | Run `npm install`                |
| Java not found          | Install Java 11+                 |
| Port 8080 in use        | Kill process: `lsof -i :8080`    |
| Live reload not working | Check browser console for errors |

## Deployment Checklist

- [ ] Run `npm run build`
- [ ] Verify `public/js/main.js` exists
- [ ] Test in production mode locally
- [ ] Commit changes: `git add . && git commit -m "Build for production"`
- [ ] Push to GitHub: `git push origin main`
- [ ] Check GitHub Actions deployment
- [ ] Verify live site at `https://username.github.io/lispmind-web`

## Advanced: Customizing the AI

### Add Multi-Turn Conversation

```clojure
(def conversation-history (atom []))

(defn add-to-history [role text]
  (swap! conversation-history conj {:role role :text text}))
```

### Add Fuzzy Matching

```clojure
(defn fuzzy-match [pattern text]
  ;; Implement Levenshtein distance
  )
```

### Add Learning

```clojure
(def learned-responses (atom {}))

(defn learn [pattern response]
  (swap! learned-responses assoc pattern response))
```

### Add Persistence

```clojure
(defn save-memory []
  (js/localStorage.setItem "lispmind-memory" (pr-str @memory)))

(defn load-memory []
  (when-let [data (js/localStorage.getItem "lispmind-memory")]
    (reset! memory (read-string data))))
```

## Resources

- [ClojureScript Guide](https://clojurescript.org/guides/getting-started)
- [shadow-cljs Docs](https://shadow-cljs.github.io/)
- [Clojure String API](https://clojure.github.io/clojure/clojure.string-api.html)
- [Browser APIs](https://developer.mozilla.org/en-US/docs/Web/API)

---

Happy coding! 🚀
