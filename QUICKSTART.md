## Quick Start Commands

```bash
# Install dependencies
npm install

# Development (watch mode with live reload)
npm run watch

# Production build
npm run build

# Clean build
npm run clean

# Interactive REPL
npm run repl
```

## Local Development

1. Run `npm run watch`
2. Open http://localhost:8080
3. Start chatting!
4. Changes auto-reload

## Deployment

1. Build: `npm run build`
2. Commit: `git add . && git commit -m "Build"`
3. Push: `git push origin main`
4. GitHub Actions auto-deploys to Pages

## Try These Interactions

- Say "hello"
- Say "my name is [your name]"
- Ask "what is my name"
- Say "I am sad"
- Say "I have fever and cough"
- Ask "what is lisp"
- Ask "what is ai"

## File Structure

| File                | Purpose                                |
| ------------------- | -------------------------------------- |
| `src/core.cljs`     | AI logic (500+ lines of ClojureScript) |
| `public/index.html` | UI interface                           |
| `public/style.css`  | Dark modern theme                      |
| `package.json`      | Dependencies & scripts                 |
| `shadow-cljs.edn`   | Build configuration                    |

Enjoy! 🧠✨
