# DogWatch
MVVM Clean Architecture App with Testing and Modularisation.



## App Structure
I followed clean architecture guidelines, there is an App and Core module.

#### Core Module
The Core module is segmented into three parts, data_layer containing the ApiService and Repository, domain_layer containing the models and user_case_layer containing the actions a user can take for example fetching a list of dogs and images of dogs.

#### App Module
The App module follows the MVVM pattern using LiveData, the state is managed by a data class



## Testing
Every open method has been tested, there are also Espresso Tests



## Libraries
Networking: Rx, Retrofit, OkHttp, Gson
DI: Dagger
View: Picasso
Testing: JUnit, Mockito, Espresso, Barista
