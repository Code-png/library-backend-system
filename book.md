# Book API Interaction

Retrieve a list of all books.

**URL** : `/api/books`

**Method** : `GET`

**Auth required** : YES

**Data constraints** : `{}`

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Responses

**Condition** : No books are present.

**Code** : `200 OK`

**Content** : `[]`

### OR

**Condition** : Atleast 1 book is present.

**Code** : `200 OK`

**Content type** : ```JSON array of BookDTO objects```

**Content** :

```json
[
  {
    "id": 4,
    "title": "title 4",
    "author": "author 4",
    "publicationYear": "2023-01-01",
    "isbn": "isbn 4"
  },
  {
    "id": 3,
    "title": "title 3 edited",
    "author": "author 3 edited",
    "publicationYear": "2023-01-01",
    "isbn": "isbn 3 edited"
  },
  {
    "id": 5,
    "title": "title 5",
    "author": "author 5",
    "publicationYear": "2023-01-01",
    "isbn": "isbn 5"
  }
]
```

---
***
___

Retrieve a book by id.

**URL** : `/api/books/{id}`

**Method** : `GET`

**Auth required** : YES

**Input parameters type** : Path variable

**Parameter value** : `Long id`

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Response

**Condition** : The book is present.

**Code** : `200 OK`

**Content type** : ```BookDTO object as JSON```

**Content** :

```json
{
  "id": 4,
  "title": "title 4",
  "author": "author 4",
  "publicationYear": "2023-01-01",
  "isbn": "isbn 4"
}
```

## Error Response

**Condition** : If the entered id doesn't match any book.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The book with the provided ID does not exist.
```

---
***
___

Add a new book to the library.

**URL** : `/api/books`

**Method** : `POST`

**Auth required** : YES

**Headers required** :
```Cookie : access_token=jwtToken```

**Input parameters type** : Request Body as JSON

**Data constraints**

```json
{
    "title": "[book title in plain text]",
    "author": "[book author in plain text]",
    "publicationYear": "[book publication year in plain text of format yyyy-MM-dd]",
    "isbn": "[book isbn in plain text]"
}
```

**Data example**

```json
{
	"title" : "title 2",
	"author" : "author 2",
	"publicationYear" : "2023-01-01",
	"isbn" : "isbn-2"
}
```



## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The book has been created successfully```


## Error Response

**Condition** : If the entered year doesn't match the yyy-MM-dd pattern.

**Code** : `400 BAD REQUEST`

**Content Type:** `JSON`

**Content** :

```json
{
  "publicationYear": "Does not match the defined pattern"
}
```

---
***
___

Update an existing book's information.

**URL** : `/api/books/{id}`

**Method** : `PUT`

**Auth required** : YES

**Headers required** :
```Cookie : access_token=jwtToken```


**Input parameters type** :
- ```Request Body: Updated Book Information, as JSON```
- ```Path Variable: book id to update, as Long```

**Data constraints**

```json
{
    "title": "[book title in plain text]",
    "author": "[book author in plain text]",
    "publicationYear": "[book publication year in plain text of format yyyy-MM-dd]",
    "isbn": "[book isbn in plain text]"
}
```

**Data example**

```json
{
	"title" : "title 2",
	"author" : "author 2",
	"publicationYear" : "2023-01-01",
	"isbn" : "isbn-2"
}
```


## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The book has been updated successfully```

## Error Responses

**Condition** : If the entered id doesn't match any book.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The book with the provided ID does not exist.
```
### OR


**Condition** : If the entered year doesn't match the yyy-MM-dd pattern.

**Code** : `400 BAD REQUEST`

**Content Type:** `JSON`

**Content** :

```json
{
  "publicationYear": "Does not match the defined pattern"
}
```


---
***
___

Remove a book from the library.

**URL** : `/api/books/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Input parameters type** : ```Path Variable: book id to delete, as Long```

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The book has been deleted successfully```

## Error Responses

**Condition** : If the entered id doesn't match any book.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The book with the provided ID does not exist.
```

### OR

**Condition** : If the entered id matches a borrowed book.

**Code** : `500 INTERNAL SERVER ERROR`

**Content Type:** `String`

**Content** :

```text
An error has occurred, please refer to the Support Team.
```

