## INSURANCE CLAIMS SYSTEM

##Sample Request
http://localhost:8080/allow/create-account
```
{
    "username": "stesh",
    "fullName": "Stella Mbithe",
    "phoneNumber": "555-12340765432189",
    "email": "mbithe0@example.com",
    "dateOfBirth": "1995-07-14T21:00:00.000+00:00",
    "gender": "Female",
    "password": "pass",
    "roleId": 2 
}
```
##Sample Response
```
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```
##Sample Request
http://localhost:8080/allow/login
```
{
    "username": "stesh",
    "password": "pass"
}
```
##Sample Response
```
 "statusCode": "00",
    "statusMessage": "success",
    "result": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJwcml2aWxlZ2VzIjpbeyJhdXRob3JpdHkiOiJjdXN0b21lciJ9XSwic3ViIjoic3Rlc2giLCJpYXQiOjE3MzE5Mzc3NTIsImV4cCI6MTczMTk3Mzc1Mn0.ZXoPFJ0QoKS_Pr7r2ZZTvuTP0ZzKbJBPuQGNQxHql-s",
        "username": "stesh"
    }
    
```
```
##Sample Request
```
http://localhost:8080/api/customer/claim
```
```
```
{
    "policyNumber": "POL123431",
    "name": "medical",
    "incidentDate": "2024-10-10",
    "amountClaimed": 8000.0,
    "description": "Hospital bill for accident injuries",
    "attachments": [
        {
            "type": "medicalReport",
            "url": "https://example.com/report.pdf",
            "description": "This is the medical report"
        }
    ]
}
```
```

##Sample Response
```
```
```
```

{
    "statusCode": "00",
    "statusMessage": "Successfully submitted a claim",
    "result": {
        "id": 1,
        "policyNumber": "POL123431",
        "incidentDate": "2024-10-10T00:00:00.000+00:00",
        "amountClaimed": 8000.0,
        "claimStatus": {
            "id": 1,
            "name": "submitted"
        },
        "claimType": {
            "id": 1,
            "name": "medical"
        },
        "attachments": [
            {
                "id": 1,
                "type": "medicalReport",
                "url": "https://example.com/report.pdf",
                "description": "This is the medical report"
            }
        ]
    }
}
```    
```
##Sample Request
```
http://localhost:8080/api/insurer/claims

```
```
##Sample Response
```
```
```

{
"statusCode": "00",
"statusMessage": "Successfully retrieved claims",
"result": [
{
"id": 1,
"policyNumber": "POL123431",
"incidentDate": "2024-10-10T00:00:00.000+00:00",
"amountClaimed": 8000.00,
"claimStatus": {
"id": 1,
"name": "submitted"
},
"claimType": {
"id": 1,
"name": "medical"
},
"attachments": [
{
"id": 1,
"type": "medicalReport",
"url": "https://example.com/report.pdf",
"description": "This is the medical report"
}
]
}
]
}
```
```
```
##Sample Request
```
```
http://localhost:8080/api/insurer/claim/1/investigation-report
```

{
"attachments": [
{
"type": "medicalReport",
"url": "https://example.com/report.pdf",
"description":"This is the medical report"
}
]
}
```
```
```
##Sample Response
```
```
```
```
```
{
"statusCode": "00",
"statusMessage": "Investigation report successfully submitted.",
"result": null
}
```
```
```
##Sample Request
```
```
http://localhost:8080/api/insurer/claim/1/workflow
```
```
```

{
    "assignedTo": 2,
    "stageName": "investigation"
}
```
```
```
##Sample Response
```
```
```

{
    "statusCode": "00",
    "statusMessage": "success",
    "result": {
        "id": 1,
        "claim": {
            "id": 1,
            "policyNumber": "POL123431",
            "incidentDate": "2024-10-10T00:00:00.000+00:00",
            "amountClaimed": 8000.00,
            "claimStatus": {
                "id": 2,
                "name": "pending"
            },
            "claimType": {
                "id": 1,
                "name": "medical"
            },
            "attachments": [
                {
                    "id": 1,
                    "type": "medicalReport",
                    "url": "https://example.com/report.pdf",
                    "description": "This is the medical report"
                },
                {
                    "id": 2,
                    "type": "medicalReport",
                    "url": "https://example.com/report.pdf",
                    "description": "This is the medical report"
                }
            ]
        },
        "assignedUser": {
            "id": 2,
            "username": "stesh",
            "fullName": "Stella Mbithe",
            "phoneNumber": "555-12340765432189",
            "email": "mbithe0@example.com",
            "dateOfBirth": "1995-07-14T21:00:00.000+00:00",
            "gender": "Female",
            "name": "Stella Mbithe",
            "active": true
        },
        "workflowStage": {
            "id": 1,
            "stageName": "investigation"
        },
        "workflowStatus": {
            "id": 1,
            "statusName": "in-progress"
        }
    }
}
```
```
```
```
##Sample Request
```
```
```

http://localhost:8080/api/insurer/claim/1/approval
```
```
##Sample Response
```
```
```

{
"statusCode": "00",
"statusMessage": "Claim successfully approved.",
"result": null
}
```
```


##Sample Request
```
```
```
http://localhost:8080/api/insurer/claim/1/disbursement
```
```
```
{
 "transactionReference": "TX1234567890"

}
```
```
```
##Sample Response
```
```
```
{
    "statusCode": "00",
    "statusMessage": "Payment successfully disbursed",
    "result": null
}
```
```
```
```


##Sample Request
```
```
```
http://localhost:8080/api/insurer/claim/1/disbursement
```
```
```
{
 "transactionReference": "TX1234567890"

}
```
```
```
##Sample Response
```
```
```
{
    "statusCode": "00",
    "statusMessage": "Payment successfully disbursed",
    "result": null
}
```
```
```
```



##Sample Request
```
```
```
http://localhost:8080/api/insurer/claim/1/disbursement
```
```
```
{
 "transactionReference": "TX1234567890"

}
```
```
```
##Sample Response
```
```
```
{
    "statusCode": "00",
    "statusMessage": "Payment successfully disbursed",
    "result": null
}
```


