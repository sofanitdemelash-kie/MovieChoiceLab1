# MovieChoiceLab1


## Group Members

- Student 1: Semion Yohannes
- Student 2: Sofanit Demelash

## Project Overview

MovieChoiceLab1 is a simple Java console-based game that demonstrates Object-Oriented Programming concepts through a psychology-themed decision-making activity.

The idea of the game is based on the  **paradox of choice** . Sometimes, when people are given many options, choosing becomes harder instead of easier. In this game, the player is asked to choose something to watch from a list of movies, series, and documentaries. While the player is choosing, the program tracks some of their decision-making behavior.

The game tracks things like:

* how many hints the player asks for
* how many content items the player views
* how many times the player filters the options
* how many times the player goes back
* how long the player takes to finish

At the end, the game gives the player a simple decision-style result:

* **Satisficer**
* **Balanced Chooser**
* **Maximizer**

A satisficer is someone who is comfortable choosing a good enough option. A maximizer is someone who compares more options and tries harder to find the best possible choice. A balanced chooser is somewhere in the middle.

The main purpose of this project is not to build a complex psychology test, but to create a beginner-friendly Java MVP that clearly demonstrates the OOP concepts required in the assignment.

---

## How the Game Works

The game runs in the console.

When the program starts:

1. The game welcomes the player.
2. The player enters their name.
3. The player chooses or enters their mood.
4. The game starts tracking time.
5. The player sees a main menu.

The player can choose from options such as:

1. View all content
2. Filter by genre
3. Ask for a hint
4. Go back
5. Finish

When the player views content, the program shows the title, genre, duration, rating, and type of content. The content can be a movie, a series, or a documentary.

When the player filters by genre, the program only shows content that matches the genre entered by the player.

When the player asks for a hint, the program gives a simple suggestion based on the selected content and the player's mood.

When the player goes back, the program records that as a backtrack.

When the player finishes, the game shows a final summary including:

* the final selected item
* number of hints used
* number of items viewed
* number of filters used
* number of backtracks
* time taken
* final decision result

---

## Folder Structure

```text
MovieTwistLab/
├── README.md
└── src/
    ├── Main.java
    ├── Game.java
    ├── Player.java
    ├── DecisionTracker.java
    ├── WatchContent.java
    ├── Movie.java
    ├── Series.java
    └── Documentary.java
```

All Java files are stored inside the `src` folder. I kept the structure simple because this is a beginner-level MVP.

---

## How to Compile and Run

Open a terminal inside the main project folder.

### Compile

```bash
javac -d out src/*.java
```

### Run

```bash
java -cp out Main
```

The program starts from `Main.java` because `Main.java` contains the `main` method.

---

## Main Classes in the Project

### Main.java

`Main.java` is the entry point of the program.

Its job is simple. It creates a `Game` object and starts the game.

Example:

```java
Game game = new Game();
game.start();
```

This keeps the `main` method short and clean.

---

### Game.java

`Game.java` controls the main flow of the game.

It is responsible for:

* welcoming the player
* creating the player object
* loading the watchable content
* showing the menu
* reading the player's choices
* showing content details
* filtering content
* giving hints
* recording backtracking
* showing the final result

`Game.java` works like the controller or host of the game. It connects the other classes together.

Important fields in `Game.java` include:

```java
private Scanner input;
private Player player;
private ArrayList<WatchContent> contents;
private DecisionTracker tracker;
private WatchContent finalChoice;
```

Explanation:

* `Scanner input` reads user input.
* `Player player` stores the current player.
* `ArrayList<WatchContent> contents` stores movies, series, and documentaries.
* `DecisionTracker tracker` records the player's decision behavior.
* `WatchContent finalChoice` stores the content item the player finally chooses.

---

### Player.java

`Player.java` represents the person playing the game.

It stores:

```java
private String name;
private String mood;
```

The fields are private to demonstrate encapsulation.

The player is created using a constructor:

```java
public Player(String name, String mood)
```

This means that when a player object is created, it starts with a name and mood.

---

### DecisionTracker.java

`DecisionTracker.java` is responsible for tracking the psychology-related behavior.

It stores:

```java
private int hintsUsed;
private int moviesViewed;
private int filtersUsed;
private int backtracks;
private long startTime;
private long endTime;
```

These fields are private because other classes should not directly change them.

Instead, the game uses methods like:

```java
recordHintUsed();
recordMovieViewed();
recordFilterUsed();
recordBacktrack();
```

This class also tracks time using:

```java
System.currentTimeMillis();
```

At the end, it calculates a simple score and returns one of the three results:

* Satisficer
* Balanced Chooser
* Maximizer

---

### WatchContent.java

`WatchContent.java` is the parent class for all watchable content.

It stores common information that every watchable item has:

```java
private String title;
private String genre;
private int duration;
private double rating;
```

Movies, series, and documentaries all have a title, genre, duration, and rating. So instead of repeating these fields in every class, they are placed in the parent class.

`WatchContent` also contains shared methods such as:

