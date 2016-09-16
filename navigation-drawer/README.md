# Navigation Drawer
An example, using the navigation drawer for switching between fragments.

## How to use this example
You can use this project as a base for your own project.
Obviously there are some steps to follow before calling it your own.

**AndroidManifest.xml**

- `android:icon`: add your own app icon
- `android:label`: shown title of your app
- `android:name`: fill in the class where your app starts of


**Creating fragments**

1. Create your layout `example_layout.xml`
2. Make your needed adjustments to the layout
3. Create a java class `ExampleFragment`
4. Copy the template code from either `FirstFragment`, `SecondFragment` or `ThirdFragment`
5. Alter the first argument of the inflate() method to the location of your layout (R.layout.<YOUR_LAYOUT_NAME>)
6. In `MainActivity`, go to *onNavigationItemSelected* method and add your fragment in the same style as in the first three if statements


## Notes
- *XML* layouts cannot be replaced by the `fragmentManager`
- Only *programmatically* created layouts can be replaced by the `fragmentManager`
