# AI Features & Rules

## Overview

LispMind implements a **rule-based inference system** using ClojureScript. This document details each feature and the reasoning rules behind them.

## Feature Categories

### 1. Greeting Detection

**Rules**: Pattern matching against greeting keywords

```clojure
(def greeting-patterns
  {:hello "Hello! Nice to meet you."
   :hi "Hi there! How can I help you?"
   :hey "Hey! What's on your mind?"})
```

**Trigger Patterns**:

- Contains "hello"
- Contains "hi " (with space to avoid "high", "hint", etc.)
- Contains "hey"

**Logic Flow**:

```
Input: "hello there"
  ↓ normalize to lowercase
"hello there"
  ↓ detect-greeting checks patterns
:hello found
  ↓ lookup response
"Hello! Nice to meet you."
```

---

### 2. Memory System

**Architecture**: Atom-based persistent state

```clojure
(def memory (atom {}))
```

**Operations**:

- `remember :key :value` - Store data
- `recall :key` - Retrieve data

**Rules**:

#### 2.1 Name Storage

```clojure
Pattern: "my name is [NAME]"
Action: Extract name, store in memory
Response: "I will remember your name {NAME}. Nice to meet you!"
```

**Regex**: `my name is\s+(\w+)`

**Example**:

```
User: "my name is alice"
  ↓
Extract: "alice"
  ↓
Store: {:name "alice"}
  ↓
Response: "I will remember your name Alice. Nice to meet you!"
```

#### 2.2 Name Recall

```clojure
Pattern: "what is my name"
Condition: If name stored in memory
Response: "Your name is {NAME}."
Fallback: "I don't know your name yet. Tell me your name!"
```

**Example**:

```
User: "what is my name"
  ↓
Lookup: @memory :name
  ↓
If found: "Your name is Alice."
If missing: "I don't know your name yet..."
```

---

### 3. Emotion Detection

**System**: Keyword-based emotion classification

```clojure
(def emotion-keywords
  {:happy ["happy" "great" "wonderful" "awesome" "excellent" "amazing"]
   :sad ["sad" "depressed" "unhappy" "miserable" "terrible"]
   :angry ["angry" "furious" "mad" "irritated" "annoyed" "frustrated"]
   :tired ["tired" "exhausted" "fatigued" "sleepy" "weary"]
   :confused ["confused" "puzzled" "lost" "unclear" "don't understand"]})
```

**Detection Algorithm**:

1. Normalize input (lowercase)
2. Iterate through emotion keywords
3. Check if any keyword appears in text
4. Return first matching emotion

**Responses**:

- `:happy` → Congratulate and encourage
- `:sad` → Show empathy and hope
- `:angry` → Acknowledge and offer help
- `:tired` → Recommend rest
- `:confused` → Offer clarification

**Examples**:

```
User: "I am so sad"
  ↓ detect-emotion
:sad
  ↓ emotion-response
"I'm sorry to hear that. I hope things improve soon..."

User: "I feel amazing!"
  ↓ detect-emotion
:happy
  ↓ emotion-response
"That's wonderful! I'm glad you're feeling good..."
```

---

### 4. Inference Engine (Symptom → Diagnosis)

**System**: Rule-based forward chaining

```clojure
(def symptom-rules
  {#{:fever :cough} {:diagnosis "You may have flu symptoms."
                      :recommendation "Rest and stay hydrated."}
   #{:headache :tired} {:diagnosis "You seem to need rest."
                        :recommendation "Get some sleep and relax."}
   ; ... more rules
  })
```

**Symptom Extraction**:

```clojure
(def symptom-keywords
  {"fever" :fever
   "cough" :cough
   "headache" :headache
   "sore throat" :sore-throat
   "tired" :tired
   "fatigue" :fatigue})
```

**Algorithm**:

1. Parse input for symptom keywords
2. Create set of identified symptoms
3. Check against symptom rules (exact match first)
4. If no exact match, check for partial matches
5. Return diagnosis and recommendation

**Example Flow**:

```
User: "I have fever and cough"
  ↓ extract-symptoms
#{:fever :cough}
  ↓ lookup in symptom-rules
Found: #{:fever :cough}
  ↓ return result
{:diagnosis "You may have flu symptoms."
 :recommendation "Rest and stay hydrated."}
  ↓
Response: "You may have flu symptoms. Rest and stay hydrated."
```

**Rule Set**:

| Symptoms                 | Diagnosis          | Recommendation              |
| ------------------------ | ------------------ | --------------------------- |
| fever + cough            | Flu symptoms       | Rest and stay hydrated      |
| headache + tired         | Need rest          | Get sleep and relax         |
| headache + fever         | Possible infection | Consider seeing doctor      |
| cough + sore-throat      | Common cold        | Drink warm fluids, rest     |
| fever + headache + cough | Illness            | Monitor health, seek advice |

---

### 5. Knowledge Base

**System**: Semantic knowledge storage

