# Borrowing API Interaction
Allow a patron to borrow a book.

**URL** : `/api/borrow/{bookId}/patron/{patronId}`

**Method** : `POST`

**Auth required** : YES

**Headers required** :
```Cookie : access_token=jwtToken```

**Input parameters type** :
- ```Path Variable: book id to borrow, as Long```
- ```Path Variable: patron id of borrower, as Long```

## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The action has succeeded```


## Error Response

**Condition** : If the entered patron id is not present.

**Code** : `400 BAD REQUEST`

**Content Type:** `String`

**Content** :

```text
The patron with the provided ID does not exist
```

### OR

**Condition** : If the entered book id is not present.

**Code** : `400 BAD REQUEST`

**Content Type:** `String`

**Content** :

```text
The book with the provided ID does not exist
```

---
***
___

Record the return of a borrowed book by a patron.

**URL** : `/api/return/{bookId}/patron/{patronId}`

**Method** : `PUT`

**Auth required** : YES

**Input parameters type** :
- ```Path Variable: book id to borrow, as Long```
- ```Path Variable: patron id of borrower, as Long```

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The action has succeeded```

## Error Response

**Condition** : If the entered book id and patron id do not match any existing borrowing record.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The entered borrowing record does not exist
```

