Mar 13:
 Note in order to properly modify the .classpath in your local repo, do this:
 
 git pull   (shold get a message telling you to commit or stash .classpath)
 
 git stash --patch    (this is an interactive mode
 
     In Interactive Mode: You want to add only .classpath to the stash...
     
                          Y : will add current chunk
                          
                          n : will skip current chunk
                          
                          q : will quit, skipping rest of chunks.
                          
 git pull   (your .classpath should be properly set, gson files will now be accessed locally thru the plugins               folder)
 


Mar 11:

See Issues at right side, divided remaining work into 12 parts. As we start them we will be breaking them into smaller parts.

-------

How to easily code a list adapter:

BaseAdapter is an abstract class, so you have to extend it.
The easiest way is to do it anonymously (anonymous just means subclassing/implementing it on-the-fly without assigning it a class name).

ListView listView = findViewById(R.id.listView);
listView.setAdapter(new BaseAdapter(){

});
-------------


After quickfix --> add unimplemented methods in Eclipse, you get 4 methods:

getCount(): put the size of the list here (return myList.size();)

getItem(int position): this is used if you add a onItemClickListener/onItemSelectListener/onItemLongClickListener, otherwise not needed

getItemId(int id): this is used for Cursors (for example for an SQLite database - so this doesn't concern us

getView(int position, View convertView, ViewGroup container): this is the one you have to implement.

------------------

How to implement getView:

Ultimately, you're returning a View that contains everything you want inside a row for the listView you want to display. So the general pattern is:

1. inflate row layout XML
2. setText on its TextViews based on the position parameter (which is the position inside myList)
3. return the view

to save memory you can use viewHolder and convertView pattern.
(you can see an example in ClaimListActivity.TagListDialogFragment.TagListAdapter where I used convertView)


------------------
-------------------


Mar 10:

Guys, I don't think we will be making specific TODO's since it's a lot of effort. Instead, please look at the Use Cases and implement whatever we haven't done. Part 4 is due on Monday.

** Delete project from Eclipse and reimport after pulling, to get rid of build path error if you have it


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
