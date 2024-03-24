## Todo API using Spring

- Build a basic Todo API
- 2nd Commit : Added API versioning with custom message
- `ResponseEntity<?>` could be anything inside
- Added ApiResponse class can be used for almost every Response
- Added Delete and Updates API , but the updated API works such that we have to provide every entry in todo else it will take the default value like for boolean it takes `false` and for int it takes `0`
- Added another method with entries sent in query params
- Used Datatype such as Boolean instead of boolean , Integer instead of int , so that it takes null values as well