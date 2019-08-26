# Webmotors Challenge

This app is a really simple Webmotors Vehicle Catalog.

## How to run

### Install third-party

Download the project and run on Android Studio 3.5.

## Current features

- [x] Universal App
- [x] Custom Service Layer with Retrofit
- [x] Listing
- [x] Models
- [x] Pull to refresh
- [x] Automatic paging with infinite scroll
- [x] Offline Data using Room

## TODO (in the future):

- [ ] Tests and UI tests


# Architecture

The chosen architecture was protocol-oriented model-view-viewModel.

The Project uses some Architecture Components like ViewModel, LiveData, and other lifecycle-aware classes.

It's based on Google Samples Project, implementing the new architecture MVVM.

# Frameworks

- [Retrofit](https://github.com/square/retrofit) - Networking.
- [RXjava](https://github.com/ReactiveX/RxJava) - Async calls.
- [Glide](https://github.com/bumptech/glide) - Library to load images async.
- [Dagger](https://github.com/google/dagger) - Library to Dependency Injection.
