# Patron API Interaction

Retrieve a list of all patrons.

**URL** : `/api/patrons`

**Method** : `GET`

**Auth required** : YES

**Data constraints** : `{}`

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Responses

**Condition** : No patrons are present.

**Code** : `200 OK`

**Content** : `[]`

### OR

**Condition** : Atleast 1 patron is present.

**Code** : `200 OK`

**Content type** : ```JSON array of PatronDTO objects```

**Content** :

```json
[
  {
    "id": 3,
    "name": "name 2",
    "age": 2,
    "favoriteGenre": "genre 2"
  }
]
```

---
***
___

Retrieve a patron by id.

**URL** : `/api/patrons/{id}`

**Method** : `GET`

**Auth required** : YES

**Input parameters type** : Path variable

**Parameter value** : `Long id`

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Response

**Condition** : The patron is present.

**Code** : `200 OK`

**Content type** : ```PatronDTO object as JSON```

**Content** :

```json
{
    "id": 3,
    "name": "name 2",
    "age": 2,
    "favoriteGenre": "genre 2"
  }
```

## Error Response

**Condition** : If the entered id doesn't match any patron.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The patron with the provided ID does not exist.
```

---
***
___

Add a new patron to the system.

**URL** : `/api/patrons`

**Method** : `POST`

**Auth required** : YES

**Headers required** :
```Cookie : access_token=jwtToken```

**Input parameters type** : Request Body as JSON

**Data constraints**

```json
{
    "name": "[patron name in plain text]",
    "age": "[patron age as positive number]",
    "favoriteGenre": "[patron favorite book genre in plain text]"
}
```

**Data example**

```json
{
	"name" : "name 5",
	"age" : 5,
	"favoriteGenre" : "genre 5"
}
```



## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The patron has been created successfully```


## Error Response

**Condition** : If the entered age is not positive.

**Code** : `400 BAD REQUEST`

**Content Type:** `JSON`

**Content** :

```json
{
  "age": "Does not match the defined pattern"
}
```

---
***
___

Update an existing patron's information.

**URL** : `/api/patrons/{id}`

**Method** : `PUT`

**Auth required** : YES

**Headers required** :
```Cookie : access_token=jwtToken```


**Input parameters type** :
- ```Request Body: Updated Patron Information, as JSON```
- ```Path Variable: patron id to update, as Long```

**Data constraints**

```json
{
    "name": "[patron name in plain text]",
    "age": "[patron age as positive number]",
    "favoriteGenre": "[patron favorite book genre in plain text]"
}
```

**Data example**

```json
{
	"name" : "name 5",
	"age" : 5,
	"favoriteGenre" : "genre 5"
}
```


## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The patron has been updated successfully```

## Error Responses

**Condition** : If the entered id doesn't match any patron.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The patron with the provided ID does not exist.
```
### OR


**Condition** : If the entered age is not positive.

**Code** : `400 BAD REQUEST`

**Content Type:** `JSON`

**Content** :

```json
{
  "age": "Does not match the defined pattern"
}
```


---
***
___

Remove a patron from the system.

**URL** : `/api/patrons/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Input parameters type** : ```Path Variable: patron id to delete, as Long```

**Headers required** :
```Cookie : access_token=jwtToken```

## Success Response

**Code** : `200 OK`

**Content type** : ```String```

**Content** :

```The patron has been deleted successfully```

## Error Responses

**Condition** : If the entered id doesn't match any patron.

**Code** : `404 NOT FOUND`

**Content Type:** `String`

**Content** :

```text
The patron with the provided ID does not exist.
```

### OR

**Condition** : If the entered id matches a patron that is borrowing a book.

**Code** : `500 INTERNAL SERVER ERROR`

**Content Type:** `String`

**Content** :

```text
An error has occurred, please refer to the Support Team.
```

