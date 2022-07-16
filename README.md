# Chalkboard code challenge

### How to run the project

You don't need any special configuration to run the project.
For easier run, please observe the following.
- Clone the project in Android Studio v2021.2.1 -last versions-.
- The gradle version is 7.3.3

## Implementation details

- The project implemented in MVVM-in presentation layer level- + Clean architecture-in app level-. This architecture will help us to have more clean codes, easy to testing and also it’s more easy to adding new features or changes.
- I’ve used Paging-3 library to add pagination feature to fetching data. Due to have a single source of truth, I’ve  - added a mediator by using Paging library.
- Room database is selected due to performance and easy to use features.
- According to situation Hilt is selected for dependency injection. I found it more easy rather than Dagger2.


## If I had more time ...

If I had more time to implement, I would add the following to the code:

- Writing a more details in the Readme file :)
- Using modular architecture -feature based-
- I didn’t have enough time to implement all parts of error handling. Just basic classes are added.
- Using Jetpack Compose to implement UI
- Writing more unit test and UI tests
- Network detection + Loading views + Footer recyclerview in Paging adapter to have for example a retry button in bottom of list
- Using single screen -> Implementing screens in fragments: For saving time, I’ve skipped implementing screens in Fragments. Implementing in Fragment has some benefits like reusability. 