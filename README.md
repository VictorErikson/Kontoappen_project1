## Länk till inspelning:
### Jag kunde inte hitta behörighetsinställningarna så säg till om det inte funkar
https://teams.microsoft.com/l/meetingrecap?driveId=b%212KOGcs3rBkKNiq-uRRKWr-EAmquQgQtFpLYEdzCT6g8iasw_Q5FWTbADlsV3gRoO&driveItemId=01IQ4SR2Z6XST5NXLXK5G2IEQD3I3562M5&sitePath=https%3A%2F%2Ffunet-my.sharepoint.com%2F%3Av%3A%2Fg%2Fpersonal%2F3kdyhapp26_erikvi_folkuniversitetet_nu%2FIQA-vKfW3XdXTaQSA9o332mdARAXtvZfkp2vMGHZXQyfGpQ&fileUrl=https%3A%2F%2Ffunet-my.sharepoint.com%2Fpersonal%2F3kdyhapp26_erikvi_folkuniversitetet_nu%2FDocuments%2FInspelningar%2FMeeting+with+Victor+Eriksson+APP26-20261008_140559-Meeting+Recording.mp4%3Fweb%3D1&threadId=19%3Ameeting_MWRmMmQ3ZmQtZjU3ZS00ZWFlLWFlYWMtNmYzNTJmMGVlMzA4%40thread.v2&organizerId=522b25bf-240f-47ea-a95c-642037935b1c&tenantId=a4d3b9bf-2082-4eee-ab79-fd407faef1e5&callId=f0bcc092-54eb-4baa-98e9-9b2c4cd6f682&threadType=Meeting&meetingType=MeetNow&subType=RecapSharingLink_RecapCore&recapType=Recording

# Kontoappen

Ett konsolprogram i Java där man kan skapa konton och sparkonton, logga in, sätta in och ta ut pengar. Det finns också en admin-inloggning som kan visa alla konton.

## Datasäkerhet/Inkapsling

Alla fält i `Account` (`accountHolder`, `balance`, `code` och `transactionHistory`) är `private`, och namn och kod är dessutom `final` så de inte kan ändras efter att kontot skapats. Saldot kan bara ändras via metoderna `deposit` och `withdrawal`, som också loggar transaktionen, och den interna metoden `addToBalance` är `protected` så bara subklasser kan använda den. Om fälten hade varit `public` hade vilken del av koden som helst kunnat skriva `account.balance = 1000000` eller ändra någons kod utan att det syntes i transaktionshistoriken.

## Skapande-mönster (Factory)

Konton skapas med `createAccount` och `createSavingsAccount` i `AccountRegister` i stället för med `new` i `Main`. Då läggs kontot alltid in i registrets lista direkt när det skapas, så det går inte att skapa ett konto som man sedan glömmer registrera (och som då aldrig skulle hittas av `findAccount`). `Main` och menyn behöver heller inte veta hur konstruktorerna ser ut, så om ett konto får nya fält ändras det på ett ställe och dessutom hålls Main renare pga detta. 

## Flöde: Insättning

1. Vid inloggningen skriver användaren in namn och kod. `Menu.showLoginMenu` anropar `accountRegister.findAccount(name)`, som söker i registrets lista och returnerar rätt `Account` (eller `SavingsAccount`). Menyn jämför koden och sparar kontot i `loggedinAccount`.
2. Användaren väljer `3: Deposit` i kontomenyn och skriver in ett belopp, t.ex. `500`.
3. `Menu.showAccountMenu` läser in beloppet med `Scanner`.
4. Menyn anropar `loggedinAccount.deposit(amount)`, eftersom kontot redan hämtades från registret vid inloggningen (och sparades i `loggedinAccount`) så behöver vi inte söka efter det igen.
5. `Account.deposit` kontrollerar först att beloppet är större än 0 (annars returneras "Deposit denied: amount must be greater than 0." och saldot lämnas orört). Sedan anropas `addToBalance(amount)` som ökar saldot, sedan sparas raden "Deposit of 500.0$, current balance is: ..." i `transactionHistory`.
6. Metoden returnerar texten "Deposit succeeded, current balance is: ..." som `Menu` skriver ut med `System.out.println`.

## Reflektion

När jag körde fast försökte jag först läsa felmeddelandet noga och hitta vilken rad det gällde. Ett exempel är när jag la till transaktionshistorik och räntan loggades dubbelt: `applyInterest` anropade `deposit`, som redan loggade en insättning, och sedan loggade jag räntan själv också. Jag använde AI för att få förklarat varför det blev dubbelt och fick förslaget att flytta själva saldoändringen till en separat metod (`addToBalance`) utan loggning. Sedan skrev jag om `applyInterest` så att den använder den metoden och loggar sin egen rad, och testade i programmet att historiken blev rätt.
