Hey everyone if anyone wants to get started with the coding, I wrote some code for debugging:

--

EzToast: implement this interface and override toast(String message) so that instead of typing:

Toast.MakeText(this, message, Toast.LENGTH_SHORT).show();

you can type:
toast(message);

(open the javadoc for it to copy paste)

--

EzLog: implement this interface and override log(String message) and log() so that instead of typing:

Log.d(tag, message);

you can type:
log(message);

and instead of:

Log.d(tag,""); for a line break for readability when debugging

you can type:
log();
(override the function log() with Log.d("TAG","");)

also if you call str(int) it will return the int in String form (copy paste method from javadoc when you're overriding)

so instead of:

Log.d(tag, "Number of claims: "+Integer.valueOf(claims.size()).toString());

you can type:

log("Number of claims: "+str(claims.size()));
--

EzArrayListAdapter: it's a class. Purpose: Can use it to quickly test ListView UI visually.
Instantiate this with 3 parameters:

1. context

2. ArrayList holding the data model class elements you want to display in a listview (e.g. ArrayList<ClaimModel>)

3. the ListView ROW LAYOUT R.id (R.layout.listview_row) -- NOT the ListView's R.id inside the parent XML

Example:
EzArrayListAdapter adapter = new EzArrayListAdapter(this, claims, R.layout.claimslist_row);

After that you would just go listview.set(adapter); like normal.

If you name your getter methods in the model class match TextViews inside the row layout, it will set those TextViews according to the getter methods in the model class. For example, if getName() returns a String, the TextView with id R.id.name will have that String set to it. If there is no TextView with a matching name then it's ok, and if there is no getter that matches a certain TextView's R.id then it's ignored too.

I don't think we can use it in the final product since on principle, it's slower than writing a custom adapter (since it uses reflection to write the adapter for you), and in practice, it uses some methods from a WTFPL repository (in the import statement) so I don't know if we'd be allowed to use it.

--

-John
