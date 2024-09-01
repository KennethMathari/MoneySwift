# MoneySwift Android App
MoneySwift is a fictitious Fintech company that provides an e-commerce solution. This project is a basic Android application that simulates a simple e-commerce platform. The app showcases a list of products and allows users to select items and proceed to a checkout screen where they can enter their payment information via a custom payment sheet that supports Stripe(card payments) or any other payment platform e.g MPESA, Airtel Money, Bitcoin etc.

## Setup
To set up and run this project on your local machine, follow these steps:

1. Clone the repository:
> git clone <https://github.com/KennethMathari/MoneySwift>
2. Open the project in Android Studio.
- Launch Android Studio and select Open an existing Android Studio project. Then navigate to the directory where you cloned the repository and select the project.
3. Sync Gradle files:
- After opening the project, Gradle will automatically start syncing. Wait for the process to finish.

## Build and Run
After opening the project, build and run to install the app in the emulator or a connected device.

## App Features
1. <b>Product Listing Page</b> 
 - Displays a list of products with their logo, name, and price.
2. <b>Product Info Page</b>
 - Displays detailed information for a selected product, including logo, name, price, and description.
 - Includes "Add to Cart" and "Buy Now" buttons.
3. <b>Cart Functionality</b>
 - Adds selected items to the cart and displays a badge with the item count.
 - Allows removing items from the cart.
4. <b>Payment Sheet</b>
- Collects the user's billing information e.g. Card, MPesa, Bitcoin etc

## API
> Products API: <https://my-json-server.typicode.com/carry1stdeveloper/mock-product-api/productBundles>

## Libraries & Plugins
- <b>Jetpack Compose </b>: For building the UI in a declarative manner.
- <b>Koin </b>: For dependency injection to manage dependencies efficiently.
- <b>Retrofit </b>: For network operations to fetch data from the API.
- <b>Kotlinx Serialization </b>: Facilitates data serialization and deserialization in a format-agnostic way.
- <b>Room </b>: For local storage.
- <b>List-Detail Layout </b>: For a dual-pane layout where one pane presents a list of items and another pane displays the details of items selected from the list.
- <b>Instantiator </b>: a little Kotlin library that uses reflection to fill data class with random test data.
- <b>Coil </b>: For image loading and caching.
- <b>JUnit </b>: For unit testing.
- <b>MockK </b>: For mocking dependencies in tests.
- <b> Turbine </b> : Specialized library for testing kotlinx.coroutines Flow.

Other dependencies are listed in the build.gradle files.

## Architecture
The project follows the MVI (Model-View-Intent) architecture and is modularized into features consisting of the Data, Domain & UI layers.

Below is an abstract diagram of the architecture
> ![](https://github.com/user-attachments/assets/0fdf9c12-be8e-4e25-bc20-7946ba096b6f)


### App Module
The app module serves as an entry point to the application. It depends on all, or relevant `feature` modules and provides root navigation. Additionally, the `app` module can depend directly on the `core` & `data`  modules.
### Feature Modules
The feature modules, represent a singular feature of the app i.e. `product` or `cart`. It allows us to decouple the features from the app itself and test these in isolation.
### Domain Modules
The domain modules is usually optional but it can contain use cases, domain models & interface repositories. It is independent of frameworks and libraries specific to Android, allowing it to be reused or tested easily without the need for an Android environment.
### Data Modules
The data modules contains a repository, data sources and DTO model classes.
### Core Modules
Core modules, also known as common modules, contain code that other modules frequently use. They reduce redundancy and don't represent any specific layer in an app's architecture e.g Network, Database module.
Features depend on core modules and core modules can depend on one another. We should be careful to not create cyclical dependencies amongst the core modules, however.

In the case of cyclical dependencies we most likely can refactor the code which is needed into a separate core module such as :core-common

## App Screenshots
> ![image](https://github.com/user-attachments/assets/c9de9ff4-4c69-404e-a845-eef0df2dc8de)
> 

## APK File
The APK file is [here](https://drive.google.com/file/d/1lMT8AHoYianNO-YPlqPIT8s3kMHBPhtn/view?usp=drive_link)


