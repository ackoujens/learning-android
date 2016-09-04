# Beginning Android Games
Studying this book for making android applications and games

## Project structure
- `src/` - Contains all your Java source files
- `gen/` - All source files generated by the Android build system (not to be touched)
- `assets/` - All files your application needs (configuration, audio, images)
- `bin/` - Holds compiled code ready to be deployed
- `libs/` - Any additional JAR files we might need to depend on
- `res/` - Any resources your application might need like icons, strings, UI layouts in XML
- `AndroidManifest.xml` - Describes your application, activities and services, minimum and target Android version, needed permissions.

## Game design
Before diving into your code, you should plan what you're going to do first.
- Core game mechanics, including a level concept if applicable
- Rough backstory with the main characters
- List of items, power-ups, other things that modify the characters, mechanics, or environment.
- Rough sketch of the graphics style based on the backstory and characters
- Sketches of all the screens involved, diagrams of transitions between screens and transition triggers (ex: game over state)

## Basic game framework
Every game needs a basic game framework to be based on. Mostly split up in different modules. Each of these modules is composed of one or more interfaces.
- `Application and Window management` - responsible for creating a window and coping with things like closing the window or pausing/resuming application in Android.
- `Input` - related to window management module, keeps track of user input (touch, keys, periphery, accelerometer)
- `File I/O` - allows to get the bytes of our assets into the program from disk
- `Graphics` - most complex module, loading graphics and drawing on screen
- `Audio` - loading and playing sounds and music
- `Game framework` - ties all the above together, provides easy-to-use base for writing games