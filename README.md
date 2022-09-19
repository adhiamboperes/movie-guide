# movie-guide
Android app that displays movies from TMDB API.

## Architecture
MVVM with Clean Architecture

Advantages:
- Creates abstraction
- Separation of concerns for the various layers
- Single focus classes to improve testability
- Easy to read and follow therefore great for teamwork

## Libraries
### Rxjava for reactive programming
Advantages:
- Composability — operators in RxJava are easily assembled to conduct difficult operations.
- Convertibility — operators in RxJava can transform data types by filtering, processing and expanding data streams.
- A lot of documentation and community support online

### Coil for image loading and caching
Advantages:
- Coil is kotlin-based, lightweight and faster than Glide
- Has growing community support
- Easy to set up and use
- Supports out of the box caching

### Room database
Advantages:
- compile-time validation of SQL queries
- supported by Google on android

### Dagger
- supported by Google and Jetpack
- Has great community support
- Builds and validates dependency graphs during build time, reducing boilerplate

## Assumptions
The app will always perform a network call when network is available, and in the event connectivity cannot be established, the app will fall back to using the local data source.

## Incomplete Features
- Movie Details screen
- Pagination of requests
