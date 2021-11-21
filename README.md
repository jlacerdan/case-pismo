Case application for Pismo's backend test. This application uses Spring and MongoDB.

To start the application with MongoDB, run "docker-compose up".

By default, two Operations (DEPOSIT and WITHDRAW) are created during startup using Mongock.

The application contains three endpoints:

GET - /accounts/{accountId} - Get data on a specific account.

POST - /accounts/ - Used to create an account.

    Request Body:
    {
        "documentNumber": "string" - required
    }

POST - /transactions/ - Used to create a transaction for an account. All attributes on the Request are required and "
amount" must be a positive value.

    Request Body:
    {
        "accountId": "string"
        "operationId": "string"
        "amount": 123.45
    }