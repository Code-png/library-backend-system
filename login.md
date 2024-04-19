# Login

Used to collect a Token for a registered User.

**URL** : `/api/login`

**Method** : `POST`

**Auth required** : NO

**Input parameters type** : form-data

**Data constraints**

```json
{
    "username": "[username in plain text]",
    "password": "[password in plain text]"
}
```

**Data example**

```json
{
    "username": "user1",
    "password": "test"
}
```

## Success Response

**Code** : `200 OK`

**Content Type:**

`Empty response body, but an HTTPonly cookie will be set, with the value of the access token generated`

**Content** :

```text
access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyIDEiLCJleHAiOjE3MDY1Mjg4NzMsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MS9hcGkvbG9naW4ifQ.ls7LQWJB14yBXzTFWnSXA6jQU9AKn_6vhQ8RPcUSbm8
```

## Error Response

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `400 BAD REQUEST`

**Content Type:** `String`

**Content** :

```text
The username and password that you entered do not match.
```
