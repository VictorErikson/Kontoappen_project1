# Kontoappen

Ett konsolprogram i Java där man kan skapa konton och sparkonton, logga in, sätta in och ta ut pengar. Det finns också en admin-inloggning som kan visa alla konton.

## Datasäkerhet/Inkapsling

Alla fält i `Account` (`accountHolder`, `balance`, `code` och `transactionHistory`) är `private`, och namn och kod är dessutom `final` så de inte kan ändras efter att kontot skapats. Saldot kan bara ändras via metoderna `deposit` och `withdrawal`, som också loggar transaktionen, och den interna metoden `addToBalance` är `protected` så bara subklasser kan använda den. Om fälten hade varit `public` hade vilken del av koden som helst kunnat skriva `account.balance = 1000000` eller ändra någons kod utan att det syntes i transaktionshistoriken.

## Skapande-mönster (Factory)

Konton skapas med `createAccount` och `createSavingsAccount` i `AccountRegister` i stället för med `new` i `Main`. Då läggs kontot alltid in i registrets lista direkt när det skapas, så det går inte att skapa ett konto som man sedan glömmer registrera (och som då aldrig skulle hittas av `findAccount`). `Main` och menyn behöver heller inte veta hur konstruktorerna ser ut, så om ett konto får nya fält ändras det på ett ställe och dessutom hålls Main renare pga detta. 

## Flöde: Insättning

1. Vid inloggningen skriver användaren in namn och kod. `Menu.showLoginMenu` anropar `accountRegister.findAccount(name)`, som söker i registrets lista och returnerar rätt `Account` (eller `SavingsAccount`). Menyn jämför koden och sparar kontot i `loggedinAccount`.
2. Användaren väljer `3: Deposit` i kontomenyn och skriver in ett belopp, t.ex. `500`.
3. `Menu.showAccountMenu` läser in beloppet med `Scanner` och kontrollerar att det är större än 0 (annars frågar den igen).
4. Menyn anropar `loggedinAccount.deposit(amount)`, eftersom kontot redan hämtades från registret vid inloggningen (och sparades i `loggedinAccount`) så behöver vi inte söka efter det igen.
5. I `Account.deposit` anropas `addToBalance(amount)` som ökar saldot, sedan sparas raden "Deposit of 500.0$, current balance is: ..." i `transactionHistory`.
6. Metoden returnerar texten "Deposit succeeded, current balance is: ..." som `Menu` skriver ut med `System.out.println`.

## Reflektion

När jag körde fast försökte jag först läsa felmeddelandet noga och hitta vilken rad det gällde. Ett exempel är när jag la till transaktionshistorik och räntan loggades dubbelt: `applyInterest` anropade `deposit`, som redan loggade en insättning, och sedan loggade jag räntan själv också. Jag använde AI för att få förklarat varför det blev dubbelt och fick förslaget att flytta själva saldoändringen till en separat metod (`addToBalance`) utan loggning. Sedan skrev jag om `applyInterest` så att den använder den metoden och loggar sin egen rad, och testade i programmet att historiken blev rätt.
