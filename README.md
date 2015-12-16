Counter Applet
==================

This is a very simple Java Card applet implementing a secure counter. It is based on the [Fidesmo gradle plugin example](https://github.com/fidesmo/gradle-fidesmo-example). You can read all about it in [this tutorial](https://developer.fidesmo.com/tutorials/javacard). To build this project, follow the instructions in [the example's README.md](https://github.com/fidesmo/gradle-fidesmo-example).

Functionality
-------------
- When installed, the applet will receive the initial counter value as an install parameter. The initial counter value is limited to 1 byte: otherwise installation will fail with error code `6985`, "conditions of use not satisfied"
- Command SELECT (A4) will return the current value of the counter
- Command DECREMENT (00):
    - If counter > 0, it will decrease its value and return the new value of the counter
    - If counter = 0, it will return an error code `6985`

