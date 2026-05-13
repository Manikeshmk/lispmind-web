(ns core
  "LispMind Web — A Rule-Based AI Assistant powered by ClojureScript.
   This module implements symbolic AI concepts including:
   - Rule-based reasoning
   - Pattern matching
   - Memory handling
   - NLP keyword detection
   - Inference systems"
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [clojure.string :as str]))

;; ============================================
;; MEMORY SYSTEM
;; ============================================
;; Use atoms to store user information
;; This demonstrates stateful symbolic AI

(def memory
  "Global memory atom to store user information.
   Maps user-identifiable info like :name, :mood, :symptoms, etc."
  (atom {}))

(defn remember
  "Store a key-value pair in memory.
   Example: (remember :name \"Alice\")"
  [key value]
  (swap! memory assoc key value))

(defn recall
  "Retrieve a value from memory by key.
   Example: (recall :name)"
  [key]
  (get @memory key))

(defn clear-memory
  "Clear all stored memory."
  []
  (reset! memory {}))

;; ============================================
;; STRING PREPROCESSING
;; ============================================
;; Normalize user input for pattern matching

(defn normalize
  "Convert string to lowercase for consistent pattern matching.
   Example: (normalize \"Hello WORLD\") => \"hello world\""
  [text]
  (str/lower-case text))

(defn extract-name
  "Extract a name from user input.
   Example: (extract-name \"my name is alice\") => \"alice\""
  [text]
  (when-let [parts (re-find #"my name is\s+(\w+)" text)]
    (second parts)))

(defn extract-symptom-list
  "Extract symptoms from user input as a list.
   Example: (extract-symptom-list \"I have fever and cough\") => [\"fever\" \"cough\"]"
  [text]
  (re-seq #"(?:have|with|and)\s+(\w+)" text))

;; ============================================
;; KNOWLEDGE BASE
;; ============================================
;; Define facts and domain knowledge

(def knowledge-base
  "Knowledge base containing AI domain facts"
  {:lisp "Lisp is a symbolic programming language used in AI systems. Created by John McCarthy in 1958, it emphasizes code-as-data and homoiconicity."
   :ai "Artificial Intelligence is the simulation of human intelligence by machines. It includes machine learning, reasoning, perception, and natural language understanding."
   :clojure "Clojure is a modern dialect of Lisp running on the JVM, offering immutability, functional programming, and concurrency features."
   :rule-based-ai "Rule-based AI systems use explicit rules to make decisions. Experts define if-then rules, and the system applies them to solve problems."
   :inference "Inference is the process of applying logical rules to derive new conclusions from known facts. It is central to symbolic AI."})

(defn lookup-knowledge
  "Look up a concept in the knowledge base.
   Example: (lookup-knowledge :lisp)"
  [concept]
  (get knowledge-base concept "I don't have information about that topic."))

;; ============================================
;; EMOTION & INTENT DETECTION
;; ============================================
;; Pattern matching for emotional states

(def emotion-keywords
  "Map emotions to keyword patterns"
  {:happy ["happy" "great" "wonderful" "awesome" "excellent" "amazing"]
   :sad ["sad" "depressed" "unhappy" "miserable" "terrible"]
   :angry ["angry" "furious" "mad" "irritated" "annoyed" "frustrated"]
   :tired ["tired" "exhausted" "fatigued" "sleepy" "weary"]
   :confused ["confused" "puzzled" "lost" "unclear" "don't understand"]})

(defn detect-emotion
  "Detect user emotion from text.
   Returns the detected emotion keyword or nil.
   Example: (detect-emotion \"I am so sad\") => :sad"
  [text]
  (let [normalized (normalize text)]
    (some (fn [[emotion keywords]]
            (when (some #(str/includes? normalized %) keywords)
              emotion))
          emotion-keywords)))

(defn emotion-response
  "Generate a response based on detected emotion.
   Example: (emotion-response :sad)"
  [emotion]
  (case emotion
    :happy "That's wonderful! I'm glad you're feeling good. Keep up the positive energy!"
    :sad "I'm sorry to hear that. I hope things improve soon. Remember that better days are ahead."
    :angry "I understand you're frustrated. Take a deep breath. Want to talk about what's bothering you?"
    :tired "It sounds like you need rest. Make sure to take care of yourself and get some sleep."
    :confused "I can help clarify things. What specifically are you confused about?"
    nil))

;; ============================================
;; SYMPTOM & INFERENCE ENGINE
;; ============================================
;; Implement symbolic reasoning rules

(def symptom-rules
  "Define inference rules for symptoms.
   Each rule maps a set of symptoms to a diagnosis."
  {
   #{:fever :cough} {:diagnosis "You may have flu symptoms." :recommendation "Rest and stay hydrated."}
   #{:headache :tired} {:diagnosis "You seem to need rest." :recommendation "Get some sleep and relax."}
   #{:headache :fever} {:diagnosis "You might have an infection." :recommendation "Consider seeing a doctor."}
   #{:cough :sore-throat} {:diagnosis "You may have a cold." :recommendation "Drink warm fluids and rest."}
   #{:fever :headache :cough} {:diagnosis "These are symptoms of illness." :recommendation "Monitor your health and seek medical advice if needed."}
  })

(defn extract-symptoms
  "Extract mentioned symptoms from user input.
   Example: (extract-symptoms \"I have fever and cough\") => #{:fever :cough}"
  [text]
  (let [normalized (normalize text)
        symptom-keywords 
        {"fever" :fever
         "cough" :cough
         "headache" :headache
         "sore throat" :sore-throat
         "sore-throat" :sore-throat
         "tired" :tired
         "fatigue" :fatigue}]
    (reduce (fn [acc [keyword symptom]]
              (if (str/includes? normalized keyword)
                (conj acc symptom)
                acc))
            #{}
            symptom-keywords)))

(defn infer-diagnosis
  "Apply inference rules to diagnose based on symptoms.
   Example: (infer-diagnosis \"I have fever and cough\")"
  [text]
  (let [symptoms (extract-symptoms text)]
    (if (empty? symptoms)
      nil
      ;; Check for exact rule matches first
      (or (symptom-rules symptoms)
          ;; Check for partial matches (at least 1 symptom match)
          (some (fn [[rule-symptoms result]]
                  (when (some symptoms rule-symptoms)
                    result))
                symptom-rules)))))

;; ============================================
;; GREETING & BASIC INTENT
;; ============================================
;; Handle simple greeting patterns

(def greeting-patterns
  "Map greeting inputs to responses"
  {:hello "Hello! Nice to meet you."
   :hi "Hi there! How can I help you?"
   :hey "Hey! What's on your mind?"
   :greet "Nice to meet you!"})

(defn detect-greeting
  "Detect if text is a greeting.
   Example: (detect-greeting \"hello there\") => :hello"
  [text]
  (let [normalized (normalize text)]
    (cond
      (str/includes? normalized "hello") :hello
      (str/includes? normalized "hi ") :hi
      (str/includes? normalized "hey") :hey
      :else nil)))

;; ============================================
;; CONVERSATION HISTORY & ANALYTICS
;; ============================================
;; Track conversation state and statistics

(def conversation-state
  "Store conversation history and metrics"
  (atom {:messages [] :message-count 0 :start-time (js/Date.) :topics #{}}))

(defn log-conversation
  "Log a message to conversation history"
  [role text]
  (swap! conversation-state 
         (fn [state]
           (-> state
               (update :messages conj {:role role :text text :time (js/Date.)})
               (update :message-count inc)))))

(defn get-conversation-stats
  "Get statistics about the current conversation"
  []
  (let [state @conversation-state
        msg-count (:message-count state)
        duration (/ (- (.getTime (js/Date.)) 
                      (.getTime (:start-time state))) 1000)]
    {:total-messages msg-count
     :duration-seconds duration
     :avg-time-per-message (if (> msg-count 0) (/ duration msg-count) 0)
     :topics (:topics state)}))

(defn track-topic
  "Track topics discussed in conversation"
  [topic]
  (swap! conversation-state update :topics conj topic))

;; ============================================
;; ADVANCED PATTERN MATCHING
;; ============================================
;; More sophisticated text analysis

(defn has-question-word
  "Check if text is a question"
  [text]
  (let [normalized (normalize text)]
    (or (str/includes? normalized "what")
        (str/includes? normalized "how")
        (str/includes? normalized "why")
        (str/includes? normalized "when")
        (str/includes? normalized "where")
        (str/includes? normalized "who")
        (str/includes? normalized "?"))))

(defn extract-entities
  "Extract named entities from user input"
  [text]
  (let [normalized (normalize text)
        entities {:person [] :location [] :action []}]
    (cond-> entities
      (re-find #"alice|bob|john|mary|dave" normalized)
      (update :person conj (re-find #"alice|bob|john|mary|dave" normalized))
      
      (re-find #"london|paris|new york|tokyo|canada" normalized)
      (update :location conj (re-find #"london|paris|new york|tokyo|canada" normalized))
      
      (re-find #"run|walk|eat|sleep|think" normalized)
      (update :action conj (re-find #"run|walk|eat|sleep|think" normalized)))))

(defn calculate-sentiment-score
  "Calculate sentiment from text (-1 to 1)"
  [text]
  (let [normalized (normalize text)
        positive-words ["good" "great" "awesome" "happy" "love" "excellent" "wonderful"]
        negative-words ["bad" "terrible" "awful" "sad" "hate" "horrible" "awful"]
        
        pos-count (count (filter #(str/includes? normalized %) positive-words))
        neg-count (count (filter #(str/includes? normalized %) negative-words))]
    
    (cond
      (= 0 (+ pos-count neg-count)) 0
      :else (/ (- pos-count neg-count) (+ pos-count neg-count)))))

;; ============================================
;; MULTI-TURN CONTEXT
;; ============================================
;; Handle conversation context

(def conversation-context
  "Store context across turns"
  (atom {:last-topic nil
         :last-emotion nil
         :previous-message nil
         :conversation-flow []}))

(defn update-context
  "Update conversation context after each message"
  [topic emotion]
  (swap! conversation-context
         (fn [ctx]
           (-> ctx
               (assoc :last-topic topic
                      :last-emotion emotion
                      :previous-message (:current-message ctx))
               (update :conversation-flow conj 
                      {:topic topic :emotion emotion :timestamp (js/Date.)})))))

(defn get-contextual-response
  "Generate response considering conversation context"
  [input topic emotion]
  (let [ctx @conversation-context]
    (if (= topic (:last-topic ctx))
      "We're still discussing this topic. Tell me more!"
      "Let's talk about this new topic.")))

;; ============================================
;; VALIDATION & ERROR HANDLING
;; ============================================
;; Ensure input validity

(defn validate-input
  "Validate user input"
  [text]
  {:valid? (and (string? text) (> (count (str/trim text)) 0))
   :length (count text)
   :word-count (count (str/split text #"\s+"))})

(defn safe-respond
  "Safely handle user input with validation"
  [input]
  (let [validation (validate-input input)]
    (if (:valid? validation)
      (respond input)
      "Please enter valid text.")))

;; ============================================
;; RESPONSE RANKING & SCORING
;; ============================================
;; Score responses for quality

(defn score-response-quality
  "Score response quality based on various metrics"
  [response]
  (let [length (count response)
        has-punctuation (str/includes? response ".")
        has-numbers (re-find #"\d" response)
        specificity (if (> length 50) 1 0.5)]
    (+ (/ length 100)
       (if has-punctuation 0.2 0)
       (if has-numbers 0.1 0)
       specificity)))

;; ============================================
;; KNOWLEDGE EXPANSION
;; ============================================
;; More knowledge entries

(defn expand-knowledge
  "Dynamically expand the knowledge base"
  [topic description]
  (swap! (fn [kb] (assoc kb (keyword topic) description))))

(defn search-knowledge
  "Search knowledge base for relevant information"
  [query]
  (let [q (normalize query)]
    (some (fn [[key val]]
            (when (str/includes? (str val) q)
              val))
          knowledge-base)))

;; ============================================
;; RESPONSE GENERATION HELPERS
;; ============================================
;; More sophisticated response building

(defn build-response
  "Build a response from components"
  [& components]
  (str/join " " (filter some? components)))

(defn personalize-response
  "Add personalization based on memory"
  [base-response]
  (if-let [name (recall :name)]
    (str base-response " " name "!")
    base-response))

(defn format-recommendation
  "Format recommendations nicely"
  [recommendation]
  (str "💡 " recommendation))

;; ============================================
;; CONVERSATION FLOW MANAGEMENT
;; ============================================
;; Better conversation handling

(defn is-continuing-conversation
  "Check if user is continuing a previous thought"
  [text]
  (let [normalized (normalize text)
        continuations ["also" "and" "furthermore" "besides" "moreover" "however" "but"]]
    (some #(str/starts-with? normalized %) continuations)))

(defn is-affirmation
  "Check if user is agreeing"
  [text]
  (let [normalized (normalize text)
        affirmations ["yes" "yeah" "yep" "sure" "okay" "ok" "agreed" "right"]]
    (some #(= normalized %) affirmations)))

(defn is-negation
  "Check if user is disagreeing"
  [text]
  (let [normalized (normalize text)
        negations ["no" "nope" "not" "neither" "disagree" "wrong"]]
    (some #(= normalized %) negations)))

;; ============================================
;; MAIN RESPONSE ENGINE - ENHANCED
;; ============================================

(defn respond
  "Main inference engine that generates AI responses.
   Applies rules in priority order:
   1. Name storage & recall
   2. Greeting detection
   3. Knowledge questions
   4. Emotion detection
   5. Symptom inference
   6. Fallback
   
   Example: (respond \"hello\") => \"Hello! Nice to meet you.\""
  [input]
  (let [normalized (normalize input)]
    
    (cond
      ;; Rule 1: Store user name
      (str/includes? normalized "my name is")
      (let [name (extract-name normalized)]
        (if name
          (do
            (remember :name name)
            (str "I will remember your name " (str/capitalize name) ". Nice to meet you!"))
          "Could you tell me your name clearly?"))

      ;; Rule 2: Recall user name
      (str/includes? normalized "what is my name")
      (if-let [name (recall :name)]
        (str "Your name is " (str/capitalize name) ".")
        "I don't know your name yet. Tell me your name!")

      ;; Rule 3: Greeting patterns
      (detect-greeting input)
      (greeting-patterns (detect-greeting input))

      ;; Rule 4: Knowledge questions (Lisp, AI, etc.)
      (str/includes? normalized "what is lisp")
      (lookup-knowledge :lisp)

      (str/includes? normalized "what is ai")
      (lookup-knowledge :ai)

      (str/includes? normalized "what is clojure")
      (lookup-knowledge :clojure)

      (str/includes? normalized "what is rule")
      (lookup-knowledge :rule-based-ai)

      (str/includes? normalized "what is inference")
      (lookup-knowledge :inference)

      ;; Rule 5: Emotion detection
      (detect-emotion input)
      (emotion-response (detect-emotion input))

      ;; Rule 6: Symptom inference (if-then rules)
      (extract-symptoms normalized)
      (let [diagnosis (infer-diagnosis normalized)]
        (if diagnosis
          (str (:diagnosis diagnosis) " " (:recommendation diagnosis))
          "I detected some symptoms. You might want to consult a doctor."))

      ;; Rule 7: Farewell
      (or (str/includes? normalized "bye")
          (str/includes? normalized "goodbye"))
      "Goodbye! Come back soon!"

      ;; Rule 8: Help request
      (str/includes? normalized "help")
      "I can help you with:\n• Storing your name\n• Detecting emotions\n• Analyzing symptoms\n• Answering questions about Lisp and AI\n\nTry asking me something!"

      ;; Fallback: Unknown input
      :else
      "I do not understand. Could you rephrase that? Try saying 'help' for suggestions.")))

;; ============================================
;; UI & DOM INTERACTION
;; ============================================
;; Handle user interface and message display

(def current-timestamp
  "Get current timestamp for messages"
  (fn []
    (let [now (js/Date.)
          hours (.getHours now)
          minutes (.getMinutes now)]
      (str (if (< hours 10) "0" "") hours 
           ":" 
           (if (< minutes 10) "0" "") minutes))))

(defn add-message
  "Add a message to the chat area.
   sender: :user or :ai
   text: message content"
  [sender text]
  (let [messages-container (dom/getElement "messages")
        message-div (dom/createElement "div")
        bubble (dom/createElement "div")
        timestamp (dom/createElement "span")]
    
    ;; Set message class based on sender
    (dom/classes message-div (str "message " (if (= sender :user) "user-message" "ai-message")))
    
    ;; Create message bubble
    (dom/classes bubble "message-bubble")
    (dom/setTextContent bubble text)
    
    ;; Create timestamp
    (dom/classes timestamp "message-timestamp")
    (dom/setTextContent timestamp (current-timestamp))
    
    ;; Append elements
    (dom/appendChild message-div bubble)
    (dom/appendChild message-div timestamp)
    (dom/appendChild messages-container message-div)
    
    ;; Auto-scroll to bottom
    (set! (.-scrollTop messages-container) (.-scrollHeight messages-container))))

(defn handle-send-message
  "Process user message and generate AI response"
  []
  (let [input-element (dom/getElement "user-input")
        user-message (.-value input-element)]
    
    (when-not (str/blank? user-message)
      ;; Add user message to chat
      (add-message :user user-message)
      
      ;; Clear input
      (set! (.-value input-element) "")
      
      ;; Generate and add AI response
      (let [ai-response (respond user-message)]
        (js/setTimeout 
          (fn [] (add-message :ai ai-response))
          300)))))  ;; Slight delay for natural feel

;; ============================================
;; EVENT LISTENERS
;; ============================================
;; Setup keyboard and button interactions

(defn setup-event-listeners
  "Attach event handlers to UI elements"
  []
  (let [input-element (dom/getElement "user-input")
        send-button (dom/getElement "send-btn")]
    
    ;; Send button click
    (events/listen send-button "click" 
                   (fn [_] (handle-send-message)))
    
    ;; Enter key to send
    (events/listen input-element "keypress"
                   (fn [e]
                     (when (= (.-key e) "Enter")
                       (.preventDefault e)
                       (handle-send-message))))))

;; ============================================
;; INITIALIZATION
;; ============================================
;; Start the chatbot

(defn init
  "Initialize the LispMind chatbot.
   Called on page load and during development reload."
  []
  (js/console.log "🧠 LispMind Web — Symbolic AI Assistant Initialized!")
  (js/console.log "📚 Built with ClojureScript & Rule-Based Reasoning")
  (setup-event-listeners))

;; Initialize on load
(init)
