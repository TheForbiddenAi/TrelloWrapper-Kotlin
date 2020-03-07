# TrelloWrapper-Kotlin
A kotlin wrapper, that supports java, for the [Trello REST API](https://developers.trello.com/)

## Documentation

### Maven Dependency

<a href="https://github.com/TheForbiddenAi/TrelloApiWrapper-Kotlin">
    <img src="https://img.shields.io/github/v/release/TheForbiddenAi/TrelloApiWrapper-Kotlin?label=Latest%20Version" alt="Latest Version">
</a>

<br>

Make sure to replace **VERSION** with the version shown above

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.TheForbiddenAi</groupId>
    <artifactId>TrelloApiWrapper-Kotlin</artifactId>
    <version>VERSION</version>
</dependency>
```

### Usage
To start using this wrapper, you must first create an instance of `TrelloApi`
```kotlin
val trelloApi = TrelloApi(trelloApiKey, trelloToken)
```

After creating an instance of `TrelloApi`, getting a board is as simple as just using the `getBoard` function
```kotlin
val board = trelloApi.getBoard(boardId)
```

To get a board's lists you can simply use the `getLists` function
```kotlin
val lists = board.getLists()
```

This is just a brief overview of the many features this wrapper has
