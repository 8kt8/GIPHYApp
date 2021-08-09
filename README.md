# GIPHYApp
Protopyte project with intergration of GIPHY API https://developers.giphy.com/

## Best practices

- Separation of concerns
- KISS
- DRY
- SOLID
- Commend Query
- Clean Architecture

## Architecture
Logic in application can be seen as segregated into four abstract layers:

**_User Interface layer:_**
* renders ui model (View - part of UiComponent)
* look&feel, animations, colors, styles, descriptions, layout
* captures user interactions with UI elements and only routes them (NOT handling) into the system

**_Application layer (aka "Glue layer"):_**
* controls user flow inside app e.g navigation 
* passes ui model to UI layer 
* handles user interaction with UI
* integrates standalone pieces of functionality from other layers

**_Domain layer:_**
* executes business domain flows aka "business logic

**_Infrastructure layer:_**
* provides general functionality not specific to business domain
    * networking (Retrofit)
    * reactive (RxJava)

#### Remarks
**UiModel** has only data to display, for a View. Colors, styles, descriptions etc. are View implementation details, they are not part of **UiModel**.

### Benefits :
Makes the code much more pleasant to work with!
* No need to look for things
* Readable and intuitive
* Easy to reason about and modify
* Productive and less stressful
