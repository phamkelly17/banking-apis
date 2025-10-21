# Banking Transactions API Task

## How to Run and build application
1. Clone repo
2. Run `./mvnw spring-boot:run` to start application
3. If using IntelliJ Idea IDE run the unit tests by clicking the green triangle when you are on the unit test file
4. If running unit tests via terminal download maven (`brew install maven`) and then run tests (`mvn test`)

## API Endpoints
1. `POST` `/api/accounts/transfer`
Create a new user with an initial balance.

Input:
```
public class AccountRequest {
    @NotNull @Positive
    private double balance;
    @NotNull
    private String name;

}
```

Output:
```
public class AccountResponse {
    @NotNull
    private UUID id; // usually wouldn't expose this to client, but we need to make more requests
    @NotNull
    private String name;
    @NotNull
    private double balance;
}

```
2. `PUT` `/api/accounts/transfer`

Transfer funds from one account to another.

Input:
```
public class TransactionRequest {
    @NotNull
    private UUID accountFromId;
    @NotNull
    private UUID accountToId;
    @NotNull @Positive
    private double amount;
}
```

Output:
```
public class TransactionResponse {
    @NotNull
    private TransactionType transactionType; // enum of WITHDRAWL or DEPOSIT
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private double amount;
    @NotNull
    private double newBalance;
}
```
3. `GET` `/api/accounts/getTransactionHistory`
Retrieve the transaction history for a given account using pagination.

Input:
```
public class TransactionRequest {
    @NotNull
    private UUID accountFromId;
    @NotNull
    private UUID accountToId;
    @NotNull @Positive
    private double amount;
}
```

Output:
List of `TransactionResponse` (see transfer endpoint output).

## Requirements

- [x] Design an appropriate endpoint path for each operation

- [x] Implement dependency injection (or equivalent) to manage your service layers.
  - used constructor injection in `AccountService` and `AccountController`

- [x] Use DTOs to pass data between layers
  - Have separate Request and Response DTOs to manage which data gets exposed to the client

- [x] Ensure that all endpoints validate the input data
  - Used `jakarta.validation.constraints` dependency to use @NotNull and @Positive for input fields that must be not null and/or positive

- [x] Handle errors graacefully
  - Handles bad inputs, insufficient funds, and invalid account IDs
  - Returns a `ResponseEntity` containing an error message

- [x] Store data in memory
  - Store in a HashMap