```java
getTitle();
getGenre();
showDetails();
getHint();
getHint(String mood);
```

---

### Movie.java

`Movie.java` is a child class of `WatchContent`.

```java
public class Movie extends WatchContent
```

This means:

Movie is a type of WatchContent.

The `Movie` class inherits common fields and methods from `WatchContent`. It also overrides the `showDetails()` method to add that the content type is a movie.

---

### Series.java

`Series.java` is a child class of `WatchContent`.

```java
public class Series extends WatchContent
```

A series has an extra field:

```java
private int episodes;
```

This field belongs only to `Series`, because movies and documentaries do not usually have episodes.

The `Series` class overrides `showDetails()` to show the number of episodes.

---

### Documentary.java

`Documentary.java` is a child class of `WatchContent`.

```java
public class Documentary extends WatchContent
```

A documentary has an extra field:

```java
private String topic;
```

This field belongs only to `Documentary`, because documentaries usually focus on a specific topic.

The `Documentary` class overrides `showDetails()` to show the documentary topic.

---

## OOP Concepts Used

### 1. Classes and Objects

A class is a blueprint. An object is a real instance created from a class.

Classes used in this project include:

* `Game`
* `Player`
* `DecisionTracker`
* `WatchContent`
* `Movie`
* `Series`
* `Documentary`

Examples of objects:

```java
Game game = new Game();
Player player = new Player(name, mood);
new Movie("The Silent Planet", "Sci-Fi", 145, 8.7);
```

In this project, objects represent real parts of the game. For example, the player is an object, each movie is an object, and the tracker is also an object.

File references:

* `Main.java` line 3: creates a `Game` object.
* `Game.java` lines 65-72: creates `Movie`, `Series`, and `Documentary` objects.
* `Game.java` line 48: creates a `Player` object.

---

### 2. Encapsulation

Encapsulation means protecting data by making fields private and allowing access through methods.

Examples:

```java
private String name;
private String mood;
```

```java
private int hintsUsed;
private int moviesViewed;
```

These fields cannot be directly changed from outside their class.

For example, `DecisionTracker` does not allow another class to directly set `hintsUsed` to a random value. Instead, the game must call:

```java
recordHintUsed();
```

This is better because the value only changes when a hint is actually used.

File references:

* `Player.java` lines 2-3: private fields `name` and `mood`.
* `WatchContent.java` lines 2-5: private fields `title`, `genre`, `duration`, and `rating`.
* `DecisionTracker.java` lines 2-7: private tracking fields.

---

### 3. Constructors

A constructor is used to create and initialize an object.

Examples:

```java
public Player(String name, String mood)
```

```java
public WatchContent(String title, String genre, int duration, double rating)
```

```java
public Movie(String title, String genre, int duration, double rating)
```

Constructors help make sure objects start with meaningful values.

For example, a movie should not be created without a title, genre, duration, or rating. The constructor makes sure these values are provided when the object is created.

File references:

* `Player.java` lines 5-8: player constructor.
* `WatchContent.java` lines 7-12: parent constructor.
* `Movie.java` lines 2-4: movie constructor.
* `Series.java` lines 4-7: series constructor.
* `Documentary.java` lines 4-7: documentary constructor.
* `DecisionTracker.java` lines 9-14: tracker constructor initializes values to zero.

---

### 4. ArrayList

The project uses an `ArrayList` to store multiple watchable content items.

Example:

```java
private ArrayList<WatchContent> contents;
```

An `ArrayList` is useful because the game has many content items. Instead of creating separate variables like `movie1`, `movie2`, and `movie3`, all content items are stored in one list.

File references:

* `Game.java` line 7: declares the `ArrayList`.
* `Game.java` lines 65-72: adds content objects to the list.

---

### 5. Inheritance

Inheritance allows one class to reuse fields and methods from another class.

In this project, the parent class is:

```java
WatchContent
```

The child classes are:

```java
Movie
Series
Documentary
```

Examples:

```java
public class Movie extends WatchContent
```

```java
public class Series extends WatchContent
```

```java
public class Documentary extends WatchContent
```

This creates an IS-A relationship:

* Movie IS-A WatchContent
* Series IS-A WatchContent
* Documentary IS-A WatchContent

This avoids repeating common fields like title, genre, duration, and rating in each child class.

File references:

* `Movie.java` line 1: `Movie extends WatchContent`.
* `Series.java` line 1: `Series extends WatchContent`.
* `Documentary.java` line 1: `Documentary extends WatchContent`.

---

### 6. The `super` Keyword

The `super` keyword is used to call the parent class constructor or method.

Example:

```java
super(title, genre, duration, rating);
```

This sends the common values to the parent class constructor.

Another example:

```java
super.showDetails();
```

This calls the parent version of `showDetails()` before adding child-specific details.

File references:

* `Movie.java` line 3: calls the parent constructor.
* `Series.java` line 5: calls the parent constructor.
* `Documentary.java` line 5: calls the parent constructor.
* `Movie.java` line 8: calls parent `showDetails()`.
* `Series.java` line 12: calls parent `showDetails()`.
* `Documentary.java` line 12: calls parent `showDetails()`.

