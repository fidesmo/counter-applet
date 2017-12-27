Counter Applet
==================

This is a very simple Java Card applet implementing a secure counter. It is based on the [Fidesmo gradle plugin example](https://github.com/fidesmo/gradle-fidesmo-example). You can read all about it in [this tutorial](https://developer.fidesmo.com/tutorials/javacard). 

Functionality
-------------
- When installed, the applet will receive the initial counter value as an install parameter. The initial counter value is limited to 1 byte: otherwise installation will fail with error code `6985`, "conditions of use not satisfied"
- Command SELECT (A4) will return the current value of the counter
- Command DECREMENT (00):
    - If counter > 0, it will decrease its value and return the new value of the counter
    - If counter = 0, it will return an error code `6985`

Building instructions
-------------
To build this project, use `gradle`:

`./gradlew build` - Build the application and create the CAP-file.

Then you can upload the CAP-file to your account at [Fidesmo Developers Portal](https://developer.fidesmo.com/) using the `/executableLoadFiles` POST operation, and use the Fidesmo Android app to install it on any Fidesmo-enabled device.

Releases
--------
The code in this repository is not written to be released, only as an example to be shared with a customer.

