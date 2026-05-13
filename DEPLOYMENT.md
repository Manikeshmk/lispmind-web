# 🚀 Deployment Guide

## What's Included

Your LispMind Web project contains:

### Source Code

- **src/core.cljs** (500+ lines)
  - Memory system with atoms
  - String preprocessing
  - Emotion detection
  - Symptom inference engine
  - Knowledge base
  - Response generation
  - DOM interaction
  - Event handling

- **public/index.html**
  - Modern chatbot UI
  - Responsive design
  - Message display area
  - Input field with send button

- **public/style.css**
  - Dark modern theme
  - Animated components
  - Responsive layout
  - Accessibility features

### Configuration

- **package.json** - Dependencies & scripts
- **shadow-cljs.edn** - ClojureScript build config
- **.nvmrc** - Node.js version (18)
- **.github/workflows/deploy.yml** - Auto-deployment

### Documentation

- **README.md** - Full project documentation
- **DEVELOPMENT.md** - Dev environment guide
- **FEATURES.md** - AI features & rules
- **QUICKSTART.md** - Quick reference

---

## Deploy to GitHub

### Step 1: Push to Your Repository

```bash
cd c:\Users\deii\Desktop\github\lispmind-web

git init
git add .
git commit -m "🧠 Initial LispMind Web - Rule-Based AI Chatbot"
git branch -M main
git remote add origin https://github.com/Manikeshmk/lispmind-web.git
git push -u origin main
```

### Step 2: Enable GitHub Pages

1. Go to https://github.com/Manikeshmk/lispmind-web
2. Click **Settings**
3. Scroll to **Pages** section
4. Under "Build and deployment":
   - Source: **Deploy from a branch**
   - Branch: **main**
   - Folder: **/root** (or **/ (root)** on some interfaces)
5. Click **Save**

### Step 3: Initial Build & Deploy

```bash
# Build for production
npm install
npm run build

# Commit the build
git add public/js/
git commit -m "🚀 Build for production"
git push
```

### Step 4: GitHub Actions Setup

The `.github/workflows/deploy.yml` file automatically:

- Installs dependencies
- Builds the project
- Deploys to GitHub Pages

No additional setup needed! GitHub Actions runs automatically on every push.

---

## Access Your Live Site

After 1-2 minutes, your site will be live at:

```
https://Manikeshmk.github.io/lispmind-web
```

Share this link! 🌐

---

## Development Workflow

### For Development

```bash
npm install
npm run watch
# Opens http://localhost:8080 with live reload
```

### For Production Deployment

```bash
npm run build
git add public/js/
git commit -m "Production build"
git push
# GitHub Actions auto-deploys
```

---

## Project Statistics

| Metric                  | Value                          |
| ----------------------- | ------------------------------ |
| **Lisp Code**           | 85%+                           |
| **ClojureScript Lines** | 500+                           |
| **AI Features**         | 6 major systems                |
| **Response Rules**      | 8+ decision paths              |
| **CSS Animations**      | 10+ custom animations          |
| **Memory Rules**        | Pattern-based state management |
| **Build Size**          | ~40KB (optimized)              |

---

## AI Features Implemented

✅ **Greeting Detection**

- Detects: hello, hi, hey

✅ **Memory System**

- Store and recall user information
- Atom-based state management

✅ **Emotion Detection**

- Detects: happy, sad, angry, tired, confused
- Empathetic responses

✅ **Inference Engine**

- Symptom → Diagnosis reasoning
- Rule-based medical inference
- 5+ diagnostic rules

✅ **Knowledge Base**

- Lisp, AI, Clojure, Rules, Inference
- Semantic knowledge storage

✅ **Smart Fallback**

- Help system
- Unknown input handling

---

## Testing Your Deployment

### Test Cases

1. **Greeting**

   ```
   You: hello
   AI: Hello! Nice to meet you.
   ```

2. **Memory**

   ```
   You: my name is Arun
   AI: I will remember your name Arun. Nice to meet you!

   You: what is my name
   AI: Your name is Arun.
   ```

3. **Emotion**

   ```
   You: I am sad
   AI: I'm sorry to hear that. I hope things improve soon...
   ```

4. **Inference**

   ```
   You: I have fever and cough
   AI: You may have flu symptoms. Rest and stay hydrated.
   ```

5. **Knowledge**
   ```
   You: what is lisp
   AI: Lisp is a symbolic programming language used in AI systems...
   ```

---

## File Manifest

```
lispmind-web/
│
├── .github/
│   └── workflows/
│       └── deploy.yml              ← Auto-deployment config
│
├── public/
│   ├── index.html                  ← Main interface
│   ├── style.css                   ← Dark theme styles
│   └── js/                         ← Generated on build
│
├── src/
│   └── core.cljs                   ← AI logic (ClojureScript)
│
├── .gitignore                      ← Git ignore rules
├── .nvmrc                          ← Node.js version
├── package.json                    ← Dependencies
├── shadow-cljs.edn                 ← Build config
│
├── README.md                       ← Full documentation
├── DEVELOPMENT.md                  ← Dev guide
├── FEATURES.md                     ← AI features detail
├── QUICKSTART.md                   ← Quick reference
└── DEPLOYMENT.md                   ← This file
```

---

## Troubleshooting

| Issue                         | Solution                                      |
| ----------------------------- | --------------------------------------------- |
| **Build fails locally**       | Run `npm install` again                       |
| **Port 8080 in use**          | Kill process: `lsof -i :8080` then retry      |
| **GitHub Pages not updating** | Wait 2-3 minutes, hard-refresh (Ctrl+Shift+R) |
| **Java not found**            | Download Java 11+ from oracle.com             |
| **Node version issues**       | Use nvm: `nvm use` (checks .nvmrc)            |

---

## Next Steps

1. ✅ Deploy to GitHub (this guide)
2. ✅ Test at live URL
3. ✅ Share with friends
4. 📖 Read [FEATURES.md](./FEATURES.md) to understand the AI
5. 🎨 Customize theme in [public/style.css](./public/style.css)
6. 🧠 Add new rules in [src/core.cljs](./src/core.cljs)

---

## Advanced Customization

### Add a New Rule

```clojure
;; In src/core.cljs, update 'respond' function:

(cond
  (str/includes? text "new pattern")
  "Custom response"

  ; ... rest of rules
)
```

### Change Theme Colors

```css
/* In public/style.css */
:root {
  --primary-color: #YOUR_COLOR;
  --secondary-color: #YOUR_COLOR;
  /* ... */
}
```

### Add Symptoms

```clojure
;; In src/core.cljs:

(def symptom-keywords
  {"your-symptom" :keyword
   ; ... existing
  })
```

---

## Support & Resources

- **ClojureScript**: https://clojurescript.org/
- **shadow-cljs**: https://shadow-cljs.github.io/
- **GitHub Pages**: https://pages.github.com/
- **MDN Web Docs**: https://developer.mozilla.org/

---

## 🎉 You're All Set!

Your LispMind Web chatbot is ready to amaze the world with **Lisp-powered AI** in the browser!

```
   🧠 LispMind Web
   Rule-Based AI Assistant
   Powered by ClojureScript
```

**Live at**: https://Manikeshmk.github.io/lispmind-web

---

_Built with ❤️ to demonstrate symbolic AI for the modern web_