---

### 7. Method Overriding

Method overriding happens when a child class provides its own version of a method already found in the parent class.

The parent class `WatchContent` has:

```java
public void showDetails()
```

The child classes override this method:

```java
@Override
public void showDetails()
```

This lets each content type show details in its own way.

Examples:

* `Movie` adds `Type: Movie`.
* `Series` adds `Type: Series` and `Episodes`.
* `Documentary` adds `Type: Documentary` and `Topic`.

File references:

* `Movie.java` lines 6-10: overrides `showDetails()`.
* `Series.java` lines 10-15: overrides `showDetails()`.
* `Documentary.java` lines 10-15: overrides `showDetails()`.

---

### 8. Method Overloading

Method overloading means using the same method name with different parameters.

In `WatchContent.java`, the project has:

```java
public String getHint()
```

and:

```java
public String getHint(String mood)
```

Both methods are called `getHint`, but one has no parameter and the other receives the player's mood.

This demonstrates compile-time polymorphism.

File references:

* `WatchContent.java` lines 31-37: overloaded `getHint()` methods.

---

### 9. Polymorphism

Polymorphism means that one parent type can refer to different child objects.

In this project, the game stores movies, series, and documentaries in one list:

```java
private ArrayList<WatchContent> contents;
```

The list type is `WatchContent`, but it stores different child objects:

```java
contents.add(new Movie(...));
contents.add(new Series(...));
contents.add(new Documentary(...));
```

This works because `Movie`, `Series`, and `Documentary` all extend `WatchContent`.

File references:

* `Game.java` line 7: `ArrayList<WatchContent> contents`.
* `Game.java` lines 65-72: stores `Movie`, `Series`, and `Documentary` objects in the same list.

---

### 10. Superclass Reference Holding Subclass Objects

This requirement is shown when a parent class reference points to a child class object.

Example:

```java
WatchContent selected = list.get(index);
```

The variable type is `WatchContent`, but the actual object may be:

* a `Movie`
* a `Series`
* a `Documentary`

This is possible because of inheritance and polymorphism.

File references:

* `Game.java` line 92: `WatchContent selected = list.get(index);`.

---

### 11. Time Tracking

The game tracks how long the player takes to make a decision.

The tracker uses:

```java
System.currentTimeMillis();
```

The start time is saved when the game begins, and the end time is saved when the game finishes.

The time taken is calculated like this:

```java
(endTime - startTime) / 1000
```

Java gives the time in milliseconds, so dividing by `1000` converts it to seconds.

File references:

* `DecisionTracker.java` lines 16-18: `startTimer()`.
* `DecisionTracker.java` lines 20-22: `stopTimer()`.
* `DecisionTracker.java` lines 40-42: calculates time in seconds.

---

## Mapping Table

| Requirement                                   | File/Line Reference                                                                                                                     | Explanation                                                                                                     |
| --------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| Classes and Objects                           | `Main.java`line 3,`Game.java`lines 48 and 65-72                                                                                     | The program creates objects from classes such as `Game`,`Player`,`Movie`,`Series`, and `Documentary`. |
| Encapsulation                                 | `Player.java`lines 2-3,`WatchContent.java`lines 2-5,`DecisionTracker.java`lines 2-7                                               | Fields are private and accessed through methods.                                                                |
| Constructors                                  | `Player.java`lines 5-8,`WatchContent.java`lines 7-12,`Movie.java`lines 2-4,`Series.java`lines 4-7,`Documentary.java`lines 4-7 | Constructors initialize objects when they are created.                                                          |
| Inheritance                                   | `Movie.java`line 1,`Series.java`line 1,`Documentary.java`line 1                                                                   | These classes extend `WatchContent`.                                                                          |
| Method Overriding                             | `Movie.java`lines 6-10,`Series.java`lines 10-15,`Documentary.java`lines 10-15                                                     | Child classes override `showDetails()`.                                                                       |
| Method Overloading                            | `WatchContent.java`lines 31-37                                                                                                        | `getHint()`and `getHint(String mood)`use the same method name with different parameters.                    |
| Polymorphism                                  | `Game.java`line 7,`Game.java`lines 65-72                                                                                            | `ArrayList<WatchContent>`stores `Movie`,`Series`, and `Documentary`objects.                             |
| Superclass Reference Holding Subclass Objects | `Game.java`line 92                                                                                                                    | `WatchContent selected = list.get(index);`can refer to a `Movie`,`Series`, or `Documentary`.            |
| Time Tracking                                 | `DecisionTracker.java`lines 16-22 and 40-42                                                                                           | The game records start time and end time, then calculates time taken in seconds.                                |

---

## Final Note

MovieTwistLab is a simple beginner-level MVP. It keeps the code easy to understand while still demonstrating the required OOP concepts. The project uses a console-based game to show how objects can work together in Java.
