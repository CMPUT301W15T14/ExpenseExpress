Mar 10:

Guys, I don't think we will be making specific TODO's since it's a lot of effort. Instead, please look at the Use Cases and implement whatever we haven't done. Part 4 is due on Monday


Progress:

- App class is done other than anything related to Receipt
 
- LoginActivity/layout is 100% done (but no javadoc or unit test) and is working.
 
- ClaimController is 25% done
 
- FileHelper (gson read/write for local storage of claims, and tags) 100% done

- ElasticSearchHelper skeleton code done, overall 10% done


Activities to do:

- ClaimListActivity (started)

- ExpenseListActivity or ClaimActivity (haven't started)

I think these are the only two activities we need. Everything else (including single Expense's detail) can be done in a dialog (AlertDialog, Dialog, DialogFragment). We won't have to worry about activity life cycle (a little bit in DialogFragment). We can still use the layout files we already have, in those dialogs, of course.
We can even switch/case setContentView if you want to use a different layout for Claimant vs. Approver (it might prove easier to organize the code that way) or we can use the same layout for both and dynamically modify them. App has a field that remembers the mode (claimant or approver) for the duration of the application.
