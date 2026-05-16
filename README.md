# 🧠 LispMind Web

AI Chatbot in your browser. Built with **ClojureScript (Lisp)**.

[![ClojureScript](https://img.shields.io/badge/ClojureScript-1.10-green?logo=clojure&logoColor=white&style=flat-square)](https://clojurescript.org/)
[![HTML5](https://img.shields.io/badge/HTML5-E34C26?logo=html5&logoColor=white&style=flat-square)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS3](https://img.shields.io/badge/CSS3-1572B6?logo=css3&logoColor=white&style=flat-square)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![shadow-cljs](https://img.shields.io/badge/shadow--cljs-2.25-blue?style=flat-square)](https://shadow-cljs.github.io/)

---

## ✨ Features

- 💾 **Remembers** your name
- 😊 **Detects emotions** (happy, sad, angry, tired, confused)
- 🔍 **Diagnoses symptoms** with inference rules
- 📚 **Answers** about Lisp & AI
- ⚡ **Fast** (~40KB, zero dependencies)
- 🚀 **100% client-side** (offline)

---

## 🎮 Try These

```
hello                    → Get a greeting
my name is Alice         → I'll remember!
what is my name          → Your name is Alice
I am sad                 → Empathetic response
I have fever and cough   → Symptom diagnosis
what is lisp             → Lisp explanation
```

---

## 🚀 Quick Start

```bash
git clone https://github.com/Manikeshmk/lispmind-web.git
# Install
npm install

# Run dev server
npm run watch

# Open http://localhost:8080
```

## 🏗️ Build

```bash
npm run build
```

---

## 📁 Project

```
src/core.cljs      ← 🧠 AI Engine (500+ lines Lisp)
public/index.html  ← 🎨 UI
public/style.css   ← 💅 Dark theme
```

---

## 📄 License

MIT
