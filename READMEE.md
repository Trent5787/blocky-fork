# Blocky Game

_(Gamewerks corporation internal code—do not share!)_

## Credits

Primary developer: Trenton Cochell

### Resources Used

- Java 23 coding language
  - Apache Netbeans IDE
  - Course website by Professor Osera for instructions:
    https://osera.cs.grinnell.edu/ttap/data-structures-labs/classical-encryption.html
  - Paden Houck provided basic conceptual help with method operation, function execution, and general purpose of various operations. 

## Changelog

~~~console

:
commit 7c44ad08d286f9ea8d1b63bd8dc8484b75eadf4e (HEAD -> main)
Author: Trenton Cochell <trentoncochell@gmail.com>
Date:   Mon Feb 10 19:47:54 2025 -0600

    Fixed the use of row in the getCompletedRows method. Completed lines now delete correctly

commit 334131c3bd4df93c867e707767dd5f2965adeed5
Author: Trenton Cochell <trentoncochell@gmail.com>
Date:   Mon Feb 10 19:46:44 2025 -0600

    Added the shuffle and its helper method to choose a random non duplicating piecetype

commit bbdf6abf868fec3f9646ec9834f6b003128d5a94
Author: Trenton Cochell <trentoncochell@gmail.com>
Date:   Sun Feb 9 14:38:30 2025 -0600

    Changed comparisons to < for well.length and well[0].length for isValidPosition method

commit 9cfb0f77cc565be285e9d1216b0ee1fc577408db
Author: Trenton Cochell <trentoncochell@gmail.com>
Date:   Sun Feb 9 14:00:15 2025 -0600

    Added missing break statement to processMovement() switch statement
:
~~~

Note: Some other small bug fixes may have been captured under other commits.