```clojure
(def knowledge-base
  {:lisp "Lisp is a symbolic programming language..."
   :ai "Artificial Intelligence is..."
   :clojure "Clojure is..."
   ; ...
  })
```

**Knowledge Entry Function**:

```clojure
(defn lookup-knowledge [concept]
  (get knowledge-base concept "I don't have information about that."))
```

**Trigger Patterns**:

- "what is lisp" → `:lisp`
- "what is ai" → `:ai`
- "what is clojure" → `:clojure`
- "what is rule" → `:rule-based-ai`
- "what is inference" → `:inference`

**Example**:

```
User: "what is lisp"
  ↓ pattern match
Found: str/includes? "what is lisp"
  ↓ lookup-knowledge
:lisp
  ↓ retrieve value
"Lisp is a symbolic programming language used in AI systems..."
```

**Current Knowledge**:

1. **Lisp**
   - Symbolic programming language
   - Created by John McCarthy (1958)
   - Code-as-data (homoiconicity)
   - Central to AI history

2. **AI**
   - Simulation of human intelligence
   - Includes machine learning, reasoning, perception
   - Natural language understanding

3. **Clojure**
   - Modern Lisp dialect
   - Runs on JVM
   - Immutability and functional programming
   - Concurrency support

4. **Rule-Based AI**
   - Uses explicit if-then rules
   - Experts define decision rules
   - Applied to solve problems

5. **Inference**
   - Applying logical rules to derive conclusions
   - Central to symbolic AI
   - Forward/backward chaining

---

### 6. Fallback & Help

**Help System**:

```clojure
Pattern: "help"
Response: List of chatbot capabilities
```

**Fallback**:

```clojure
Pattern: Unknown/unmatched input
Response: "I do not understand. Could you rephrase that? Try saying 'help'..."
```

---

## Response Priority Order

The `respond` function applies rules in this priority:

1. **Name Storage** - "my name is X"
2. **Name Recall** - "what is my name"
3. **Greeting** - "hello", "hi", "hey"
4. **Knowledge** - "what is X"
5. **Emotion** - Emotional keywords
6. **Inference** - Symptom analysis
7. **Farewell** - "bye", "goodbye"
8. **Help** - "help"
9. **Fallback** - Unknown input

This ensures specific rules take precedence over general patterns.

---

## Extending the AI

### Add New Rule Type

```clojure
;; Step 1: Add pattern detector
(defn detect-new-pattern [text]
  (when (str/includes? text "pattern")
    :my-pattern))

;; Step 2: Add response generator
(defn handle-new-pattern []
  "Response for this pattern")

;; Step 3: Add to respond function
(cond
  (detect-new-pattern text)
  (handle-new-pattern)

  ; ... other rules
)
```

### Add Learning

```clojure
(def learned-rules (atom {}))

(defn learn-pattern [pattern response]
  (swap! learned-rules assoc pattern response))

;; In respond:
(or (learned-rules normalized)
    ; ... other rules
)
```

### Add Multi-Turn Context

```clojure
(def conversation-context (atom {:last-topic nil :turn 0}))

(defn update-context [topic]
  (swap! conversation-context
         assoc :last-topic topic :turn (inc (:turn @conversation-context))))
```

---

## Performance Considerations

- **String Operations**: O(n) for `str/includes?` on normalized text
- **Pattern Matching**: O(k) where k = number of rules checked
- **Memory Lookup**: O(1) for atom-based storage
- **Regex**: Pre-compiled patterns for efficiency
- **Inference**: O(m) where m = number of symptom combinations

---

## Testing the AI

### Unit Testing Patterns

```clojure
;; Test emotion detection
(assert (= (detect-emotion "I am sad") :sad))

;; Test name extraction
(assert (= (extract-name "my name is alice") "alice"))

;; Test symptom inference
(assert (infer-diagnosis "fever and cough"))

;; Test response generation
(assert (str/includes? (respond "hello") "hello"))
```

### Manual Testing Scenarios

See [DEVELOPMENT.md](./DEVELOPMENT.md#testing-in-browser) for comprehensive testing guide.

---

## Future AI Enhancements

1. **Fuzzy Matching** - Handle typos and variations
2. **Context Memory** - Multi-turn conversation tracking
3. **Learning System** - User teaches new patterns
4. **Dialogue Acts** - Question, statement, command classification
5. **Semantic Similarity** - Beyond keyword matching
6. **Temporal Reasoning** - Time-aware responses
7. **Contradiction Detection** - Logical consistency checking
8. **Confidence Scoring** - Certainty measures in responses

---

## References

- **Rule-Based Systems**: https://en.wikipedia.org/wiki/Expert_system
- **Symbolic AI**: https://en.wikipedia.org/wiki/Symbolic_artificial_intelligence
- **Pattern Matching**: https://en.wikipedia.org/wiki/Pattern_matching
- **Inference**: https://en.wikipedia.org/wiki/Inference

---

_LispMind Web — Demonstrating Symbolic AI in the Browser_ 🧠
