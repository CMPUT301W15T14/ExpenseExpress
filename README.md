Mar 10:

                                                           Models
The flow of information in this app:                         ^ 
                                                             |
                                                             V
Files/Server <----> Application subclass (App) <------> Controllers <--------> Activities
                              |                                                   ^
                               -------abstract class ExpenseExpressActivity--------


Have every activity extend ExpenseExpressActivity.


Progress:

- App class is done other than anything related to Receipt
 
- LoginActivity/layout are 100% done (but no javadoc or unit test)
 
- ClaimController is 25% done
 
- FileHelper (gson read/write for local storage of claims, and tags) 100% done

- ElasticSearchHelper skeleton code done, overall 10% done

- ClaimListActivity (x% done) 

     - Brandon/John
