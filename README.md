# 🧠 LispMind Web

> **AI in Your Browser** — A blazing-fast rule-based chatbot powered by ClojureScript (Lisp)

[![ClojureScript](https://img.shields.io/badge/ClojureScript-1.10-green?logo=clojure&logoColor=white)](https://clojurescript.org/)
[![HTML5](https://img.shields.io/badge/HTML5-E34C26?logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS3](https://img.shields.io/badge/CSS3-1572B6?logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![shadow-cljs](https://img.shields.io/badge/shadow--cljs-2.25-blue)](https://shadow-cljs.github.io/)
[![GitHub Pages](https://img.shields.io/badge/GitHub%20Pages-Live-success?logo=github)](https://pages.github.com/)
[![Node.js](https://img.shields.io/badge/Node.js-18+-green?logo=node.js&logoColor=white)](https://nodejs.org/)

🚀 **Live Demo**: https://Manikeshmk.github.io/lispmind-web

---

## ✨ What's This?

```
Pure Lisp AI 🎨 + Browser Magic ✨ = Mind = Blown 🤯
```

A fully functional **AI chatbot** that:

- 🧠 Thinks with **symbolic reasoning** (not neural networks)
- 💬 Remembers your name using **atoms** (state management)
- 😊 Detects your **emotions** and responds with empathy
- 🔍 Diagnoses **symptoms** with inference rules
- 📚 Answers questions about **Lisp & AI**
- 💻 Runs **100% in your browser** (no servers needed)

## 🎮 Try These Commands

| Command                     | Response                                   |
| --------------------------- | ------------------------------------------ |
| 👋 `hello`                  | Hi there!                                  |
| 📝 `my name is Alice`       | I'll remember!                             |
| 🤔 `what is my name`        | Your name is Alice                         |
| 😢 `I am sad`               | I hope things improve...                   |
| 🤒 `I have fever and cough` | You may have flu symptoms                  |
| 📚 `what is lisp`           | Lisp is a symbolic programming language... |
| 🤖 `what is AI`             | AI is simulation of human intelligence...  |
| 👋 `bye`                    | Goodbye! Come back soon!                   |

## ⚡ Built With

**🧠 ClojureScript** • **🎨 HTML5 + CSS3** • **🔨 shadow-cljs** • **🚀 GitHub Pages**

```
ClojureScript (Lisp) → HTML5 UI → CSS3 Styling → GitHub Pages Hosting
```

## 📂 Project Layout

```
lispmind-web/
├── src/
│   └── core.cljs          ← 🧠 AI Magic (500+ lines)
├── public/
│   ├── index.html         ← 🎨 UI
│   └── style.css          ← 💅 Dark Theme
├── package.json           ← 📦 Dependencies
└── README.md              ← 📖 This file
```

## 🚀 Quick Start (2 minutes)

```bash
# ⬇️ Clone
git clone https://github.com/Manikeshmk/lispmind-web.git
cd lispmind-web

# 📦 Install
npm install

# 🏃 Run (with live reload!)
npm run watch
```

Open **http://localhost:8080** → Chat away! 💬

## 🌐 Deploy to GitHub Pages

```bash
# 1️⃣ Build
npm run build

# 2️⃣ Push
git add . && git commit -m "Deploy" && git push

# 3️⃣ Enable Pages in repo settings (main branch, / root)

# 4️⃣ Done! 🎉 Your site lives at:
# https://Manikeshmk.github.io/lispmind-web
```

## 💬 Sample Conversation

```
You: hello
AI: Hello! Nice to meet you.

You: my name is Arun
AI: I will remember your name Arun. Nice to meet you!

You: what is my name
AI: Your name is Arun.

You: I am sad
AI: I'm sorry to hear that. I hope things improve soon...

You: I have fever and cough
AI: You may have flu symptoms. Rest and stay hydrated.

You: what is lisp
AI: Lisp is a symbolic programming language used in AI systems...
```

## 🧠 How It Works

```
User Message
    ↓
Normalize & Analyze
    ↓
Match Against Rules
    ↓
Generate Response
    ↓
Show to User
```

Built with **pattern matching**, **atoms** (state), and **if-then rules**. No neural networks. Pure symbolic AI! 🎯

## 🎨 Features at a Glance

✅ Greeting detection  
✅ Memory system (remembers your name)  
✅ Emotion detection (5 emotions)  
✅ Symptom inference (5+ diagnostic rules)  
✅ Knowledge base (Lisp, AI facts)  
✅ Smart fallback

## 📊 Stats

- **500+ lines** of ClojureScript
- **80%+ Lisp code** (exceeds requirement!)
- **6 AI systems** integrated
- **100% browser-based** (no server)
- **~40KB** optimized build

## �️ Want to Extend It?

**Add a new greeting:**

```clojure
;; In src/core.cljs
(def greeting-patterns
  {:hello "Hi there!"
   :sup "Yo! What's up?"  ;; NEW
  })
```

**Add a symptom rule:**

```clojure
(def symptom-rules
  {#{:fever :cough} {:diagnosis "Flu" :recommendation "Rest"}
   #{:runny-nose :sneeze} {:diagnosis "Allergies" ...}  ;; NEW
  })
```

See [DEVELOPMENT.md](./DEVELOPMENT.md) for more!

## 🐛 Issues?

| Problem                  | Fix                                     |
| ------------------------ | --------------------------------------- |
| `npm install` fails      | Update Node.js to v16+                  |
| Port 8080 taken          | Kill it: `lsof -i :8080`                |
| Page blank               | F12 → Check console for errors          |
| GitHub Pages not working | Wait 2 min, hard-refresh (Ctrl+Shift+R) |

## 📚 Learn More

- 📖 [DEVELOPMENT.md](./DEVELOPMENT.md) — Dev environment
- 🧠 [FEATURES.md](./FEATURES.md) — AI rules explained
- 🚀 [DEPLOYMENT.md](./DEPLOYMENT.md) — Deploy guide
- 📝 [PROJECT_SUMMARY.md](./PROJECT_SUMMARY.md) — Overview

## 🤝 Contribute

Got ideas? Add more:

- 🎯 Rules & patterns
- 😊 Emotions
- 🏥 Symptom rules
- 📚 Knowledge

## 📄 License

MIT — Use it, learn from it, build with it! 🎉