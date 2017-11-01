# [twister](https://github.com/ShepherdJerred/twister)
Android app similar to Twitter with a mock backend

### Known bugs
* None

### Extra credit
* None

### TODO
* Twist List
    * Verify that time is relative on Twist List
    * Twist searching
* Add Twist
    * Limit to 150 characters
    * Require one non-whitespace character
    * Add to database

### Contributions
Noah Kinslow (50%)
* Link clicking in Twists (Extra credit)
* Regex highlighting for Twist search (Extra credit)
* Landscape support (Extra credit)

Jerred Shepherd (50%)
* App icon
* DataFetcher
* User login
    * LoginActivity, LoginFragment
    * Show error message if API returns error
* Twist List
    * ListActivity, ListFragment
    * Recycler view
    * Relative time
    * Click to show details
    * Action Bar
        * Add twist
        * Search twists
        * Logout (overflow)
        * About (overflow)
* Add Twist
    * AddTwistActivity, AddTwistFragment
    * Disable posting if textbox doesn't have one non-whitespace character or
      more than 150 characters
    * Posting returns it to list activity
    * Add twist to SQLite DB
* User Details activity
    * DetailActivity, DetailFragment
    * Recycler view
* Database/cache
    * TwisterDatabase, TwisterDatabaseHelper
* Image caching (Extra credit)
